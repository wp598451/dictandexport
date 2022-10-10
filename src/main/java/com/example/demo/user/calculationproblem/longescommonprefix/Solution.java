package com.example.demo.user.calculationproblem.longescommonprefix;

class Solution {
    public static String longestCommonPrefix(String[] strs) {
/*
        if (strs.length < 1 || strs.length > 200) {
            return "请输入正确的数组长度!!!";
        }else {
            for (String str : strs) {
                if (str.length() > 200) {
                    return "请输入正确的字符串长度!!!";
                }
            }
        }

        String flag = null;
        flag = strs[0];
        for (int i = 1; i < strs.length; i++) {
            flag = flag.length() > strs[i].length() ? strs[i] : flag;
        }

        StringBuilder sb = new StringBuilder();
        String stri = null;
        String strj = null;
        for (int i = 0; i < flag.length(); i++) {
            stri = strs[0].substring(i, i+1);
            for (String str : strs) {
                strj = str.substring(i, i + 1);
                if (!stri.equals(strj)) {
                    return sb.toString();
                }
            }
            sb.append(stri);
        }
        return sb.toString();*/

        for (int i = 1, j = strs[0].length(); i < strs.length; i++) {
            while (!strs[i].startsWith(strs[0])) {
                strs[0] = strs[0].substring(0, j--);
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        longestCommonPrefix(strs);
    }
}