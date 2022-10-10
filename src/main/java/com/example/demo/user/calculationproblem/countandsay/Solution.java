package com.example.demo.user.calculationproblem.countandsay;

import java.util.ArrayList;
import java.util.List;

class Solution {

    static List<String> countList = new ArrayList<>();

    static {
        countList.add("1");
        countList.add("11");
        countList.add("21");
        countList.add("1211");
        countList.add("111221");
    }

    public static String countAndSay(int n) {


        if (n < 1 || n > 30) {
            return "请输入正确的整数范围！";
        }

        if (n <= 5) {
            return countList.get(n - 1);
        }

        // 初始化获取最大数组长度
        int i = countList.size();
        StringBuilder sb = null;

        while (i < n) {
            String s = countList.get(i - 1);
            List<String> strs = new ArrayList<>();
            sb = new StringBuilder();
            List<String> middleList = new ArrayList<>();
            for (int j = 0; j < s.length(); j++) {
                String substring = s.substring(j, j + 1);
                strs.add(substring);
            }

            for (int z = 1; z < strs.size(); z++) {
                middleList.add(strs.get(z - 1));
                if (!strs.get(z).equals(strs.get(z - 1))) {
                    sb.append(middleList.size()).append(middleList.get(0));
                    middleList = new ArrayList<>();
                }
                // 当索引为最大时， 保存最后一位数据
                if (z == strs.size() - 1) {
                    middleList.add(strs.get(z));
                    sb.append(middleList.size()).append(middleList.get(0));
                }
            }

            countList.add(sb.toString());
            i++;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        int n = 5;
        countAndSay(n);
    }
}