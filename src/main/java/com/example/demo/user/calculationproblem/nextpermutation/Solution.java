package com.example.demo.user.calculationproblem.nextpermutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public static int[] nextPermutation(int[] nums) {

        if (nums.length >= 100) {
            return null;
        }
        Arrays.stream(nums).boxed().collect(Collectors.toList()).forEach(num -> {
            if (num < 0 || num > 100) {
                return;
            }
        });


        int bz = 0;
        boolean flag = true;
        int swag = nums[nums.length - 1];

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < swag) {
                flag = false;
                break;
            }
        }

        if (flag) {
            Arrays.sort(nums);
            System.out.println(Arrays.toString(nums));
            return nums;
        }

        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < swag) {
                nums[nums.length - 1] = nums[i];
                nums[i] = swag;
                break;
            }else {
                bz = nums[i];
                nums[i] = swag;
                swag = bz;
            }
        }


        System.out.println(Arrays.toString(nums));
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1};
        nextPermutation(nums);
    }
}