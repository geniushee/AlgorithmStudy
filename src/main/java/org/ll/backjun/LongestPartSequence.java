package org.ll.backjun;

import java.io.*;
import java.util.Arrays;

/**
 * 문제<br>
 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.<br>
 * 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.<hr>
 * 입력<br>
 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.<br>
 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000,000)<hr>
 * 출력<br>
 * 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.<hr>
 * 예제 입력 1 <br>
 * 6<br>
 * 10 20 10 30 20 50<hr>
 * 예제 출력 1 <br>
 * 4
 */
public class LongestPartSequence {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n];
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int len = 0;
        for(int i = 0;i < n;i++){
            int idx = binarySearch(dp, 0,len, seq[i]);
            dp[idx] = seq[i];
            if(idx == len) len++;
        }
        bw.write(len + "\n");
        bw.flush();
        bw.close();
    }
    public static int binarySearch(int[] dp, int start, int end, int target){
        int left = start;
        int right = end;

        while(left < right){
            int mid = (left + right) /2;
            if(dp[mid] < target){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }
    public static void run(String arg) throws IOException {
        System.out.println("start!");
        BufferedReader br = new BufferedReader(new StringReader(arg));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n];
        int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int len = 0;
        for(int i = 0;i < n;i++){
            int idx = binarySearch(dp, 0,len, seq[i]);
            dp[idx] = seq[i];
            if(idx == len) len++;
        }
        System.out.println(len);
    }
}
