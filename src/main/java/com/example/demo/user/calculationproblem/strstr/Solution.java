package com.example.demo.user.calculationproblem.strstr;

class Solution {
    public static int strStr(String haystack, String needle) {

        if (1 > haystack.length() && needle.length() > Math.pow(10, 4)) {
            return 0;
        }

        int i = 0;
        while (i + needle.length() <= haystack.length()) {
            String substring = haystack.substring(i, i + needle.length());
            if (needle.equals(substring)) {
                return i;
            }
            i++;
        }

        return -1;
    }

    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        strStr(haystack, needle);
    }
}