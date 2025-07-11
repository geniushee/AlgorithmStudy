package org.ll.backjun;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 문제<br>
 * 하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다. 몇 가지 자연수의 예를 들어 보면 다음과 같다.<br>
 * 3 : 3 (한 가지)<br>
 * 41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)<br>
 * 53 : 5+7+11+13+17 = 53 (두 가지)<br>
 * 하지만 연속된 소수의 합으로 나타낼 수 없는 자연수들도 있는데, 20이 그 예이다. 7+13을 계산하면 20이 되기는 하나 7과 13이 연속이 아니기에 적합한 표현이 아니다. 또한 한 소수는 반드시 한 번만 덧셈에 사용될 수 있기 때문에, 3+5+5+7과 같은 표현도 적합하지 않다.<br>
 * 자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하는 프로그램을 작성하시오.<hr>
 * 입력<br>
 * 첫째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 4,000,000)<hr>
 * 출력<br>
 * 첫째 줄에 자연수 N을 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 출력한다.<hr>
 * 예제 입력 1 <br>
 * 20<hr>
 * 예제 출력 1<br>
 * 0<hr>
 * 예제 입력 2<br>
 * 3<hr>
 * 예제 출력 2<br>
 * 1<hr>
 * 예제 입력 3<br>
 * 41<hr>
 * 예제 출력 3<br>
 * 3<hr>
 * 예제 입력 4<br>
 * 53<hr>
 * 예제 출력 4<br>
 * 2
 */
public class ConsecutiveSum {

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int target = Integer.parseInt(br.readLine());
        boolean[] isNotPrime = new boolean[target + 1];
        List<Integer> primes = new ArrayList<>();
        for(int i = 2; i<=target; i++){
            if(!isNotPrime[i]){
                primes.add(i);
                for(int j = i *2;j <=target; j += i){
                    isNotPrime[j] = true;
                }
            }
        }

        // 투 포인터 알고리즘 - 연속이니까 그냥 한칸씩 움직이면 된다.
        int start = 0, end = 0, sum = 0, count =0;
        while(true){
            if(sum >= target){
                sum -= primes.get(start++);
            }else if(end == primes.size()){
                break;
            }else{
                sum += primes.get(end++);
            }
            if(sum == target){
                count++;
            }
        }

        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        final int target = Integer.parseInt(br.readLine());
        boolean[] isNotPrime = new boolean[target + 1];
        List<Integer> primes = new ArrayList<>();
        for(int i = 2; i<=target; i++){
            if(!isNotPrime[i]){
                primes.add(i);
                for(int j = i *2;j <=target; j += i){
                    isNotPrime[j] = true;
                }
            }
        }

        // 투 포인터 알고리즘 - 연속이니까 그냥 한칸씩 움직이면 된다.
        int start = 0, end = 0, sum = 0, count =0;
        while(true){
            if(sum >= target){
                sum -= primes.get(start++);
            }else if(end == primes.size()){
                break;
            }else{
                sum += primes.get(end++);
            }
            if(sum == target){
                count++;
            }
        }

        System.out.println(count);
    }
}
