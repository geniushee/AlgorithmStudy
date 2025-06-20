package org.ll.backjun;

import java.io.*;

/**
 * 문제<br>
 * LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.<br>
 * 예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.<hr>
 * 입력<br>
 * 첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.<hr>
 * 출력<br>
 * 첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를 출력한다.<hr>
 * 예제 입력 1 <br>
 * ACAYKP<br>
 * CAPCAK<hr>
 * 예제 출력 1<br>
 * 4<br>
 */
public class LCS {
    private static int maxLength;

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final String str1 = br.readLine();
        final String str2 = br.readLine();
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m+ 1];

        for(int i = 1; i <=n; i++){
            for(int j = 1; j <=m; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        bw.write(dp[n][m] + "\n");
        bw.flush();
        bw.close();
    }

    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        final String str1 = br.readLine();
        final String str2 = br.readLine();
        int n = str1.length();
        int m = str2.length();
        int[][] dp = new int[n + 1][m+ 1];

        for(int i = 1; i <=n; i++){
            for(int j = 1; j <=m; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println(dp[n][m]);
    }
}
