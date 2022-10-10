package com.example.demo.user.face.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ByteArrayUtils<T> {

    /**
     * 判断文件或者base64是否为空(字节码)
     * @param file
     * @param image
     * @return
     * @throws IOException
     */
        public static byte[] arrayOfByteMethod(MultipartFile file, String image) throws IOException {
        if (null != file) {
            return file.getBytes();
        }else {
            return Base64Util.base64ToBytes(image);
        }
    }

    /**
     * 判断文件或者base64是否为空(流)
     * @param file
     * @param image
     * @return
     * @throws IOException
     */
    public static InputStream arrayOfStreamMethod(MultipartFile file, String image) throws IOException {
        if (null != file) {
            return file.getInputStream();
        }else {
            return new ByteArrayInputStream(Base64Util.base64ToBytes(image));
        }
    }

    public List<T> judgeMethod(T t1, T t2) {
        List<T> ts = new ArrayList<>();
        if (null != t1) {
            ts.add(t1);
        }
        if (null != t2) {
            ts.add(t2);
        }
        return ts;
    }
}
