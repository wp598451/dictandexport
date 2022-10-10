package com.example.demo.user.calculationproblem.lettercombinations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    /*public static List<String> letterCombinations(String digits) {

        if (digits.length() > 4) {
            return null;
        }

        List<String> results = new ArrayList<>();
        if (digits.length() == 0) {
            return results;
        }

        List<String> letter2 = new ArrayList<>();
        List<String> letter3 = new ArrayList<>();
        List<String> letter4 = new ArrayList<>();
        List<String> letter5 = new ArrayList<>();
        List<String> letter6 = new ArrayList<>();
        List<String> letter7 = new ArrayList<>();
        List<String> letter8 = new ArrayList<>();
        List<String> letter9 = new ArrayList<>();

        letter2.add("a");
        letter2.add("b");
        letter2.add("c");
        letter3.add("d");
        letter3.add("e");
        letter3.add("f");
        letter4.add("g");
        letter4.add("h");
        letter4.add("i");
        letter5.add("j");
        letter5.add("k");
        letter5.add("l");
        letter6.add("m");
        letter6.add("n");
        letter6.add("o");
        letter7.add("p");
        letter7.add("q");
        letter7.add("r");
        letter7.add("s");
        letter8.add("t");
        letter8.add("u");
        letter8.add("v");
        letter9.add("w");
        letter9.add("x");
        letter9.add("y");
        letter9.add("z");

        Map<String, List<String>> letters = new HashMap<>();
        letters.put("2", letter2);
        letters.put("3", letter3);
        letters.put("4", letter4);
        letters.put("5", letter5);
        letters.put("6", letter6);
        letters.put("7", letter7);
        letters.put("8", letter8);
        letters.put("9", letter9);

        String[] strs = new String[digits.length()];
        for (int i = 0; i < digits.length(); i++) {
            strs[i] = digits.substring(i, i+1);
        }

        int finalI = 0;
        int finalII = 1;
        int finalIII = 2;
        if (strs.length == 1)
            results.addAll(letters.get(strs[finalI]));
        else if (strs.length == 2)
        letters.get(strs[finalI]).forEach(s -> {
            letters.get(strs[finalI +1]).forEach(s1 -> {
                results.add(s+s1);
                    }
            );
                }
        );
        else if (strs.length == 3)
            letters.get(strs[finalI]).forEach(s -> {
                letters.get(strs[finalI + 1]).forEach(s1 -> {
                            letters.get(strs[finalII + 1]).forEach(s2 -> {
                                        results.add(s + s1 + s2);
                                    }
                            );
                        }
                );
            });
        else letters.get(strs[finalI]).forEach(s -> {
                letters.get(strs[finalI + 1]).forEach(s1 -> {
                        letters.get(strs[finalII + 1]).forEach(s2 -> {
                            letters.get(strs[finalIII + 1]).forEach(s3 -> {
                                results.add(s + s1 + s2 + s3);
                            }
                            );
                        }
                        );
                }
                );
            }
            );

        return results;
    }
*/

    static List<String> result = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    static String[] array = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"} ;

    private static List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return result;
        backtracking(digits, 0);
        return result;
    }

    private static void backtracking(String digits, int index) {
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }
        String string = array[digits.charAt(index) - '0']; // jkl
        for (int i = 0; i < string.length(); i++) {
            sb.append(string.charAt(i));
            backtracking(digits, index+1);
            sb.deleteCharAt(sb.length()- 1);
        }
    }

    public static void main(String[] args) {
        String s = "5678";
        List<String> list = letterCombinations(s);
        assert list != null;
        System.out.println(list.size());
        for (String s1 : list) {
            System.out.print(s1);
            System.out.print(",");
        }
    }
}