package com.example.demo.user.calculationproblem.fulljustify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

class Solution {
    public static List<String> fullJustify(String[] words, int maxWidth) {

        // ==========判断条件开始==========
        AtomicBoolean flag = new AtomicBoolean(false);

        if (words.length > 300 || maxWidth > 100) {
            return null;
        }

        Arrays.stream(words).collect(Collectors.toList()).forEach(word -> {
            if (word.length() > 20) {
                flag.set(true);
            }

            if (word.length() > maxWidth) {
                flag.set(true);
            }
        });

        if (flag.get()) {
            return null;
        }
        // ==========判断条件结束==========

        StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append(words[0]);
        List<String> result = new ArrayList<>();
        wordMethod(words, maxWidth, sb, result, i);

        result.forEach(System.out::println);
        return result;
    }

    private static void wordMethod(String[] words, int maxWidth, StringBuilder sb, List<String> result, int i) {

        if (i == words.length - 1) {
            int length = sb.length();
            int i1 = maxWidth - length;
            for (int j = 0; j < i1; j++) {
                sb.append(" ");
            }
            result.add(sb.toString());
            return;
        }

        // 当长度之和大于maxWidth
        if (sb.length() + words[i + 1].length() + 1 > maxWidth) {
            if (sb.length() != maxWidth) {
                int judge = maxWidth - sb.length();
                String[] s = sb.toString().split(" ");

                int kgNum = s.length - 1;
                checkMethod(s, judge, kgNum, sb, maxWidth);


//                sb = new StringBuilder();
//                int cs = judge / s.length;
//                int ms = judge % s.length;
//
//                // 初始化空格
//                StringBuilder kg = new StringBuilder(" ");
//                for (int j = 0; j < cs; j++) {
//                    for (int z = 0; z < cs; z++) {
//                        kg.append(" ");
//                    }
//                }
//
//                String[] space = new String[s.length - 1];
//
//                for (int j = 0; j < space.length; j++) {
//                    space[j] = kg.toString();
//                }
//
//                for (int j = 0; j < ms; j++) {
//                    space[j] += " ";
//                }
//
//                for (int j = 0; j < s.length; j++) {
//
//                    if (s.length == 1) {
//                        sb.append(s[j]).append(kg);
//                    }
//
//                    if (j == s.length - 1 && s.length != 1) {
//                        sb.append(s[j]);
//                    }
//                    for (int z = 0; z < space.length; z++) {
//                        if (j == z) {
//                            sb.append(s[j]).append(space[z]);
//                            break;
//                        }
//                    }
//                }

                result.add(sb.toString());
                sb = new StringBuilder();
                i++;
                sb.append(words[i]);
                wordMethod(words, maxWidth, sb, result, i);
            }
        }else if (sb.length() + 1 + words[i + 1].length() == maxWidth){
            sb.append(" ").append(words[++i]);
            result.add(sb.toString());
            sb = new StringBuilder();
            i++;
            sb.append(words[i]);
            wordMethod(words, maxWidth, sb, result, i);
        }else {
            sb.append(" ").append(words[i+1]);
            i++;
            wordMethod(words, maxWidth, sb, result, i);
        }

    }

    private static void checkMethod(String[] s, int judge, int kgNum, StringBuilder sb, int maxWidth) {
        if (sb.length() == maxWidth) {
            return;
        }

        sb = new StringBuilder();

        if (sb.length() < maxWidth) {
            for (int z = 0; z < s.length; z++) {

                sb.append(s[z]);

                for (int i = 0; i < judge; i++) {
                    for (int j = 0; j < kgNum; j++) {
                        if (i == j) {
                            sb.append(" ");
                            break;
                        }

                        if (i > j) {
                            j = 0;
                        }
                    }
                }
            }


        }
    }

    public static void main(String[] args) {
//        String[] words = {"Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"};
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
//        String[] words = {"What","must","be","acknowledgment","shall","be"};
        int maxLength = 16;
        fullJustify(words, maxLength);
    }
}