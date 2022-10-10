package com.example.demo.user.calculationproblem.maxarea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 1. 直接通过循环转换List->Array
        /*Integer[] res = new Integer[areaList.size()];
        for (int i = 0; i < areaList.size(); i++) {
            res[i] = areaList.get(i);
        }*/

// 2. 通过泛型转换List->Array
        /*Integer[] res = new Integer[areaList.size()];

        areaList.toArray(res);*/

class Solution {

    public static int maxArea(int[] height) {

        if (height.length == 1) {
            return 0;
        }

        int maxResult = 0, start = 0, j = height.length - 1, result = 0;

        while (start < j) {
            result = height[start] < height[j] ? height[start] * (j - start) : height[j] * (j - start);
            if (height[start] < height[j]) start++;
            else j--;
            if (maxResult < result) maxResult = result;
        }

        System.out.println("最大面积为：" + maxResult);
        return maxResult;
    }

   /* private static void maxAreaMethod(int[] height, int start, int j, int[] resullt) {
        if (start >= height.length - 1) {
            return;
        }

        while (j < height.length) {
            int result = height[start] < height[j] ? height[start] * (j - start) : height[j] * (j - start);
            if (resullt[0] < result) {
                resullt[0] = result;
            }
            j++;
        }
        start++;
        j = start + 1;
        maxAreaMethod(height, start, j, resullt);
    }*/

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        maxArea(height);
    }
}