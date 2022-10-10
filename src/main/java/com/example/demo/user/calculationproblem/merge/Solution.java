package com.example.demo.user.calculationproblem.merge;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Solution {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        if (m + n > 200 || m + n != nums1.length || m < 0 || m > 200 || n > 200 || n != nums2.length) {
            return;
        }

        AtomicBoolean flagNum1 = new AtomicBoolean(false);
        AtomicBoolean flagNum2 = new AtomicBoolean(false);

        Arrays.stream(nums1).boxed().collect(Collectors.toList()).forEach(num -> {
            if (num > Math.pow(10, 9) || num < Math.pow(-10, 9)) {
                flagNum1.set(true);
            }
        });

        Arrays.stream(nums2).boxed().collect(Collectors.toList()).forEach(num -> {
            if (num > Math.pow(10, 9) || num < Math.pow(-10, 9)) {
                flagNum2.set(true);
            }
        });

        if (flagNum1.get() || flagNum2.get()) {
            return;
        }

        int j = 0;
//        for (int i = m; i < nums1.length; i++) {
//            if (j < n) {
//                nums1[i] = nums2[j];
//                j++;
//            }
//        }

        while (j < n) nums1[m++] = nums2[j++];

        Arrays.sort(nums1);
        System.out.println(Arrays.toString(nums1));
    }

    public static void main(String[] args) {
//        int[] nums1 = {1,2,3,0,0,0};
//        int[] nums2 = {2,5,6};
//        int[] nums1 = {0};
//        int[] nums2 = {1};
//        int[] nums1 = {1};
//        int[] nums2 = {};
        int[] nums1 = {-10,-10,-9,-9,-9,-8,-8,-7,-7,-7,-6,-6,-6,-6,-6,-6,-6,-5,-5,-5,-4,-4,-4,-3,-3,-2,-2,-1,-1,0,1,1,1,2,2,2,3,3,3,4,5,5,6,6,6,6,7,7,7,7,8,9,9,9,9,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] nums2 = {-10,-10,-9,-9,-9,-9,-8,-8,-8,-8,-8,-7,-7,-7,-7,-7,-7,-7,-7,-6,-6,-6,-6,-5,-5,-5,-5,-5,-4,-4,-4,-4,-4,-3,-3,-3,-2,-2,-2,-2,-2,-2,-2,-1,-1,-1,0,0,0,0,0,1,1,1,2,2,2,2,2,2,2,2,3,3,3,3,4,4,4,4,4,4,4,5,5,5,5,5,5,6,6,6,6,6,7,7,7,7,7,7,7,8,8,8,8,9,9,9,9};

        merge(nums1, 55, nums2, 99);
    }
}