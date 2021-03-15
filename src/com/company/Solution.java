package com.company;

public class Solution {
    /**
     * Algorithm is very simple. It just takes every char pair in string and compares them to find
     * highest. It could be done better, but main focus of this task is correctness so I chose the simplest and
     * most readable solution in my mind.
     */
    public int solution(String S) {
        int sLength = S.length();
        int maxNumber = Integer.parseInt(S.substring(0,2));
        for (int i = 1; i< sLength-1; ++i) {
            int possibleMaxNumber = Integer.parseInt(S.substring(i,i+2));
            if (maxNumber < possibleMaxNumber) {
                maxNumber = possibleMaxNumber;
            }
        }
        return maxNumber;
    }
}
