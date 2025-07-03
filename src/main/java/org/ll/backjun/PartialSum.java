package org.ll.backjun;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 문제<br>
 * 10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다. 이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.<hr>
 * 입력<br>
 * 첫째 줄에 N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)가 주어진다. 둘째 줄에는 수열이 주어진다. 수열의 각 원소는 공백으로 구분되어져 있으며, 10,000이하의 자연수이다.<hr>
 * 출력<br>
 * 첫째 줄에 구하고자 하는 최소의 길이를 출력한다. 만일 그러한 합을 만드는 것이 불가능하다면 0을 출력하면 된다.<hr>
 * 예제 입력 1<br>
 * 10 15<br>
 * 5 1 3 5 10 7 4 9 2 8<hr>
 * 예제 출력 1<br>
 * 2
 */
public class PartialSum {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] seq = new int[n];
        for(int i = 0;i<n;i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }
        int min = Integer.MAX_VALUE;

        int front = 0;
        int back = 0;
        int sum = 0;
        while(true){
            if(sum >= s){
                min = Math.min(min, front - back);
                sum -= seq[back++];
            }else if(front == n){
                break;
            }else{
                sum += seq[front++];
            }
        }

        if(min == Integer.MAX_VALUE){
            bw.write(0+"\n");
        }else {
            bw.write(min + "\n");
        }
        bw.flush();
        bw.close();
    }
    // consecutiveSum과 유사하게 구현하되. 길이를 반환할 수 있도록.
    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] seq = new int[n];
        for(int i = 0;i<n;i++){
            seq[i] = Integer.parseInt(st.nextToken());
        }
        int min = Integer.MAX_VALUE;

        int front = 0;
        int back = 0;
        int sum = 0;
        while(true){
            if(sum >= s){
                min = Math.min(min, front - back);
                sum -= seq[back++];
            }else if(front == n){
                break;
            }else{
                sum += seq[front++];
            }
        }
        if(min == Integer.MAX_VALUE){
            System.out.println(0);
        }else {
            System.out.println(min);
        }
    }
}
