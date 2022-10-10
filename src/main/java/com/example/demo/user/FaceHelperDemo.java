package com.example.demo.user;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cnsugar.ai.face.FaceHelper;
import com.cnsugar.ai.face.bean.FaceIndex;
import com.cnsugar.ai.face.bean.Result;
import com.cnsugar.ai.face.utils.DataUtils;
import com.cnsugar.ai.face.utils.ImageUtils;
import com.example.demo.user.common.utils.SnowflakeIdWorker;
import com.example.demo.user.faceImg.entity.FaceImgEntity;
import com.example.demo.user.faceImg.service.FaceImgService;
import com.example.demo.user.faceIndex.entity.FaceIndexEntity;
import com.example.demo.user.faceIndex.service.FaceIndexService;
import com.seetaface.SeetaFace6JNI;
import com.seetaface.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FaceHelperDemo extends FaceHelper {

    private static Logger logger = LoggerFactory.getLogger(FaceHelperDemo.class);

    private static int CROP_SIZE = 256 * 256 * 3;

    private final static SeetaFace6JNI seeta = SeetafaceBuilderDemo.build();

    private static final FaceIndexService faceIndexService = SpringUtil.getBean(FaceIndexService.class);

    private static final FaceImgService faceImgService = SpringUtil.getBean(FaceImgService.class);

    static {
        ImageIO.setUseCache(false);
    }

    /**
     * 人脸比对
     *
     * @param img1
     * @param img2
     * @return 相似度
     */
    public static float compare(File img1, File img2) {
        BufferedImage image1 = null;
        BufferedImage image2 = null;
        try {
            image1 = ImageIO.read(img1);
            image2 = ImageIO.read(img2);

            return compare(image1, image2);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return -1;
        } finally {
            if (image1 != null) {
                image1.getGraphics().dispose();
            }
            if (image2 != null) {
                image2.getGraphics().dispose();
            }
        }
    }

    /**
     * 人脸比对
     *
     * @param img1
     * @param img2
     * @return 相似度
     */
    public static float compare(byte[] img1, byte[] img2) {
        BufferedImage image1 = null;
        BufferedImage image2 = null;
        try {
            image1 = ImageIO.read(new ByteArrayInputStream(img1));
            image2 = ImageIO.read(new ByteArrayInputStream(img2));

            return compare(image1, image2);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return -1;
        } finally {
            if (image1 != null) {
                image1.getGraphics().dispose();
            }
            if (image2 != null) {
                image2.getGraphics().dispose();
            }
        }
    }

    /**
     * `
     * 人脸比对
     *
     * @param image1
     * @param image2
     * @return 相似度
     */
    public static float compare(BufferedImage image1, BufferedImage image2) {
        if (image1 == null || image2 == null) {
            return 0;
        }
        SeetaImageData imageData1 = new SeetaImageData(image1.getWidth(), image1.getHeight(), 3);
        imageData1.data = ImageUtils.getMatrixBGR(image1);

        SeetaImageData imageData2 = new SeetaImageData(image2.getWidth(), image2.getHeight(), 3);
        imageData2.data = ImageUtils.getMatrixBGR(image2);

        return seeta.compare(imageData1, imageData2);
    }

    /**
     * 注册人脸(会对人脸进行裁剪)
     *
     * @param key   人脸照片唯一标识
     * @param image 人脸照片
     * @return
     */
    public static boolean register(String key, BufferedImage image) {
        //先对人脸进行裁剪
        SeetaImageData imageData = new SeetaImageData(image.getWidth(), image.getHeight(), 3);
        imageData.data = ImageUtils.getMatrixBGR(image);
        byte[][] bytes = seeta.crop(imageData);

        if (bytes == null || bytes.length == 0) {
            logger.info("register face fail: key={}, error=no valid face", key);
            return false;
        }
        long index = registerCroppedFace(bytes[0]);
        if (index < 0) {
            logger.info("register face fail: key={}, index={}", key, index);
            return false;
        }
        FaceIndex face = new FaceIndex();
        face.setKey(key);
        face.setImgData(bytes[0]);
        face.setIndex(index);
//        FaceDao.saveOrUpdate(face);
        FaceImgEntity faceImgEntity = new FaceImgEntity();
        FaceIndexEntity faceIndexEntity = new FaceIndexEntity();
        BeanUtils.copyProperties(face, faceImgEntity);
        BeanUtils.copyProperties(face, faceIndexEntity);

        FaceImgEntity faceImgEntity1 = faceImgService.selectById(key);
        if (faceImgEntity1 == null) {
            faceImgEntity.setId(key);
            faceImgService.insert(faceImgEntity);
        }else {
            faceImgService.updateById(faceImgEntity);
        }

        FaceIndexEntity faceIndexEntity1 = faceIndexService.selectById(index);
        if (faceIndexEntity1 == null) {
            faceIndexEntity.setIdIndex(String.valueOf(index));
            faceIndexEntity.setId(key);
            faceIndexService.insert(faceIndexEntity);
        }else {
            faceIndexService.updateById(faceIndexEntity);
        }
        logger.info("Register face success: key={}, index={}", key, index);
        return true;
    }

    /**
     * 注册人脸(会对人脸进行裁剪)
     * @param key
     * @param img
     * @return
     */
    public static boolean register(String key, byte[] img) {
        BufferedImage image = null;
        try (InputStream in = new ByteArrayInputStream(img)) {
            image = ImageIO.read(in);
            return register(key, image);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        } finally {
            if (image != null) {
                image.getGraphics().dispose();
            }
        }
    }

    /**
     * 注册人脸
     *
     * @param image 人脸照片
     * @return
     */
    public static long register(BufferedImage image) {
        SeetaImageData imageData = new SeetaImageData(image.getWidth(), image.getHeight(), 3);
        imageData.data = ImageUtils.getMatrixBGR(image);
        return seeta.register(imageData);
    }

    /**
     * 注册裁剪过的人脸
     *
     * @param croppedFace 裁剪过的人脸照片
     * @return
     */
    public static long registerCroppedFace(byte[] croppedFace) {
        if (croppedFace == null || croppedFace.length != CROP_SIZE) {
            logger.error("参数无效，必须为裁剪后的人脸照片，大小应该为{}，实际为：{}", CROP_SIZE, croppedFace.length);
            return -1;
        }
        return seeta.registerCroppedFace(croppedFace);
    }

    /**
     * 搜索人脸
     *
     * @param img 人脸照片
     * @return
     */
    public static Result search(byte[] img) {
        BufferedImage image = null;
        try (InputStream in = new ByteArrayInputStream(img)) {
            image = ImageIO.read(in);
            return search(image);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            if (image != null) {
                image.getGraphics().dispose();
            }
        }
    }

    /**
     * 搜索人脸
     *
     * @param image 人脸照片
     * @return
     */
    public static Result search(BufferedImage image) {
        if (image == null) {
            return null;
        }
        SeetaImageData imageData = new SeetaImageData(image.getWidth(), image.getHeight(), 3);
        imageData.data = ImageUtils.getMatrixBGR(image);
        RecognizeResult rr = seeta.query(imageData);
        return toResult(rr);
    }

    /**
     * 用裁剪后的人脸搜索
     * @param croppedFace
     * @return
     */
    public static Result searchByCroppedFace(byte[] croppedFace) {
        if (croppedFace == null || croppedFace.length != CROP_SIZE) {
            return null;
        }
        RecognizeResult rr = seeta.queryByCroppedFace(croppedFace);
        return toResult(rr);
    }

    private static Result toResult(RecognizeResult rr) {
        if (rr == null || rr.index == -1) {
            return null;
        }
        Result result = new Result(rr);

        result.setKey(faceIndexService.findKeyByIndex(rr.index));
        return result;
    }

    /**
     * 人脸提取（裁剪）
     *
     * @param image
     * @return return cropped face
     */
    public static byte[][] cropFace(BufferedImage image) {
        if (image == null) {
            return null;
        }
        SeetaImageData imageData = new SeetaImageData(image.getWidth(), image.getHeight(), 3);
        imageData.data = ImageUtils.getMatrixBGR(image);
        return seeta.crop(imageData);
    }

    public static byte[][] cropFace(byte[] img) {
        BufferedImage image = null;
        try (InputStream in = new ByteArrayInputStream(img)) {
            image = ImageIO.read(in);
            SeetaImageData imageData = new SeetaImageData(image.getWidth(), image.getHeight(), 3);
            imageData.data = ImageUtils.getMatrixBGR(image);
            return seeta.crop(imageData);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            if (image != null) {
                image.getGraphics().dispose();
            }
        }
    }

    /**
     * 注册人脸(会对人脸进行裁剪)
     * @param key
     * @param data
     * @return
     */
    public static boolean register(String key, float[] data) {
        BufferedImage image = null;
        try (InputStream in = new ByteArrayInputStream(DataUtils.floatArrayToByteArray(data))) {
            image = ImageIO.read(in);
            return register(key, image);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        } finally {
            if (image != null) {
                image.getGraphics().dispose();
            }
        }
    }

    /**
     * 人脸提取（裁剪）
     *
     * @param image
     * @return return cropped face
     */
    public static BufferedImage[] crop(BufferedImage image) {
        byte[][] bytes = cropFace(image);
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        BufferedImage[] images = new BufferedImage[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            images[i] = ImageUtils.bgrToBufferedImage(bytes[i], 256, 256);
        }
        return images;
    }

    /**
     * 人脸提取（裁剪）
     *
     * @param img
     * @return return cropped face
     */
    public static BufferedImage[] crop(byte[] img) {
        BufferedImage image = null;
        try (InputStream in = new ByteArrayInputStream(img)) {
            image = ImageIO.read(in);
            return crop(image);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            if (image != null) {
                image.getGraphics().dispose();
            }
        }
    }

    /**
     * 人脸识别
     *
     * @param img
     * @return
     */
    public static SeetaRect[] detect(byte[] img) {
        BufferedImage image = null;
        try (InputStream in = new ByteArrayInputStream(img)) {
            image = ImageIO.read(in);
            return detect(image);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            if (image != null) {
                image.getGraphics().dispose();
            }
        }
    }

    /**
     * 人脸识别
     *
     * @param image
     * @return
     */
    public static SeetaRect[] detect(BufferedImage image) {
        if (image == null) {
            return null;
        }
        SeetaImageData imageData = new SeetaImageData(image.getWidth(), image.getHeight(), 3);
        imageData.data = ImageUtils.getMatrixBGR(image);
        return seeta.detect(imageData);
    }

    /**
     * 人脸特征识别
     *
     * @param image
     * @return
     */
    public static FaceLandmark[] detectLandmark(BufferedImage image) {
        if (image == null) {
            return null;
        }
        SeetaImageData imageData = new SeetaImageData(image.getWidth(), image.getHeight(), 3);
        imageData.data = ImageUtils.getMatrixBGR(image);
        SeetaRect[] rects = seeta.detect(imageData);
        if (rects == null) {
            return null;
        }
        FaceLandmark[] faces = new FaceLandmark[rects.length];
        for (int i = 0; i < rects.length; i++) {
            faces[i] = new FaceLandmark(rects[i]);
            faces[i].points = seeta.mark(imageData, rects[i]);
        }
        return faces;
    }

    /**
     * 提取人脸区域特性
     *
     * @param face
     * @return
     */
    public static float[] extractCroppedFace(byte[] face) {
        if (face.length != CROP_SIZE) {
            logger.error("参数无效，必须为裁剪后的人脸照片，大小应该为{}，实际为：{}", CROP_SIZE, face.length);
            return null;
        }
        return seeta.extractCroppedFace(face);
    }

    /**
     * 提取一个图像中最大人脸的特征
     *
     * @param image
     * @return
     */
    public static float[] extractMaxFace(BufferedImage image) {
        SeetaImageData imageData = new SeetaImageData(image.getWidth(), image.getHeight(), 3);
        imageData.data = ImageUtils.getMatrixBGR(image);
        return seeta.extractMaxFace(imageData);
    }

    /**
     * 计算两个特性的相似度
     *
     * @param features1
     * @param features2
     * @return
     */
    public static float calculateSimilarity(float[] features1, float[] features2) {
        return seeta.calculateSimilarity(features1, features2);
    }

    /**
     * 静默图片活体检测
     * REAL = 0,       ///< 真实人脸
     * SPOOF = 1,      ///< 攻击人脸（假人脸）
     * FUZZY = 2,      ///< 无法判断（人脸成像质量不好）
     * DETECTING = 3,  ///< 正在检测
     * @param img
     * @return
     */
    public static FaceAntiSpoofingStatus predictImage(byte[] img) {
        BufferedImage image = null;
        try (InputStream in = new ByteArrayInputStream(img)) {
            image = ImageIO.read(in);
            return predictImage(image);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return null;
        } finally {
            if (image != null) {
                image.getGraphics().dispose();
            }
        }
    }

    /**
     * 静默图片活体检测
     * REAL = 0,       ///< 真实人脸
     * SPOOF = 1,      ///< 攻击人脸（假人脸）
     * FUZZY = 2,      ///< 无法判断（人脸成像质量不好）
     * DETECTING = 3,  ///< 正在检测
     * @param image
     * @return
     */
    public static FaceAntiSpoofingStatus predictImage(BufferedImage image) {
        SeetaImageData imageData = new SeetaImageData(image.getWidth(), image.getHeight(), 3);
        imageData.data = ImageUtils.getMatrixBGR(image);
        int status = seeta.predictImage(imageData);
        switch (status) {
            case 0:
                return FaceAntiSpoofingStatus.REAL;
            case 1:
                return FaceAntiSpoofingStatus.SPOOF;
            case 3:
                return FaceAntiSpoofingStatus.DETECTING;
            default:
                return FaceAntiSpoofingStatus.FUZZY;
        }
    }

    /**
     * 删除已注册的人脸
     *
     * @param keys
     */
    public static long removeRegister(String... keys) {
        List<Long> list = faceIndexService.findIndexes(keys);
        if (list == null) {
            return 0;
        }
        long[] ll = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ll[i] = list.get(i);
        }
        long rows = seeta.delete(ll);
        logger.debug("共删除{}条人脸", rows);
        for (int i = 0; i < keys.length; i++) {
            faceImgService.deleteById(keys[i]);
            faceIndexService.delete(new EntityWrapper<FaceIndexEntity>().eq("key", keys[i]));
        }
//        FaceDao.deleteFaceImg(keys);//删除数据库的人脸
        return rows;
    }

    /**
     * 清除人脸库数据
     */
    public static void clear() {
        long rows = seeta.delete(new long[]{-1});
        logger.debug("共删除{}条人脸", rows);
        faceIndexService.cleanIndex();
        faceImgService.clearImg();
//        FaceDao.clearIndex();
//        FaceDao.clearImg();
    }

    /**
     * 根据人脸特征匹配人脸信息
     *
     * @param features
     * @return
     */
//    public static RegisterData queryByFeature(float[] features) {
//        RegisterData registerData = seeta.queryByFeature(features);
//        return registerData;
//    }
}
