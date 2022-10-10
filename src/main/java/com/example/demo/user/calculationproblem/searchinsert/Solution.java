package com.example.demo.user.calculationproblem.searchinsert;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Solution {
    public static int searchInsert(int[] nums, int target) {

        AtomicBoolean flag = new AtomicBoolean(false);

        if (nums.length > Math.pow(10, 4)) {
            return 0;
        }

        Arrays.stream(nums).boxed().collect(Collectors.toList()).forEach(num -> {
            if (-Math.pow(10, 4) > num || Math.pow(10, 4) < num) {
                flag.set(true);
            }
        });

        if (-Math.pow(10, 4) > target || Math.pow(10, 4) < target) {
            return 0;
        }

        if (flag.get()) {
            return 0;
        }

       /* if (target > nums[nums.length - 1]) {
            return nums.length;
        }else if (target < nums[0]) {
            return 0;
        }*/

        int left = 0;
        while (left < nums.length && nums[left] < target) left++;
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        searchInsert(nums, 0);
    }
}