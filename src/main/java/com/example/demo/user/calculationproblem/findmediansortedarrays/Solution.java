package com.example.demo.user.calculationproblem.findmediansortedarrays;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

class Solution {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
//        int[] ints = ArrayUtils.addAll(nums1, nums2);
        int[] ints = new int[nums1.length + nums2.length];
        int flag = 0;
        for (int i : nums1) {
            ints[flag] = i;
            flag++;
        }

        for (int i = 0; i < nums2.length; i++) {
            ints[flag] = nums2[i];
            flag++;
        }

        Arrays.sort(ints);

        System.out.println("合并数组：" + Arrays.toString(ints));

//        int middle = ints.length%2 != 0 ? ints.length/2 + 1 : ints.length/2;
        int middle = ints.length/2;

        if (ints.length%2 != 0) {
            return ints[middle];
        }else {
            return (ints[middle - 1] + ints[middle])/2.0;
        }

        /*int i = 0;
        int j = ints.length - 1;

        while (j - i >= -1) {
            if (j - i == 0) {
//                    System.out.println("中位数：" + ints[i]);
                return ints[i];
            }else if (j - i < 0) {
//                    System.out.println("中位数：" + (ints[i]+ints[j])/2);
                return (ints[i]+ints[j])/2.0;

            }
            i++;
            j--;
        }*/

       /* for (int i = 0; i < ints.length;) {
            for (int j = ints.length - 1; j >= 0;) {
                if (j - i == 0) {
//                    System.out.println("中位数：" + ints[i]);
                    return ints[i];
                }else if (j - i < 0) {
//                    System.out.println("中位数：" + (ints[i]+ints[j])/2);
                    return (ints[i]+ints[j])/2.0;

                }
                i++;
                j--;
            }
            break;
        }*/
    }

    public static void main(String[] args) {

        int[] num1 = {1, 3};
        int[] num2 = {2, 4};

        findMedianSortedArrays(num1, num2);
    }
}