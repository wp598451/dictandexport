package com.example.demo.user.calculationproblem.reverse;

import org.apache.commons.lang3.StringUtils;

class Solution {
    public static int reverse(int x) {
        int tmp = 0;
        double aLong = (x * -1);
        if (x > 2147483647 || x < -2147483648 || x == 0 || x == -2147483648) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        while (x != 0) {

            if (x < 0) {
                sb.append("-");
                x *= -1;
            }

            int ref = x % 10;
            sb.append(ref);
            x /= 10;
        }
        if (Long.valueOf(sb.toString()) > 2147483647 || Long.valueOf(sb.toString()) < -2147483648) {
            return 0;
        }
        tmp = sb.length() > 0 ? Integer.parseInt(sb.toString()) : 0;
        return tmp;
    }

    public static void main(String[] args) {
//        int x = -3563456;
//        int x = -2147483412;
//        int x = 1534236469;
//        int x = 0;
        int x = -2147483648;
        System.out.println(reverse(x));
    }
}