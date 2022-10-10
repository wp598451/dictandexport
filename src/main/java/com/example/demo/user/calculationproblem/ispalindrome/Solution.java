package com.example.demo.user.calculationproblem.ispalindrome;

class Solution {
    public static boolean isPalindrome(int x) {

        if (x < 0) {
            return false;
        }

        StringBuilder stringBuilder = new StringBuilder();
        int def = x;
        while (x != 0) {
            if (x > 214748364) {
                return false;
            }

            int ref = x % 10;
            stringBuilder.append(ref);
            x /= 10;
        }

        int i = Integer.parseInt(stringBuilder.toString());
        if (i == def) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        int x = 214748365;
        System.out.println(isPalindrome(x));
    }
}