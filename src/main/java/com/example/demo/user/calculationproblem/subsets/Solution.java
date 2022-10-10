package com.example.demo.user.calculationproblem.subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Solution {
    public static List<List<Integer>> subsets(int[] nums) {

        // ===========================校验开始===========================
        AtomicBoolean flag = new AtomicBoolean(false);

        if (nums.length > 10) {
            return null;
        }

        Arrays.stream(nums).boxed().collect(Collectors.toList()).forEach(num -> {
            if (num > 10 || num < -10) {
                flag.set(true);
                return;
            }
        });

        if (flag.get()) {
            return null;
        }
        // ===========================校验结束===========================

        List<List<Integer>> result = new ArrayList<>();
        int num = 0;
        subsetsMethod(nums, num, result);



        return null;
    }

    private static void subsetsMethod(int[] nums, int num, List<List<Integer>> result) {

        if (num == nums.length) {
            return;
        }

        int[] res = new int[num];

        for (int i = res.length - 1; i >= 0; i--) {
            for (int j = nums.length - 1; j >= 0; j--) {
                res[i] = nums[j];
                result.add(Arrays.stream(res).boxed().collect(Collectors.toList()));
            }
        }


        num++;
        if (res.length == 0) {
            result.add(Arrays.stream(res).boxed().collect(Collectors.toList()));
        }
        subsetsMethod(nums, num, result);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        subsets(nums);
    }
}