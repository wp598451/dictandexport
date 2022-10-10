package com.example.demo.user.face.controller;


import com.cnsugar.ai.face.bean.Result;
import com.cnsugar.ai.face.dao.FaceDao;
import com.example.demo.user.FaceHelperDemo;
import com.example.demo.user.common.utils.R;
import com.example.demo.user.face.util.Base64Util;
import com.example.demo.user.face.util.ByteArrayUtils;
import com.example.demo.user.faceIndex.dao.FaceIndexDao;
import com.example.demo.user.userfaceinfo.entity.EclassUserFaceInfoEntity;
import com.seetaface.model.FaceAntiSpoofingStatus;
import com.seetaface.model.FaceLandmark;
import com.seetaface.model.SeetaRect;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("face/identification")
public class IdentificationController {

//    @Autowired
//    private FaceEngineService faceEngineService;

    @Autowired
    private FaceIndexDao faceIndexDao;

    private static final Logger logger = LoggerFactory.getLogger(IdentificationController.class);

    /**
     * 检测人脸
     * @throws IOException
     */
    @PostMapping("/face/detect")
    public R faceDetect(@RequestParam(value = "file", required = false) MultipartFile file,
                        @RequestParam(value = "image", required = false) String image) throws IOException {
        SeetaRect[] rects = FaceHelperDemo.detect(ByteArrayUtils.arrayOfByteMethod(file, image));
        if (rects != null) {
            for (SeetaRect rect : rects) {
                logger.info("x="+rect.x+", y="+rect.y+", width="+rect.width+", height="+rect.height+", score="+rect.score);
            }
        }
        return R.ok().put("data", rects);
    }

    /**
     * 检测人脸并返回每个人脸在图像中的5个特性点坐标
     * @throws IOException
     */
    @PostMapping("/face/detectLandmark")
    public R faceDetectLandmark(@RequestParam(value = "file", required = false) MultipartFile file,
                                @RequestParam(value = "image", required = false) String image) throws IOException {
        FaceLandmark[] landmarks = FaceHelperDemo.detectLandmark(ImageIO.read(ByteArrayUtils.arrayOfStreamMethod(file, image)));
        if (landmarks != null) {
            for (FaceLandmark landmark : landmarks) {
                logger.info(landmark.toString());
            }
        }
        return R.ok().put("data", landmarks);
    }

