package org.ll.backjun;

import java.io.*;

/**
 *문제<br>
 * 2차원 평면상에 N(3 ≤ N ≤ 10,000)개의 점으로 이루어진 다각형이 있다. 이 다각형의 면적을 구하는 프로그램을 작성하시오.<hr>
 * 입력<br>
 * 첫째 줄에 N이 주어진다. 다음 N개의 줄에는 다각형을 이루는 순서대로 N개의 점의 x, y좌표가 주어진다. 좌표값은 절댓값이 100,000을 넘지 않는 정수이다.<hr>
 * 출력<br>
 * 첫째 줄에 면적을 출력한다. 면적을 출력할 때에는 소수점 아래 둘째 자리에서 반올림하여 첫째 자리까지 출력한다.<hr>
 * 예제 입력 1<br>
 * 4<br>
 * 0 0<br>
 * 0 10<br>
 * 10 10<br>
 * 10 0<hr>
 * 예제 출력 1<br>
 * 100.0
 */
public class AreaOfPolygon {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        long[][] arr = new long[n+1][2];
        for(int i = 0; i<arr.length - 1;i++){
            String[] point = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(point[0]);
            arr[i][1] = Integer.parseInt(point[1]);
        }
        // 첫 점 다시 넣기
        arr[n][0] = arr[0][0];
        arr[n][1] = arr[0][1];

        long sum = 0;
        for(int i = 0; i<n;i++){
            sum += arr[i][0] * arr[i+1][1] - arr[i+1][0] * arr[i][1];
        }
        double answer = Math.abs(sum) / 2.0;

        bw.write(String.format("%.1f\n", answer));
        bw.flush();
        bw.close();
    }
    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n+1][2];
        for(int i = 0; i<arr.length - 1;i++){
            String[] point = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(point[0]);
            arr[i][1] = Integer.parseInt(point[1]);
        }
        // 첫 점 다시 넣기
        arr[n][0] = arr[0][0];
        arr[n][1] = arr[0][1];

        int sum = 0;
        for(int i = 0; i<n;i++){
            sum += arr[i][0] * arr[i+1][1] - arr[i+1][0] * arr[i][1];
        }
        sum += arr[n][0] * arr[0][1] - arr[0][0] * arr[n][1];
        double answer = Math.abs(sum) * 0.5;
        System.out.println(answer);
        answer = (double) Math.round(answer * 10) / 10;

        System.out.println(answer);
    }
}
