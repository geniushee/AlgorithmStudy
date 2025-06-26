package org.ll.backjun;

import java.io.*;

/**
 * 문제<br>
 * 어떤 N개의 수가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려 한다. 만약에 1,2,3,4,5 라는 수가 있고, 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 합을 구하라고 한다면 17을 출력하면 되는 것이다. 그리고 그 상태에서 다섯 번째 수를 2로 바꾸고 3번째부터 5번째까지 합을 구하라고 한다면 12가 될 것이다.<hr>
 * 입력<br>
 * 첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다. M은 수의 변경이 일어나는 횟수이고, K는 구간의 합을 구하는 횟수이다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다. 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a, b, c가 주어지는데, a가 1인 경우 b(1 ≤ b ≤ N)번째 수를 c로 바꾸고 a가 2인 경우에는 b(1 ≤ b ≤ N)번째 수부터 c(b ≤ c ≤ N)번째 수까지의 합을 구하여 출력하면 된다.<br>
 * 입력으로 주어지는 모든 수는 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다.<hr>
 * 출력<br>
 * 첫째 줄부터 K줄에 걸쳐 구한 구간의 합을 출력한다. 단, 정답은 -263보다 크거나 같고, 263-1보다 작거나 같은 정수이다.<hr>
 * 예제 입력 1 <br>
 * 5 2 2<br>
 * 1<br>
 * 2<br>
 * 3<br>
 * 4<br>
 * 5<br>
 * 1 3 6<br>
 * 2 2 5<br>
 * 1 5 2<br>
 * 2 3 5<hr>
 * 예제 출력 1<br>
 * 17<br>
 * 12
 */
public class IntervalSum {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int k = Integer.parseInt(line[2]);
        long[] arr = new long[n+1];
        for(int i = 1; i < n+1; i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        String flag;

        for(int i = 0; i<m+k;i++){
            line = br.readLine().split(" ");
            flag = line[0];
            switch(flag){
                case "1" -> change(arr, line[1], line[2]);
                case "2" ->{
                    long sum = partSum(arr, line[1], line[2]);
                    bw.write(sum + "\n");
                    bw.flush();
                }
            }
        }
        bw.close();
    }
    private static long partSum(long[] arr, String s, String e) {
        int start = Integer.parseInt(s);
        int end = Integer.parseInt(e);
        long sum = 0;
        for(int i = start; i<=end; i++){
            sum += arr[i];
        }
        return sum;
    }

    private static void change(long[] arr, String preIdx, String changed) {
        int idx = Integer.parseInt(preIdx);
        arr[idx] = Long.parseLong(changed);
    }
    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int k = Integer.parseInt(line[2]);
        long[] arr = new long[n+1];
        for(int i = 1; i < n+1; i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        String flag;

        for(int i = 0; i<m+k;i++){
            line = br.readLine().split(" ");
            flag = line[0];
            switch(flag){
                case "1" -> change(arr, line[1], line[2]);
                case "2" ->{
                    long sum = partSum(arr, line[1], line[2]);
                    System.out.print(sum + "\n");
                }
            }
        }

    }
}
