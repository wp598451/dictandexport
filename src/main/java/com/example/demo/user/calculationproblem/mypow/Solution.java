package com.example.demo.user.calculationproblem.mypow;

import java.text.DecimalFormat;

class Solution {
    public static double myPow(double x, int n) {

        if (x > 100 || x < -100) {
            return 0;
        }

        if (x == 1 || n == 0) {
            return 1;
        }else if (x == -1 && n % 2 != 0) {
            return -1;
        }else if (x == -1 && n % 2 == 0) {
            return 1;
        }

        if (n <= Math.pow(-2, 31) || n >= Math.pow(2, 31) - 1) {
            return 0;
        }

        double z = x;

        if (n > 0) {
            for (int i = 1; i < n; i++) {
                x *= z;
            }
        }else {
            for (int i = 1; i < n * -1; i++) {
                x *= z;
            }
        }

        return n < 0 ? 1 / x : x;
    }

    public static void main(String[] args) {
        double x = 2.00000;
        int n = -2;
        myPow(x, n);
    }
}