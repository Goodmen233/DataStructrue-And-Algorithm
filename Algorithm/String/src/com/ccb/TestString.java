package com.ccb;

import com.ccb.kmp.Kmp;
import com.ccb.manacher.Manacher;

public class TestString {
    public static void main(String[] args) {
//        testKmp();
        testManacher();
    }

    public static void testManacher() {
        System.out.println(Manacher.getLongestPalindromicSubstring("ababubabas"));
    }

    /**
     * check
     */
    public static void testKmp() {
        String s = "abababb";//abababb
        String str = "abacbcabababbcbc";
        System.out.println(Kmp.kmp(str, s));
    }
}
