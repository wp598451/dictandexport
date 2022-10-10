package com.example.demo.user.calculationproblem.mysqrt;

class Solution {
    public static int mySqrt(int x) {
        if (x > Math.pow(2, 32) - 1 || x <= 0) {
            return 0;
        }
        int i = 1;
        // 粗略定位
        while (i * i <= x && i * i >= 0) {
            i += 500;
        }
        i--;
        // 精准定位
        while (i * i > x || i * i < 0) {
            i--;
        }
        System.out.println(i);
        return i;
    }

    public static void main(String[] args) {
        mySqrt(1024102410);
    }
}