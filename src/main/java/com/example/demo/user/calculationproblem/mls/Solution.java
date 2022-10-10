package com.example.demo.user.calculationproblem.mls;

import java.util.*;


public class Solution {
    /**
     * max increasing subsequence
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public static int MLS (int[] arr) {
        // write code here

        Arrays.sort(arr);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] + 1 == arr[i + 1]) {
                list.add(arr[i]);
                list.add(arr[i + 1]);
                i++;
            }else if (arr[i - 1] + 1 == arr[i]) {
                list.add(arr[i]);
                i++;
            }
        }

        int flag = 0;
        int judge = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                judge++;
                if (flag < judge) {
                    flag = judge;
                }
                break;
            }
            if (list.get(i) + 1 == list.get(i+1)) {
                judge++;
            }else {
                judge++;
                if (flag < judge) {
                    flag = judge;
                }
                judge = 0;
            }
        }

        return flag;
    }

    public static void main(String[] args) {
        int[] arr = {100,4,200,1,3,2,8,9,10};
        MLS(arr);
    }
}