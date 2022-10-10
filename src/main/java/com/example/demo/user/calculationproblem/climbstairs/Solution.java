package com.example.demo.user.calculationproblem.climbstairs;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

class Solution {

    /**
     * 这个提供一个思路，步数为偶数，最小步数为x/2,最大步数为x，在这个范围内出现的所有情况构成不同长度数组全排列，然后去重，得到最终结果
     * 但是由于内存耗费严重，在n=11以上耗费的时间太大，造成超时问题，故而改变策略，策略如下
     *     n = 1: result = 1;
     *     n = 2: result = 2;
     *     n = 3: result = 3;
     *     n = 4: result = 5;
     *     n = 5: result = 8;
     *     n = 6: result = 13;
     *     n = 7: result = 21;
     *     n = 8: result = 34;
     *     n = 9: result = 55;
     *     n = 10: result = 89;
     *     n = 11: result = 144;
     *     n = 12: result = 233;
     *     n = 13: result = 377;
     *     n = 14: result = 610;
     *     n = 15: result = 987;
     *     n = 16: result = 1597
     *     .
     *     .
     *     .
     *     根据如上数据规律，n = 1, result = 1;
     *                      n = 2, result = 2;
     *                      n = 3, result = n1 + n2
     *                      ...
     *     得出结果，在n大于2时，往后任一项是前两项之和，所以使用如下方法
     * @param
     */
    /*public static int climbStairs(int n) {
        if (n > 45 || n < 0) {
            return 0;
        }

        int x = 0;

        Set<int[]> resultSet = new HashSet<>();

        x = n / 2;
        if (n % 2 == 0) {

            for (int i = x; i <= n; i++) {
                int[] middles = new int[i];
                if (i == x) {
                    for (int j = 0; j < middles.length; j++) {
                        middles[j] = 2;
                    }
                }else {
                    int num = i - x;
                    for (int j = 0; j < num; j++) {
                        middles[j * 2] = 1;
                        middles[j * 2 + 1] = 1;
                    }

                    for (int j = 0; j < middles.length; j++) {
                        if (middles[j] == 0) {
                            middles[j] = 2;
                        }
                    }
                }
                int start = 0;
                arrayMethod(middles, resultSet, start);
            }
        }else {

            for (int i = x; i < n; i++) {
                int[] middles = new int[i + 1];
                if (i == x) {
                    for (int j = 0; j < middles.length - 1; j++) {
                        middles[j] = 2;
                    }
                    middles[middles.length - 1] = 1;
                }else {
                    int num = i - x;
                    for (int j = 0; j < num; j++) {
                        middles[j * 2] = 1;
                        middles[j * 2 + 1] = 1;
                    }

                    for (int j = 0; j < middles.length; j++) {
                        if (middles[j] == 0) {
                            middles[j] = 2;
                        }
                    }
                    middles[middles.length - 1] = 1;
                }
                int start = 0;
                arrayMethod(middles, resultSet, start);
            }
        }

//        resultSet.forEach(result ->{
//            System.out.println(Arrays.toString(result));
//        });

        System.out.println(resultSet.size());
        return resultSet.size();
    }

    private static void arrayMethod(int[] middles, Set<int[]> resultSet, int start) {
        if (start == middles.length - 1) {
            final AtomicBoolean flag = new AtomicBoolean(true);
            resultSet.forEach(result -> {
                if (Arrays.equals(result, middles)) {
                    flag.set(false);
                    return;
                }
            });
            *//*
            for (int[] ints : resultSet) {
                if (Arrays.equals(ints, middles)) {
                    flag = false;
                    break;
                }
            }*//*
            int[] results = new int[middles.length];
            for (int i = 0; i < middles.length; i++) {
                results[i] = middles[i];
            }
            if (flag.get()) {
                resultSet.add(results);
            }
        }

        for (int i = start; i < middles.length; i++) {

            swap(middles, i, start);
            arrayMethod(middles, resultSet, start + 1);
            swap(middles, i, start);

        }
    }

    private static void swap(int[] middles, int i, int j) {
        int temp;
        temp = middles[i];
        middles[i] = middles[j];
        middles[j] = temp;
    }*/

    public static int climbStairs(int n) {
        if (n > 45 || n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }else if (n == 2) {
            return 2;
        }

        int[] nums = new int[n];
        nums[0] = 1;
        nums[1] = 2;
        for (int i = 2; i < n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }

        System.out.println(Arrays.toString(nums));
        System.out.println(nums[n - 1]);

        return nums[n - 1];
    }

    public static void main(String[] args) {
        climbStairs(46);
    }
}