package com.example.demo.user.calculationproblem.plusone;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Solution {
    public static int[] plusOne(int[] digits) {

        if (digits.length > 100) {
            System.out.println("请输入正确的数组元素数量！");
            return null;
        }

        AtomicBoolean flag = new AtomicBoolean(false);

        List<Integer> collect = Arrays.stream(digits).boxed().collect(Collectors.toList());
        collect.forEach(digit -> {
            if (digit > 9 || digit < 0) {
                flag.set(true);
                return;
            }
        });

        if (flag.get()) {
            System.out.println("请输入正确的数字范围！");
            return null;
        }
/*
        int num = digits.length - 1;
        int[] result = plusOneMethod(digits, num, flag);
        return result;*/




        int num = digits.length;
        while (--num >= 0) {
            if (digits[num] != 9) {
                digits[num]++;
                return digits;
            }

            digits[num] = 0;
        }

        int[] result = new int[digits.length + 1];
        result[0] = 1;

        return result;
    }

    private static int[] plusOneMethod(int[] digits, int num, AtomicBoolean flag) {

        if (num == -1) {
            List<Integer> collect = Arrays.stream(digits).boxed().collect(Collectors.toList());
            collect.set(0, 1);
            collect.add(0);
            digits = collect.stream().mapToInt(Integer::valueOf).toArray();
            return digits;
        }

        for (int i = num; i >= 0; ) {
            if (digits[i] == 9 && i >= 0) {
                num--;
                digits[i] = 0;
                digits = plusOneMethod(digits, num, flag);
                break;
            } else if (!flag.get()){
                digits[i] += 1;
                flag.set(true);
                break;
            }
        }
        return digits;
    }

    public static void main(String[] args) {
        int[] digits = {1, 2, 3, 4, 5, 9};
        plusOne(digits);
    }
}