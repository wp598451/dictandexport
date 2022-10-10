package com.example.demo.user.calculationproblem.twoSun;

import javax.xml.soap.Node;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Solution {
    public static int[] twoSum(int[] nums, int target) {

        // ===============================校验开始===============================
        AtomicBoolean flag = new AtomicBoolean(false);

        if (nums.length < 2 || nums.length > 10000 || target > Math.pow(10, 9) || target < Math.pow(-10, 9)) {
            return null;
        }

        Arrays.stream(nums).boxed().collect(Collectors.toList()).forEach(num -> {
            if (num > Math.pow(10, 9) || num < Math.pow(-10, 9)) {
                flag.set(true);
            }
        });

        if (flag.get()) {
            return null;
        }
        // ===============================校验结束===============================
        int[] result = new int[2];
        int i = 0;
        while (i < nums.length) {
            int j = i + 1;
            while (j < nums.length) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    System.out.println(Arrays.toString(result));
                    return result;
                }
                j++;
            }
            i++;
        }
        return null;
    }

    public static void main(String[] args) {
//        int[] nums = {3,2,4};
//        int[] nums = {0, 4, 3, 0};
//        twoSum(nums, 0);

    }
}