package com.example.demo.user.calculationproblem.inttoroman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    private final int M = 1000;

    private final int D = 500;

    private final int C = 100;

    private final int L = 50;

    private final int X = 10;

    private final int V = 5;

    private final int I = 1;

    public static String intToRoman(int num) {

        Map<Integer,String> map = new HashMap<Integer, String>();
        map.put(1,"I");
        map.put(5,"V");
        map.put(10,"X");
        map.put(50,"L");
        map.put(100,"C");
        map.put(500,"D");
        map.put(1000,"M");

        if (num < 1 || num > 3999){
            return "请输入正确的数字范围!!!";
        }

        StringBuilder sb = new StringBuilder();
        int m = 1000;

        while (num != 0) {
            int count = num / m;
            num -= count * m;

            if (count == 9) {

                sb.append(map.get(m)).append(map.get(m*10));
            }else if (count >= 5) {

                sb.append(map.get(5*m));
                while (count-- > 5) {
                    sb.append(map.get(m));
                }
            } else if (count == 4) {

                sb.append(map.get(m)).append(map.get(m*5));
            }else {

                while (count-- != 0) {
                    sb.append(map.get(m));
                }
            }
            m /= 10;
        }
        System.out.println(sb.toString());
        return sb.toString();
    }


    public static void main(String[] args) {
        int num = 3333;
        intToRoman(num);
    }
}