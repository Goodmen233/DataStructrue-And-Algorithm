package com.ccb;

public class Solution {

    public static void main(String[] args) {
        isInterleave("aabc", "abad", "aabadabc");
    }

    /**
     * 判断s3是不是由s1和s2交叉组成
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() < s3.length() ||
                s1.length() + s2.length() > s3.length()){
            return false;
        }
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for(int i = 0; i < s1.length() + 1; i++){
            for(int j = 0; j < s2.length() + 1; j++){
                if(i == 0 && j == 0) continue;
                int p3 = i + j;
                if(j > 0 && s2.charAt(j - 1) == s3.charAt(p3 - 1)){
                    dp[i][j] = dp[i][j - 1];
                }
                if(i > 0 && s1.charAt(i - 1) == s3.charAt(p3 - 1) && !dp[i][j]){
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return dp[s1.length()][s2.length()];
    }

}