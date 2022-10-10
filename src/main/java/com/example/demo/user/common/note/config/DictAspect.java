package com.example.demo.user.common.note.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.user.common.dict.utill.DicDataUtils;
import com.example.demo.user.common.note.annocation.Dict;
import com.example.demo.user.common.utils.PageUtils;
import com.example.demo.user.modules.student.entity.TStudentEntity;
import com.example.demo.user.modules.student.service.TStudentService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Aspect
@Component
@Slf4j
public class DictAspect {
    private static String DICT_TEXT_SUFFIX = "Name";

    @Autowired
    private TStudentService studentService;

    // 定义切点Pointcut 拦截所有对服务器的请求
//    @Pointcut("execution( * com.*.*.controller.*.*(..))")
    @Pointcut("execution(* com.example..controller..*(..))")
    public void excudeService() {
    }

    /**
     * 这是触发 excudeService 的时候会执行的，在环绕通知中目标对象方法被调用后的结果进行再处理
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("excudeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //这是定义开始事件
        long time1 = System.currentTimeMillis();
        //这是方法并获取返回结果
        Object result = pjp.proceed();
        //这是获取到 结束时间
        long time2 = System.currentTimeMillis();
        log.debug("获取JSON数据 耗时：" + (time2 - time1) + "ms");
        //解析开始时间
        long start = System.currentTimeMillis();
        //开始解析（翻译字段内部的值凡是打了 @Dict 这玩意的都会被翻译）
        this.parseDictText(result);
        //解析结束时间
        long end = System.currentTimeMillis();
        log.debug("解析注入JSON数据  耗时" + (end - start) + "ms");
        return result;
    }

    /**
     * 本方法针对返回对象为Result 的PageUtils的分页列表数据进行动态字典注入
     * 字典注入实现 通过对实体类添加注解@dict 来标识需要的字典内容,字典分为单字典dataSource即可
     * 示例为Student   
     *  字段为stu_sex 添加了注解@Dict(dicDataSource = "stu_sex") 会在字典服务立马查出来对应的text 然后在请求list的时候将这个字典text，已字段名称加_dictText形式返回到前端
     * 例输入当前返回值的就会多出一个stu_sex_dictText字段
     * {
     * stu_sex:1,
     * stu_sex_dictText:"男"
     * }
     * 前端直接取值sext_dictText在table里面无需再进行前端的字典转换了
     * customRender:function (text) {
     * if(text==1){
     * return "男";
     * }else if(text==2){
     * return "女";
     * }else{
     * return text;
     * }
     * }
     * 目前vue是这么进行字典渲染到table上的多了就很麻烦了 这个直接在服务端渲染完成前端可以直接用
     *
     * @param result
     */
    private void parseDictText(Object result) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (result instanceof PageUtils) {
            List<JSONObject> items = new ArrayList<>();
            PageUtils pageUtils = (PageUtils) result;
            String jsons = JSON.toJSONString(pageUtils);
            //循环查找出来的数据
            List<TStudentEntity> studentEntity = JSON.parseArray(JSON.parseObject(jsons).getString("list"), TStudentEntity.class);
            for (TStudentEntity tStudentEntity : studentEntity) {

                // 将实体转换成JSONObject
//                String json = (String) JSON.toJSONString(tStudentEntity);

                //update-begin--Author:scott -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
                //for (Field field : record.getClass().getDeclaredFields()) {
//                JSONObject item = JSONObject.parseObject(json, Feature.OrderedField);
                JSONObject resultJson = new JSONObject(new LinkedHashMap<>());
                for (Field field : ObjConvertUtils.getAllFields(tStudentEntity)) {

                    boolean flag = true;

                    //update-end--Author:scott  -- Date:20190603 ----for：解决继承实体字段无法翻译问题------
                    if (field.getAnnotation(Dict.class) != null) {

                        flag = false;

                        String datasource = field.getAnnotation(Dict.class).dicDataSource();
                        String text = field.getAnnotation(Dict.class).dicText();
                        //获取当前带翻译的值
                        // 根据反射名称动态调用Getter
                        String key = (String) tStudentEntity.getClass().getMethod(
                                "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1)).invoke(tStudentEntity);

                        //翻译字典值对应的txt
                        String textValue = DicDataUtils.getDictName(datasource, key);
                        //如果给了文本名
                        if (!StringUtils.isEmpty(text)) {
                            resultJson.put(field.getName(), tStudentEntity.getClass().getMethod(
                                    "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1)).invoke(tStudentEntity));
                        } else {
                            resultJson.put(field.getName(), tStudentEntity.getClass().getMethod(
                                    "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1)).invoke(tStudentEntity));
                            //走默认策略
                            resultJson.put(field.getName() + DICT_TEXT_SUFFIX, textValue);
                        }

                    }
                    if (!field.getName().equals("serialVersionUID") && flag) {
                        resultJson.put(field.getName(), tStudentEntity.getClass().getMethod(
                                "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1)).invoke(tStudentEntity));
                    }
                    //date类型默认转换string格式化日期
                    if (field.getType().getName().equals("java.util.Date") && field.getAnnotation(JsonFormat.class) == null && resultJson.get(field.getName()) != null) {
                        SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        resultJson.put(field.getName(), aDate.format(resultJson.get(field.getName())));
                    }
                }
                items.add(resultJson);
            }
            pageUtils.setList(items);
        }
    }
}