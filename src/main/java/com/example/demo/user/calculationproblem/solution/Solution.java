package com.example.demo.user.calculationproblem.solution;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    public static String convert(String s, int numRows) {

        if (numRows < 1 || numRows > 1000) {
            return "请按照数量范围输入行数！！！";
        }
        Map<Integer, List<String>> map = new HashMap<>();
        List<String> strings = new ArrayList<>();
        List<String> stringList = getStrings(s);

        int size = stringList.size();

        int n = numRows * 2 - 2;
        if (n == 0) {
            return s;
        }
        for (int i = 0; i < size; i++) {

            int j = i % n;

            if (j < numRows){
                if (i < numRows) {
                    if (0 != map.size()) {
                        for (Integer key : map.keySet()) {
                            map.get(key).add(" ");
                        }
                    }
                    List<String> list = new ArrayList<>();
                    list.add(stringList.get(i));
                    map.put(i, list);
                }else {
                    for (Integer key : map.keySet()) {
                        if (key == j) {
                            map.get(key).add(stringList.get(i));
                        }else {
                            map.get(key).add(" ");
                        }
                    }
                }
            }else {
                int z = n - j;
                for (Integer key : map.keySet()) {
                    if (key == z) {
                        map.get(key).add(stringList.get(i));
                    }else {
                        map.get(key).add(" ");
                    }
                }

            }

        }
        System.out.println("转换前：");
        for (Integer key : map.keySet()) {
            StringBuilder sb = new StringBuilder();
            List<String> list = map.get(key);
            for (String s1 : list) {
                sb.append(s1);
            }
            System.out.println(sb.toString());
            strings.add(sb.toString());
        }
        System.out.println("转换后：");
        StringBuilder sb1 = new StringBuilder();
        for (String string : strings) {
            List<String> strings1 = getStrings(string);
            for (String s1 : strings1) {
                if (StringUtils.isNotBlank(s1)) {
                    sb1.append(s1);
                }
            }
        }
        System.out.println(sb1.toString());
        return sb1.toString();
    }

    private static List<String> getStrings(String s) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int j = s.length() - 1;
            if (j == 0){
                stringList.add(s);
                break;
            }
            if (i < j) {
                stringList.add(s.substring(i, i + 1));
            }else {
                stringList.add(s.substring(j));
            }
        }
        return stringList;
    }

    public static void main(String[] args) {
        convert("RGUWRHYVJDFMEENERWXHYYBRWBRKICZASR.KAEWUIXUIN,RMUEDESD.CMUHNIRXRYR,XAAERWNZEFYRVBYRBYBHHU.EGNDEMRAJRDWWJRUUWDMGKYIMHEFIEXNVWAUBZWYWRZRXEUERXKSYUHBCRRRND,IRAJMINEEMR.CDYGWSBWHAYRXXDVEY.MRBWHVZUXRFABKNIEGHFCUUMRWRURJ,YIADNEE.SY", 20);
//        convert("A", 1);
//        int i = 20;
//        int n = 6;
//        for (int j = 0; j < i; j++) {
//            System.out.println(j%n);
//            System.out.println("==========="+ j +"=========");
//        }
    }
}
