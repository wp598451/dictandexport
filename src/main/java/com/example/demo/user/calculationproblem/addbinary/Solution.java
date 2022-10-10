package com.example.demo.user.calculationproblem.addbinary;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Solution {
    public static String addBinary(String a, String b) {
        int[] as = arrayMethod(a);
        int[] bs = arrayMethod(b);

        // 大数据量的二进制数字转换成十进制会出现溢出现象，故而使用用于存储更大数据的十进制对象BigInteger
        BigInteger divisor = new BigInteger(String.valueOf(2));

        BigInteger numA = numMethod(as, divisor);
        BigInteger numB = numMethod(bs, divisor);

        BigInteger num = numA.add(numB);

        StringBuilder sb = new StringBuilder();
        if (num.compareTo(new BigInteger(String.valueOf(0))) == 0) {
            sb.append(0);
        }else {
            BigInteger remainder = new BigInteger(String.valueOf(0));
            baninaryMetnod(num, remainder, sb, divisor);
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * 根据字符串对象返回数组
     * @param s
     * @return
     */
    public static int[] arrayMethod(String s) {

        int[] arrays = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arrays[i] = s.charAt(i) - '0';
        }

        return arrays;
    }

    /**
     * 根据数组的二进制数据返回十进制数字
     * @param arrays
     * @param divisor
     * @return
     */
    public static BigInteger numMethod(int[] arrays, BigInteger divisor) {
        BigInteger num = new BigInteger(String.valueOf(0));
        int length = arrays.length - 1;
        int j = 0;
        while (j < arrays.length) {

            // 原始的Math.pow()返回double类型，大数据量会返回科学计数数字，造成异常，故而重写次方函数，返回BigInteger对象
            BigInteger pow = new BigInteger(String.valueOf(1));
            for (int i = 0; i < length; i++) {
                pow = pow.multiply(divisor);
            }
            BigInteger bg = new BigInteger(String.valueOf(arrays[j]));
            BigInteger s = new BigInteger(String.valueOf(bg.multiply(pow)));
            num = num.add(s);
            j++;
            length--;
        }

        return num;
    }

    /**
     * 根据相加之和除二递归，返回二进制结果
     * @param num
     * @param remainder
     * @param sb
     * @param divisor
     * @return
     */
    private static StringBuilder baninaryMetnod(BigInteger num, BigInteger remainder, StringBuilder sb, BigInteger divisor) {

        if (num.compareTo(new BigInteger(String.valueOf(0))) == 0) {
            return sb;
        }

        // 模
        remainder = num.mod(divisor);
        // 除
        num = num.divide(divisor);
        baninaryMetnod(num, remainder, sb, divisor);
        sb.append(remainder);

        return sb;
    }

    public static void main(String[] args) {
        String a = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101";
        String b = "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011";
        addBinary(a, b);
    }
}