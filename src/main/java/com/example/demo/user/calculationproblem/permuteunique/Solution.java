package com.example.demo.user.calculationproblem.permuteunique;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {

        AtomicBoolean flag = new AtomicBoolean(false);

        if (nums.length > 8) {
            return null;
        }

        Arrays.stream(nums).boxed().collect(Collectors.toList()).forEach(num -> {
            if (num > 10 || num < -10) {
                flag.set(true);
            }
        });

        if (flag.get()) {
            return null;
        }

        int start = 0;
        int i = 0;

        while(start < nums.length) {

            swap(nums, start, i);
            permuteUniqueMethod(nums, ++start, i);
            swap(nums, start, i);


        }






        return null;
    }

    private void permuteUniqueMethod(int[] nums, int i, int start) {
        if (start > i) {
            return;
        }


    }

    private void swap(int[] nums, int start, int i) {
        int temp;
        temp = nums[i];
        nums[i] = nums[start];
        nums[start] = temp;
    }
}