package com.example.demo.user.calculationproblem.firstmissingpositive;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Solution {
    public static int firstMissingPositive(int[] nums) {

        AtomicBoolean flag = new AtomicBoolean(false);

        if (nums.length > 5 * Math.pow(10, 5)) {
            return 0;
        }

        Arrays.stream(nums).boxed().collect(Collectors.toList()).forEach(num -> {
            if (num > Math.pow(2, 31) - 1 || num < Math.pow(-2, 31)) {
                flag.set(true);
            }
        });
        if (flag.get()) {
            return 0;
        }

        Arrays.sort(nums);
        int temp = 1;

        if (nums[nums.length - temp] < temp || nums[0] > temp) {
            return temp;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1) {
                continue;
            }

            if (i < nums.length - 1) {
                if (temp == 1 && nums[i] > 1){
                    return temp;
                }
                if (temp == nums[i + 1]) {
                    continue;
                }
                if (temp + 1 != nums[i + 1]) {
                    return ++temp;
                }
            }else if (nums[i] > temp) {
                return temp;
            }/*else if (nums[i] == temp) {
                return ++temp;
            }*/else {
                return ++temp;
            }

            temp++;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {0,2,2,1,1};
//        int[] nums = {1000, -1};
//        int[] nums = {-1,-2,-60,40,43};
//        int[] nums = {7,8,9,11,12};
//        int[] nums = {1,2,0};
//        int[] nums = {3,4,-1,1};
//        int[] nums = {3,2,-1,1};

        firstMissingPositive(nums);
    }

}