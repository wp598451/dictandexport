package com.example.demo.user.calculationproblem.findsubstring;

import java.util.*;

class Solution {
    public static List<Integer> findSubstring(String s, String[] words) {

        if (s.length() > Math.pow(10, 4) || words.length > 5000) {
            return null;
        }

        for (int i = 0; i < words.length; i++) {
            if (i != words.length - 1) {
                if (words[i].length() != words[i + 1].length() || words[i].length() > 30) return null;
            }else {
                if (words[i].length() > 30) return null;
            }
        }

        // 初始化输出结果集
        List<Integer> result = new ArrayList<>();
        List<String> list = Arrays.asList(words);

        Map<Integer, String> sMap = new LinkedHashMap<>();
        int i = 0;
        List<String> stringList = Arrays.asList(words);
        int j = stringList.size() * stringList.get(0).length();

        int num = j;

        while (j <= s.length()) {
            int length = s.substring(i).length();
            if (num <= length) {
                String substring = s.substring(i, j);
                sMap.put(i, substring);
                i += stringList.get(0).length();
                j += stringList.get(0).length();
            }
        }

        for (Integer key : sMap.keySet()) {
            String str = sMap.get(key);
            int x = 0;
            int y = stringList.get(0).length();
            List<String> middleList = new ArrayList<>(list);
            while (y <= str.length()) {
                boolean flag = true;
                Iterator<String> iterator = middleList.iterator();

                while (iterator.hasNext()) {
                    if (str.substring(x, y).equals(iterator.next())) {
                        iterator.remove();
                        x += stringList.get(0).length();
                        y += stringList.get(0).length();
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    break;
                }

                if (middleList.size() == 0) {
                    result.add(key);
                }
            }
        }

        System.out.println(result);

        return result;
    }

    public static void main(String[] args) {
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar","foo","the"};
        findSubstring(s, words);
    }
}