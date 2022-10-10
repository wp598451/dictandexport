package com.example.demo.user;


import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class test {

    public static void main(String[] args) {
//        String gbk= URLDecoder.decode("%E7%8F%AD%E7%BA%A7");
//        System.out.println(gbk);

        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("1", null);
        resultMap.put("2", "123");
        resultMap.put("3", null);

        System.out.println(resultMap.get("3").toString());
    }
}
