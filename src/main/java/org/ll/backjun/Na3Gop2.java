package org.ll.backjun;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 문제<br>
 * 나3곱2 게임은 정수 하나를 이용한다. 가장 먼저, 정수 x로 시작하고, 연산을 N-1번 적용한다. 적용할 수 있는 연산은 두 가지 있고, 아래와 같다.<br>
 * 나3: x를 3으로 나눈다. x는 3으로 나누어 떨어져야 한다.<br>
 * 곱2: x에 2를 곱한다.<br>
 * 나3곱2 게임을 진행하면서, 만든 수를 모두 기록하면 수열 A를 만들 수 있다. 예를 들어, x = 9, N = 6이고, 적용한 연산이 곱2, 곱2, 나3, 곱2, 나3인 경우에 A = [9, 18, 36, 12, 24, 8] 이다.<br>
 * 수열 A의 순서를 섞은 수열 B가 주어졌을 때, 수열 A를 구해보자.<hr>
 * 입력<br>
 * 첫째 줄에 수열의 크기 N(2 ≤ N ≤ 100)이 주어진다. 둘째 줄에는 수열 B가 주어진다. B에 포함된 원소는 1018 보다 작거나 같은 자연수이다.<hr>
 * 출력<br>
 * 나3곱2 게임의 결과 수열 A를 출력한다. 항상 정답이 존재하는 경우에만 입력으로 주어지며, 가능한 정답이 여러가지인 경우에는 아무거나 출력한다.<hr>
 * 예제 입력 1 <br>
 * 6<br>
 * 4 8 6 3 12 9<hr>
 * 예제 출력 1 <br>
 * 9 3 6 12 4 8<hr>
 * 예제 입력 2 <br>
 * 4<br>
 * 42 28 84 126<hr>
 * 예제 출력 2 <br>
 * 126 42 84 28
 */
public class Na3Gop2 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Map<Integer, PriorityQueue<Long>> map = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        long[] answer = new long[n];
        int maxMod = 0;
        String[] nums = br.readLine().split(" ");
        for(String num : nums){
            Long i = Long.valueOf(num);
            int mod = count3(i);
            PriorityQueue<Long> heap = map.getOrDefault(mod, new PriorityQueue<>());
            heap.offer(i);
            map.put(mod, heap);
            if(mod > maxMod){
                maxMod = mod;
            }
        }

        int i = 0;
        while(maxMod >= 0){
            PriorityQueue<Long> heap = map.getOrDefault(maxMod, null);
            if(heap != null){
                while(!heap.isEmpty()){
                    answer[i++] = heap.poll();
                }
            }
            maxMod--;
        }
        for(int j = 0; j<n;j++){
            bw.write(answer[j]+"");
            if(j != n -1){
                bw.write(" ");
            }else{
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
    }
    public static int count3(long num){
        int count = 0;
        long mod = 0;
        while(true){
            mod = num % 3;
            if(mod == 0){
                num /= 3;
                count++;
            }else{
                break;
            }
        }
        return count;
    }

    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        Map<Integer, PriorityQueue<Long>> map = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        long[] answer = new long[n];
        int maxMod = 0;
        String[] nums = br.readLine().split(" ");
        for(String num : nums){
            Long i = Long.valueOf(num);
            int mod = count3(i);
            PriorityQueue<Long> heap = map.getOrDefault(mod, new PriorityQueue<>());
            heap.offer(i);
            map.put(mod, heap);
            if(mod > maxMod){
                maxMod = mod;
            }
        }

        System.out.println(map.toString() + " / " + maxMod);

        int i = 0;
        while(maxMod >= 0){
            PriorityQueue<Long> heap = map.getOrDefault(maxMod, null);
            if(heap != null){
                while(!heap.isEmpty()){
                    answer[i++] = heap.poll();
                }
            }
            maxMod--;
        }

        System.out.println(Arrays.toString(answer));
    }

}
