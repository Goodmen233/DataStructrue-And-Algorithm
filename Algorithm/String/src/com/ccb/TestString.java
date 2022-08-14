package com.ccb;

import com.ccb.kmp.Kmp;

public class TestString {
    public static void main(String[] args) {
        String s = "abababb";//abababb
        String str = "abacbcabababbcbc";
        System.out.println(Kmp.kmp(str, s));
    }
}