    /**
     * 提取人脸区域特性
     * @throws IOException
     */
    @PostMapping("/face/extractCroppedFace")
    public R ExtractCroppedFace(@RequestParam(value = "file", required = false) MultipartFile file,
                                @RequestParam(value = "image", required = false) String image) throws IOException {
        byte[][] faces = FaceHelperDemo.cropFace(ImageIO.read(ByteArrayUtils.arrayOfStreamMethod(file, image)));
        if (faces == null) {
            return R.error("无法确定人脸！！！");
        }
        List<float[]> result = new ArrayList<>();
        for (byte[] face : faces) {
            float[] features = FaceHelperDemo.extractCroppedFace(face);
            if (features != null) {
                logger.info(Arrays.toString(features));
                result.add(features);
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * 提取一个图像中最大人脸的特征
     * @throws IOException
     */
    @PostMapping("/face/extractMaxFace")
    public R ExtractMaxFace(@RequestParam(value = "file", required = false) MultipartFile file,
                            @RequestParam(value = "image", required = false) String image) throws IOException {
        float[] features = FaceHelperDemo.extractMaxFace(ImageIO.read(ByteArrayUtils.arrayOfStreamMethod(file, image)));
        if (features != null) {
            logger.info(Arrays.toString(features));
        }
        return R.ok().put("data", features);
    }

    /**
     * 计算两个特性的相似度
     * @throws IOException
     */
    @PostMapping("/face/calculateSimilarity")
    public R faceCalculateSimilarity(@RequestParam(value = "file1", required = false) MultipartFile file1,
                                     @RequestParam(value = "file2", required = false) MultipartFile file2,
                                     @RequestParam(value = "image1", required = false) String image1,
                                     @RequestParam(value = "image2", required = false) String image2) throws IOException {
        ByteArrayUtils<MultipartFile> fileByteMultipartFile = new ByteArrayUtils<>();
        ByteArrayUtils<String> fileByteString = new ByteArrayUtils<>();
        List<MultipartFile> files = fileByteMultipartFile.judgeMethod(file1, file2);
        List<String> images = fileByteString.judgeMethod(image1, image2);

        if (files.size() + images.size() != 2) {
            return R.error("请放入两张照片！！！");
        }
        byte[] face1 = null;
        byte[] face2 = null;

        if (files.size() == 2) {
            face1 = FaceHelperDemo.cropFace(ImageIO.read(file1.getInputStream()))[0];
            face2 = FaceHelperDemo.cropFace(ImageIO.read(file2.getInputStream()))[0];
        }else if (files.size() == 1) {
            face1 = FaceHelperDemo.cropFace(ImageIO.read(files.get(0).getInputStream()))[0];
            face2 = FaceHelperDemo.cropFace(ImageIO.read(new ByteArrayInputStream(Objects.requireNonNull(Base64Util.base64ToBytes(images.get(0))))))[0];
        }else{
            face1 = FaceHelperDemo.cropFace(ImageIO.read(new ByteArrayInputStream(Objects.requireNonNull(Base64Util.base64ToBytes(images.get(0))))))[0];
            face2 = FaceHelperDemo.cropFace(ImageIO.read(new ByteArrayInputStream(Objects.requireNonNull(Base64Util.base64ToBytes(images.get(1))))))[0];
        }
        Map<String, Object> result = new LinkedHashMap<>();

        assert face1 != null;
        float[] features1 = FaceHelperDemo.extractCroppedFace(face1);
        float[] features2 = FaceHelperDemo.extractCroppedFace(face2);

        logger.info("features1: "+Arrays.toString(features1));
        logger.info("features2: "+Arrays.toString(features2));
        logger.info("result:" + FaceHelperDemo.calculateSimilarity(features1, features2));
        result.put("result", FaceHelperDemo.calculateSimilarity(features1, features2));
        result.put("features1", Arrays.toString(features1));
        result.put("features2", Arrays.toString(features2));
        return R.ok().put("data", result);
    }

    /**
     * 人脸对比
     * @throws Exception
     */
    @PostMapping("/face/compare")
    public R faceCompare(@RequestParam(value = "file1", required = false) MultipartFile file1,
                         @RequestParam(value = "file2", required = false) MultipartFile file2,
                         @RequestParam(value = "image1", required = false) String image1,
                         @RequestParam(value = "image2", required = false) String image2) throws Exception {
        ByteArrayUtils<MultipartFile> fileByteMultipartFile = new ByteArrayUtils<>();
        ByteArrayUtils<String> fileByteString = new ByteArrayUtils<>();
        List<MultipartFile> files = fileByteMultipartFile.judgeMethod(file1, file2);
        List<String> images = fileByteString.judgeMethod(image1, image2);

        if (files.size() + images.size() != 2) {
            return R.error("请放入两张照片！！！");
        }

        byte[] face1 = null;
        byte[] face2 = null;

        if (files.size() == 2) {
            face1 = file1.getBytes();
            face2 = file2.getBytes();
        }else if (files.size() == 1) {
            face1 = files.get(0).getBytes();
            face2 = Base64Util.base64ToBytes(images.get(0));
        }else{
            face1 = Base64Util.base64ToBytes(images.get(0));
            face2 = Base64Util.base64ToBytes(images.get(1));
        }

        logger.info("result:" + FaceHelperDemo.compare(face1, face2));
        return R.ok().put("data", FaceHelperDemo.compare(face1, face2));
    }

    /**
     * 查询人脸信息
     * @throws IOException
     */
    @PostMapping("/face/query")
    public R faceQuery(@RequestParam(value = "file", required = false) MultipartFile file,
                       @RequestParam(value = "image", required = false) String image) throws IOException {
        Result result = FaceHelperDemo.search(ByteArrayUtils.arrayOfByteMethod(file, image));
        logger.info("搜索结果：" + result);
        return R.ok().put("data", result);
    }

    /**
     * 注册人脸信息
     * @throws IOException
     */
    @PostMapping("/face/register")
    public R faceRegister(@RequestBody Map<String,Object> map) throws Exception {

        List<String> files = (List<String>) map.get("files");
        List<EclassUserFaceInfoEntity > userFaces = faceIndexDao.getUserFaces();
        if (userFaces.size() == files.size()) {
            int i = 0, j = 0;
            while (i < userFaces.size()) {
                String key = userFaces.get(i).getFaceId().toString();
                FaceHelperDemo.register(key, Base64Util.base64ToBytes(files.get(j)));
                i++;
                j++;
            }
        }
        return R.ok();
    }


    /** *//**
     * 文件读取缓冲区大小
     */
    private static final int CACHE_SIZE = 1024;

    public static byte[] fileToByte(String filePath) throws Exception {
        byte[] data = new byte[0];
        File file = new File(filePath);
        if (file.exists()) {
            FileInputStream in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
            byte[] cache = new byte[CACHE_SIZE];
            int nRead = 0;
            while ((nRead = in.read(cache)) != -1) {
                out.write(cache, 0, nRead);
                out.flush();
            }
            out.close();
            in.close();
            data = out.toByteArray();
        }
        return data;
    }
    /**
     * 搜索人脸信息耗时
     * @throws IOException
     */
    @PostMapping("/face/search")
    public R faceSearch(@RequestParam(value = "file", required = false) MultipartFile file,
                        @RequestParam(value = "image", required = false) String image) throws IOException {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        long l = System.currentTimeMillis();
        Result result = FaceHelperDemo.search(ByteArrayUtils.arrayOfByteMethod(file, image));
        logger.info("搜索结果：" + result + "， 耗时：" + (System.currentTimeMillis() - l));
        resultMap.put("result", result);
        resultMap.put("time", System.currentTimeMillis() - l);
        return R.ok().put("data", resultMap);
    }

    /**
     * 复制图片
     * @throws IOException
     */
    @PostMapping("/face/corp")
    public void faceCorp(@RequestParam(value = "file", required = false) MultipartFile file,
                      @RequestParam(value = "image", required = false) String imageStr,
                      HttpServletResponse response) throws IOException {
        BufferedImage[] images = FaceHelperDemo.crop(ByteArrayUtils.arrayOfByteMethod(file, imageStr));
        if (images != null) {
            for (BufferedImage image : images) {
                ImageIO.write(image, "jpg", response.getOutputStream());
            }
        }
    }

    /**
     * 删除注册图片
     */
    @DeleteMapping("/face/delete")
    public R faceDelete(@Validated String... keys) {
        FaceHelperDemo.removeRegister(keys);
        return R.ok();
    }

    /**
     * 查询图片类型
     * @throws IOException
     */
    @PostMapping("/face/predictImage")
    public R facePredictImage(@RequestParam(value = "file", required = false) MultipartFile file,
                              @RequestParam(value = "image", required = false) String image) throws IOException {
        FaceAntiSpoofingStatus status = FaceHelperDemo.predictImage(ByteArrayUtils.arrayOfByteMethod(file, image));
        logger.info("status" + status);
        return R.ok().put("data", status);
    }
    /**
     * 根据人脸特征匹配人脸信息
     * @throws IOException
     */
   /* @GetMapping("/query/user/info")
    public R queryByFeature(@Validated String faceId){
        UserFaceInfo userFaceInfo = faceEngineService.getUserFaceInfo(faceId);
        float[] s = DataUtils.stringToGson(userFaceInfo.getFeatureFloat());
        return R.ok().put("data", s);
    }*/

    /**
     * 根据人脸特征匹配人脸信息
     * @throws IOException
     */
    @PostMapping("/getUserList")
    public R getUserList(@RequestBody Map<String, Object> map) {
        String keyByIndex = FaceDao.findKeyByIndex(Integer.parseInt(String.valueOf(map.get("index"))));
        return R.ok().put("key", keyByIndex);
    }

    /**
     * 根据人脸特征匹配人脸信息
     * @throws IOException
     */
    @PostMapping("/getFaceFeature")
    public R queryByFeature(@RequestBody Map<String, Object> map) throws IOException {
        LinkedHashMap<String, Object> file = (LinkedHashMap<String, Object>) map.get("file");
        String bytes = (String) file.get("bytes");
        byte[] obj = Base64Util.base64ToBytes(bytes);
        byte[] buf = Objects.requireNonNull(obj);

        float[] features = FaceHelperDemo.extractMaxFace(ImageIO.read(new ByteArrayInputStream(buf)));
        return R.ok().put("data", features);
    }

    /**
     * 注册單獨的人脸信息
     * @throws IOException
     */
    @PostMapping("/face/registerById")
    public R faceRegisterById(@RequestBody Map<String,Object> map) throws Exception {
        Object faceId1 = map.get("faceId");
        String s = String.valueOf(faceId1);
        Long faceId = Long.valueOf(s);
        String key = String.valueOf(faceId);
        String bytes = (String) map.get("bytes");
        FaceHelperDemo.register(key, Base64Util.base64ToBytes(bytes));
        return R.ok();

    }

    /**
     * 清空人脸信息
     * @throws IOException
     */
    @PostMapping("/face/clean")
    public R faceClean(@RequestBody Map<String, Object> map) {
        FaceHelperDemo.clear();

        return R.ok();
    }
}
