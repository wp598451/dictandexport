package com.example.demo.user.calculationproblem.getpermutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    static String[] num1 = {"1"};
    static String[] num2 = {"1", "2"};
    static String[] num3 = {"1", "2", "3"};
    static String[] num4 = {"1", "2", "3", "4"};
    static String[] num5 = {"1", "2", "3", "4", "5"};
    static String[] num6 = {"1", "2", "3", "4", "5", "6"};
    static String[] num7 = {"1", "2", "3", "4", "5", "6", "7"};
    static String[] num8 = {"1", "2", "3", "4", "5", "6", "7", "8"};
    static String[] num9 = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static String getPermutation(int n, int k) {

        StringBuilder sb = new StringBuilder();

        int flag = 1;
        if (n > 9 || n < 0) {
            return "请输入正确的数字范围！";
        }else {
            for (int i = 2; i <= n; i++) {
                flag *= i;
            }

            if (k < 1 || k > flag){
                return "请输入正确的数字！";
            }
        }

        String[] num = null;

        switch (n) {
            case 1: return num1[0];
            case 2: num = num2; break;
            case 3: num = num3; break;
            case 4: num = num4; break;
            case 5: num = num5; break;
            case 6: num = num6; break;
            case 7: num = num7; break;
            case 8: num = num8; break;
            case 9: num = num9; break;
        }

        assert num != null;
        List<String[]> result = new ArrayList<>();
        getPermutationMethod(result, num, 0);

        for (String s : result.get(k)) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void getPermutationMethod(List<String[]> result, String[] num, int start) {

        if (start == num.length - 1) {
            String[] results = new String[num.length];
            for (int i = 0; i < num.length; i++) {
                results[i] = num[i];
            }
            result.add(results);
        }

        for (int i = start; i < num.length; i++) {
            swap(num, i, start);
            getPermutationMethod(result, num, start + 1);
            swap(num, i, start);
        }
    }

    public static void swap(String[] num, int i, int j) {
        String temp;
        temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    public static void main(String[] args) {
        getPermutation(3, 5);
    }
}