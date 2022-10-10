package com.example.demo.user.calculationproblem.strangeprinter;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

class Solution {
    public static int strangePrinter(String s) {

        if (s.length() > 100) {
            return 0;
        }

        Map<String, Integer> map = new LinkedHashMap<>();

        Integer count = null;
        for (int i = 0; i < s.length(); i++) {
            count = 1;
            if (!map.containsKey(String.valueOf(s.charAt(i)))) {
                map.put(String.valueOf(s.charAt(i)), count);
            } else {
                count += map.get(String.valueOf(s.charAt(i)));
                map.put(String.valueOf(s.charAt(i)), count);
            }
        }

        int judge = 0;
        String str1 = null;
        for (String str : map.keySet()) {
            if (map.get(str) > judge) {
                judge = map.get(str);
                str1 = str;
            }
        }
        map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            count = 1;
            if (!String.valueOf(s.charAt(i)).equals(str1)) {
                if (i > 0) {
                    if (!map.containsKey(String.valueOf(s.charAt(i)))) {
                        map.put(String.valueOf(s.charAt(i)), count);
                    } else if (!String.valueOf(s.charAt(i)).equals(String.valueOf(s.charAt(i - 1)))){
                        count += map.get(String.valueOf(s.charAt(i)));
                        map.put(String.valueOf(s.charAt(i)) + i, count);
                    }
                }else {
                    map.put(String.valueOf(s.charAt(i)), count);
                }
            }
        }
        System.out.println(map.size() + 1);
        return map.size() + 1;


    }

    public static void main(String[] args) {
        String s = "123456";
//        strangePrinter(s);
        Integer integer = Integer.valueOf(s);
    }


}