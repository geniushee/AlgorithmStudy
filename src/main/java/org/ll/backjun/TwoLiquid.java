package org.ll.backjun;

import java.io.*;
import java.util.Arrays;

/**
 * 문제<br>
 * KOI 부설 과학연구소에서는 많은 종류의 산성 용액과 알칼리성 용액을 보유하고 있다. 각 용액에는 그 용액의 특성을 나타내는 하나의 정수가 주어져있다. 산성 용액의 특성값은 1부터 1,000,000,000까지의 양의 정수로 나타내고, 알칼리성 용액의 특성값은 -1부터 -1,000,000,000까지의 음의 정수로 나타낸다.<br>
 * 같은 양의 두 용액을 혼합한 용액의 특성값은 혼합에 사용된 각 용액의 특성값의 합으로 정의한다. 이 연구소에서는 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.<br>
 * 예를 들어, 주어진 용액들의 특성값이 [-2, 4, -99, -1, 98]인 경우에는 특성값이 -99인 용액과 특성값이 98인 용액을 혼합하면 특성값이 -1인 용액을 만들 수 있고, 이 용액이 특성값이 0에 가장 가까운 용액이다. 참고로, 두 종류의 알칼리성 용액만으로나 혹은 두 종류의 산성 용액만으로 특성값이 0에 가장 가까운 혼합 용액을 만드는 경우도 존재할 수 있다.<br>
 * 산성 용액과 알칼리성 용액의 특성값이 주어졌을 때, 이 중 두 개의 서로 다른 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액을 찾는 프로그램을 작성하시오.<hr>
 * 입력<br>
 * 첫째 줄에는 전체 용액의 수 N이 입력된다. N은 2 이상 100,000 이하이다. 둘째 줄에는 용액의 특성값을 나타내는 N개의 정수가 빈칸을 사이에 두고 주어진다. 이 수들은 모두 -1,000,000,000 이상 1,000,000,000 이하이다. N개의 용액들의 특성값은 모두 다르고, 산성 용액만으로나 알칼리성 용액만으로 입력이 주어지는 경우도 있을 수 있다.<hr>
 * 출력<br>
 * 첫째 줄에 특성값이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값을 출력한다. 출력해야 하는 두 용액은 특성값의 오름차순으로 출력한다. 특성값이 0에 가장 가까운 용액을 만들어내는 경우가 두 개 이상일 경우에는 그 중 아무것이나 하나를 출력한다.<hr>
 * 예제 입력 1 <br>
 * 5<br>
 * -2 4 -99 -1 98<hr>
 * 예제 출력 1 <br>
 * -99 98
 */
public class TwoLiquid {
    private static int[] arr;
    private static int min;
    private static int[] answer;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 초기화
        min = Integer.MAX_VALUE;
        answer = new int[2];
        int n = Integer.parseInt(br.readLine());
        String[] els = br.readLine().split(" ");
        arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = Integer.parseInt(els[i]);
        }
        Arrays.sort(arr);

        int left = 0;
        int right = n -1;
        while(left < right){
            int sum = arr[left] + arr[right];
            int abs = Math.abs(sum);
            if(abs < min){
                min = abs;
                answer[0] = arr[left];
                answer[1] = arr[right];
            }
            if(sum < 0){
                left++;
            }else{
                right--;
            }
        }
        bw.write(answer[0] + " " + answer[1]+"\n");
        bw.flush();
        bw.close();
    }

    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        // 초기화
        min = Integer.MAX_VALUE;
        answer = new int[2];
        int n = Integer.parseInt(br.readLine());
        String[] els = br.readLine().split(" ");
        arr = new int[n];
        for(int i = 0;i<n;i++){
            arr[i] = Integer.parseInt(els[i]);
        }
        Arrays.sort(arr);

        int left = 0;
        int right = n -1;
        while(left < right){
            int sum = arr[left] + arr[right];
            int abs = Math.abs(sum);
            if(abs < min){
                min = abs;
                answer[0] = arr[left];
                answer[1] = arr[right];
            }
            if(sum < 0){
                left++;
            }else{
                right--;
            }
        }
        System.out.println(answer[0] + " " + answer[1]+"\n");
    }
}
