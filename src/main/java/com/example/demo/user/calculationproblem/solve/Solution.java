package com.example.demo.user.calculationproblem.solve;

import java.util.*;
import java.util.stream.Collectors;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 计算两个数之和
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    /*public static String solve (String s, String t) {
        // write code here
        
        if (s.length() > Math.pow(10, 5) || t.length() > Math.pow(10, 5)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();

        String substring1 = t.substring(0, t.length() - s.length());
        String substring2 = t.substring(t.length() - s.length());
        Long substring = Long.valueOf(substring2);
        Long aLong = Long.valueOf(s);
        Long l = substring + aLong;
        String s1 = String.valueOf(l);
        sb.append(substring1);
        if (s1.length() > substring2.length()) {
            // 第几次调用递归
            int i = 0;

            // 截取字符串长度
            int param = substring1.length() - 1;
            solveMethod(param, sb, i);
            sb.append(s1.substring(1));
        }else {
            sb.append(s1);
        }
        System.out.println(sb.toString());
        System.out.println(sb.length());
        return sb.toString();
    }

    private static void solveMethod(int param, StringBuilder sb, int i) {

        if (sb.length() == 0) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    sb.append(1);
                    continue;
                }
                sb.append(0);
            }
            return;
        }
        // 将最后一位置为int类型
        int start = Integer.parseInt(sb.substring(param));
        if (start + 1 != 10 || param < 0) {
            return;
        }

        sb.deleteCharAt(param);
        i++;
        param--;
        solveMethod(param, sb, i);

    }*/

    public static void main(String[] args) {
//        String s = "9";
//        String t = "99999999999999999999999999999999999999999999999999999999999994";
        String s = "1258994789086810959258888307221656691275942378416703768";
        String t = "7007001981958278066472683068554254815482631701142544497";
//        String s = "733064366";
//        String t = "459309139";
        System.out.println(t.length());
        solve(s, t);
    }

    public static String solve (String s, String t) {

        int[] ss = new int[s.length()];
        int[] ts = new int[t.length()];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            ss[i] = Integer.parseInt(s.substring(i, i+1));
        }

        for (int i = 0; i < t.length(); i++) {
            ts[i] = Integer.parseInt(t.substring(i, i+1));
        }

        solveMethod(ss, ts, ss.length - 1, ts.length - 1);
        /*if (ss.length - 1 >= ts.length - 1) {
            Arrays.stream(ss).boxed().collect(Collectors.toList()).forEach(sb::append);
        }else {
            Arrays.stream(ts).boxed().collect(Collectors.toList()).forEach(sb::append);
        }*/
        if (ss.length - 1 >= ts.length - 1) {
            for (int j : ss) {
                sb.append(j);
            }
        }else {
            for (int j : ts) {
                sb.append(j);
            }
        }
//        System.out.println(sb.toString());
        return sb.toString();

    }

    private static void solveMethod(int[] ss, int[] ts, int ssInt, int tsInt) {
        if (ssInt < 0 || tsInt < 0) {
            return;
        }

        int result = ss[ssInt] + ts[tsInt];
        if (result >= 10 && ssInt > 0 && tsInt > 0) {

            // 判断哪个长度大 s长度大，则在s对应元素加1,原元素减10，否则在t对应元素加1,原元素减10
            if (ssInt >= tsInt) {
                ss[ssInt] = result;
                ss[ssInt - 1] = ss[ssInt - 1] + 1;
                ss[ssInt] = ss[ssInt] - 10;
                if (ts[tsInt - 1] >= 10) {
                    solveMethodSs(ss, ssInt);
                }
            } else {
                ts[tsInt] = result;
                ts[tsInt - 1] = ts[tsInt - 1] + 1;
                ts[tsInt] = ts[tsInt] - 10;
                if (ts[tsInt - 1] >= 10) {
                    solveMethodTs(ts, tsInt);
                }
            }
        }else {
            if (ssInt >= tsInt) {
                ss[ssInt] = result;
                if (ss[ssInt] >= 10) {
                    solveMethodSs(ss, ++ssInt);
                }
            }else {
                ts[tsInt] = result;
                if (ts[tsInt] >= 10) {
                    solveMethodTs(ts, ++tsInt);
                }
            }
        }

        ssInt--;
        tsInt--;

        solveMethod(ss, ts, ssInt, tsInt);
    }

    private static void solveMethodSs(int[] ss, int i) {
        i--;
        if (ss[i] < 10 || i == 0) {
            return;
        }

        ss[i - 1] = ss[i - 1] + 1;
        ss[i] = ss[i] - 10;
        solveMethodTs(ss, i);
    }

    private static void solveMethodTs(int[] ts, int i) {
        i--;
        if (ts[i] < 10 || i == 0) {
            return;
        }

        ts[i - 1] = ts[i - 1] + 1;
        ts[i] = ts[i] - 10;
        solveMethodTs(ts, i);
    }
}