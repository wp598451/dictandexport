package com.example.demo.user;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.cnsugar.ai.face.bean.FaceIndex;
import com.example.demo.user.faceImg.entity.FaceImgEntity;
import com.example.demo.user.faceImg.service.FaceImgService;
import com.example.demo.user.faceIndex.entity.FaceIndexEntity;
import com.example.demo.user.faceIndex.service.FaceIndexService;
import com.seetaface.SeetaFace6JNI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @Author Sugar
 * @Version 2019/4/22 14:28
 */
public class SeetafaceBuilderDemo {
    private static Logger logger = LoggerFactory.getLogger(SeetafaceBuilderDemo.class);
    private volatile static SeetaFace6JNI seeta = null;

    private static final FaceIndexService faceIndexService = SpringUtil.getBean(FaceIndexService.class);

    private static final FaceImgService faceImgService = SpringUtil.getBean(FaceImgService.class);

    public enum FacedbStatus {
        READY, LOADING, OK, INACTIV;
    }

    private volatile static FacedbStatus face_db_status = FacedbStatus.READY;

    public static SeetaFace6JNI build() {
        if (seeta == null) {
            synchronized (SeetafaceBuilderDemo.class) {
                if (seeta != null) {
                    return seeta;
                }
                init();
            }
        }
        return seeta;
    }

    /**
     * 返回人脸数据库状态
     * @return
     */
    public static FacedbStatus getFaceDbStatus() {
        return face_db_status;
    }

    private static void init() {
        Properties prop = getConfig();
        String separator = System.getProperty("path.separator");
        String sysLib = System.getProperty("java.library.path");
        if (sysLib.endsWith(separator)) {
            System.setProperty("java.library.path", sysLib + prop.getProperty("libs.path", ""));
        } else {
            System.setProperty("java.library.path", sysLib + separator + prop.getProperty("libs.path", ""));
        }
        try {//使java.library.path生效
            Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
            sysPathsField.setAccessible(true);
            sysPathsField.set(null, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        String[] libs = prop.getProperty("libs", "").split(",");
        for (String lib : libs) {
            logger.debug("load library: {}", lib);
            System.loadLibrary(lib.trim());
        }
        String bindata = prop.getProperty("bindata.dir");
        logger.debug("bindata dir: {}", bindata);
        seeta = new SeetaFace6JNI();
        seeta.initModel(bindata);
        String db_file = prop.getProperty("sqlite.db.file");
//        if (db_file != null) {
//            System.setProperty("seetaface.db", db_file);
//            System.setProperty(JdbcPool.MAX_TOTAL, prop.getProperty(JdbcPool.MAX_TOTAL));
//            System.setProperty(JdbcPool.MAX_IDLE, prop.getProperty(JdbcPool.MAX_IDLE));
//            System.setProperty(JdbcPool.MIN_IDLE, prop.getProperty(JdbcPool.MIN_IDLE));
//            System.setProperty(JdbcPool.MAX_WAIT_MILLIS, prop.getProperty(JdbcPool.MAX_WAIT_MILLIS));

        new Thread(() -> loadFaceDb()).start();
//        } else {
//            face_db_status = FacedbStatus.INACTIV;
//            logger.warn("没有配置sqlite.db.file，人脸注册(register)及人脸搜索(1 v N)功能将无法使用!!!");
//        }
        logger.info("Seetaface init completed!!!");
    }

    /**
     * 加载人脸库
     */
    private static void loadFaceDb() {
        if (face_db_status != FacedbStatus.READY) {
            return;
        }

//        if (System.getProperty("seetaface.db") == null) {
//            face_db_status = FacedbStatus.INACTIV;
//            logger.error("没有配置sqlite.db.file!!!");
//            return;
//        }

        face_db_status = FacedbStatus.LOADING;
        logger.info("load face data...");
        faceIndexService.cleanIndex();
//        FaceDao.clearIndex();
        int pageNo = 0, pageSize = 100;
        while (true) {
            List<FaceImgEntity> list = faceImgService.findFaceImgs();
            List<FaceIndex> faceIndexs = new ArrayList<>();
            list.forEach(face -> {
                FaceIndex faceIndex = BeanUtil.toBean(face, FaceIndex.class);
                faceIndex.setKey(face.getId());
                faceIndexs.add(faceIndex);
            });
            if (faceIndexs.size() == 0) {
                break;
            }
            faceIndexs.forEach(face -> {
                try {
                    register(face.getKey(), face);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            if (list.size() < pageSize) {
                break;
            }
            pageNo++;
        }
        face_db_status = FacedbStatus.OK;
    }

    /**
     * 将历史注册过的所有人脸重新加载到内存库中
     *
     * @param key  人脸照片唯一标识
     * @param face 人脸照片
     * @return
     * @throws IOException
     */
    private static void register(String key, FaceIndex face) {
        long index = seeta.registerCroppedFace(face.getImgData());
        if (index < 0) {
            logger.info("Register face fail: key={}, index={}", key, index);
            return;
        }
        FaceIndex faceIndex = new FaceIndex();
        faceIndex.setKey(key);
        faceIndex.setIndex(index);
//        FaceDao.saveOrUpdateIndex(faceIndex);
        FaceIndexEntity faceIndexEntity = new FaceIndexEntity();
        faceIndexEntity.setIdIndex(String.valueOf(faceIndex.getIndex()));
        faceIndexEntity.setId(faceIndex.getKey());
        faceIndexService.insertOrUpdate(faceIndexEntity);
        logger.info("Register face success: key={}, index={}", key, index);
    }

    private static Properties getConfig() {
        Properties properties = new Properties();
        String location = "classpath:/seetaface.properties";
        try (InputStream is = new DefaultResourceLoader().getResource(location).getInputStream()) {
            properties.load(is);
            logger.debug("seetaface config: {}", properties.toString());
        } catch (IOException ex) {
            logger.error("Could not load property file:" + location, ex);
        }
        return properties;
    }
}
