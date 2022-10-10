package com.example.demo.user.calculationproblem.sortcolors;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Solution {
    public static void sortColors(int[] nums) {
        AtomicBoolean flag = new AtomicBoolean(false);

        Arrays.stream(nums).boxed().collect(Collectors.toList()).forEach(num -> {
            if (!(num == 0 || num == 1 || num == 2)) {
                flag.set(true);
            }
        });

        if (nums.length > 300) {
            return;
        }
        int length = nums.length;
        dgMethod(nums, length);
    }

    private static void dgMethod(int[] nums, int length) {

        if (length < 1) {
            return;
        }

        int temp = nums[0];
        for (int i = 1; i < length; i++) {
            if (temp < nums[i]) {
                temp = nums[i];
            }else if (temp > nums[i]) {
                nums[i - 1] = nums[i];
                nums[i] = temp;
            }
        }
        length--;
        dgMethod(nums, length);
    }

    public static void main(String[] args) {
//        int[] nums = {2,0,2,1,1,0};
        int[] nums = {2,0,1};
        sortColors(nums);
    }
}