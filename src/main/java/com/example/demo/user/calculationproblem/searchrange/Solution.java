package com.example.demo.user.calculationproblem.searchrange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    private static int[] err = {-1, -1};
    public static int[] searchRange(int[] nums, int target) {

        if (nums.length > Math.pow(10, 5)) {
            return err;
        }

        Arrays.stream(nums).boxed().collect(Collectors.toList()).forEach(num -> {
            if (Math.pow(-10, 9) < num || Math.pow(10, 9) > num) {
                return;
            }
        });

        if (Math.pow(-10, 9) > target || Math.pow(10, 9) < target) {
            return err;
        }

        Arrays.sort(nums);

        boolean flag = true;

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > target) {
                flag = false;
                break;
            }

            if (nums[i] == target) {
                if (result.size() < 2) {
                    result.add(i);
                }else {
                    result.set(1, i);
                }
                flag = false;
            }
        }

        if (flag || result.size() == 0) {
            return err;
        }

        if (result.size() == 1) {
            result.add(result.get(0));
            return result.stream().mapToInt(Integer::valueOf).toArray();
        }

        return result.stream().mapToInt(Integer::valueOf).toArray();
    }

    public static void main(String[] args) {
        int[] nums = {3,3,3,3};
        int target = 3;
        searchRange(nums, target);
    }
}