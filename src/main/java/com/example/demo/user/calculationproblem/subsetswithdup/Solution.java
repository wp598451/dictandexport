package com.example.demo.user.calculationproblem.subsetswithdup;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Solution {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {

        // ==========================校验开始==========================
        AtomicBoolean flag = new AtomicBoolean(false);

        if (nums.length > 10) {
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
        // ==========================校验结束==========================
        Set<List<Integer>> resultSet = new LinkedHashSet<>();
        List<List<Integer>> resultList = new ArrayList<>();
        resultSet.add(new ArrayList<>());
        int start = 0;
        int z = 0;
        subsetsWithDupMethod(start, nums, resultSet, z);
        resultList.addAll(resultSet);
        Collections.sort(resultList, (list2, list1) -> {
            if (list1.size() != 0) {
                if(null == list1.get(0)) {
                    return -1;
                }
                if(null == list2.get(0)) {
                    return 1;
                }
                return list2.get(0).compareTo(list1.get(0));
            }
            return 1;
        });
        return resultList;
    }

    private static void subsetsWithDupMethod(int start, int[] nums, Set<List<Integer>> result, int z) {
        if (start > nums.length) {
            return;
        }

        for (int i = nums.length - 1; i >= 0;) {
            if (start == nums.length - 1) {
                break;
            }
            start++;
            int[] ints = new int[start + 1];
            if (start == 0) {
                ints[0] = nums[i];
            }else {
                ints[ints.length - 1] = nums[i];
                getInts(nums, ints, i, result, z);
            }
            result.add(Arrays.stream(ints).boxed().collect(Collectors.toList()));
        }
        subsetsWithDupMethod(start, nums, result, z);
    }

    private static void getInts(int[] nums, int[] ints, int i, Set<List<Integer>> result, int z) {

        if (i < nums.length - 1) {
            i--;
            getInts(nums, ints, i, result, z);
            z++;
            ints[z] = nums[i];
            result.add(Arrays.stream(ints).boxed().collect(Collectors.toList()));
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        subsetsWithDup(nums);
    }
}