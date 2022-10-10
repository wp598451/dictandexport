package com.example.demo.user.calculationproblem.maxsubarray;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public static int maxSubArray(int[] nums) {

        if (nums.length > Math.pow(10, 5)) {
            return 0;
        }

        Arrays.stream(nums).boxed().collect(Collectors.toList()).forEach(num -> {
            if (num < - Math.pow(10, 4) || num > Math.pow(10, 4)) {
                return;
            }
        });

        int result = nums[0];
        int temp = nums[0];

        for(int i = 1; i < nums.length; i++) {
            if(temp + nums[i] >= nums[i]) {
                temp = temp + nums[i];
            } else {
                temp = nums[i];
            }

            if(temp > result) {
                result = temp;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int [] nums = {8, -19, 5, -4, 20};
        maxSubArray(nums);
    }
}