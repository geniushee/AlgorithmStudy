package org.ll.backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 문제<br>
 * 길이가 N인 수열이 주어졌을 때, 그 수열의 합을 구하려고 한다. 하지만, 그냥 그 수열의 합을 모두 더해서 구하는 것이 아니라, 수열의 두 수를 묶으려고 한다. 어떤 수를 묶으려고 할 때, 위치에 상관없이 묶을 수 있다. 하지만, 같은 위치에 있는 수(자기 자신)를 묶는 것은 불가능하다. 그리고 어떤 수를 묶게 되면, 수열의 합을 구할 때 묶은 수는 서로 곱한 후에 더한다.
 * 예를 들면, 어떤 수열이 {0, 1, 2, 4, 3, 5}일 때, 그냥 이 수열의 합을 구하면 0+1+2+4+3+5 = 15이다. 하지만, 2와 3을 묶고, 4와 5를 묶게 되면, 0+1+(2*3)+(4*5) = 27이 되어 최대가 된다.
 * 수열의 모든 수는 단 한번만 묶거나, 아니면 묶지 않아야한다.
 * 수열이 주어졌을 때, 수열의 각 수를 적절히 묶었을 때, 그 합이 최대가 되게 하는 프로그램을 작성하시오.<hr>
 * 입력<br>
 * 첫째 줄에 수열의 크기 N이 주어진다. N은 50보다 작은 자연수이다. 둘째 줄부터 N개의 줄에 수열의 각 수가 주어진다. 수열의 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.<hr>
 * 출력<br>
 * 수를 합이 최대가 나오게 묶었을 때 합을 출력한다. 정답은 항상 231보다 작다.<hr>
 * 예제 입력 1 <br>
 * 4<br>
 * -1<br>
 * 2<br>
 * 1<br>
 * 3<hr>
 * 예제 출력 1<br>
 * 6<hr>
 * 예제 입력 2<br>
 * 6<br>
 * 0<br>
 * 1<br>
 * 2<br>
 * 4<br>
 * 3<br>
 * 5<hr>
 * 예제 출력 2<br>
 * 27<hr>
 * 예제 입력 3<br>
 * 1<br>
 * -1<hr>
 * 예제 출력 3<br>
 * -1<hr>
 * 예제 입력 4<br>
 * 3<br>
 * -1<br>
 * 0<br>
 * 1<hr>
 * 예제 출력 4<br>
 * 1<hr>
 * 예제 입력 5<br>
 * 2<br>
 * 1<br>
 * 1<hr>
 * 예제 출력 5<br>
 * 2
 */
public class BindingNumber {
    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        int n = Integer.parseInt(br.readLine());
        // 양수
        PriorityQueue<Integer> plus = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        // 음수
        PriorityQueue<Integer> minus = new PriorityQueue<>();
        int sum = 0;
        int zero = 0;
        for(int i = 0; i<n;i++){
            int num = Integer.parseInt(br.readLine());
            if(num == 0){
                zero++;
                continue;
            }
            if(num == 1){
                sum += num;
            }else if(num > 0){
                plus.offer(num);
            }else{
                minus.offer(num);
            }
        }

        while(plus.size() > 1){
            int n1 = plus.poll();
            int n2 = plus.poll();
            sum += n1 * n2;
        }
        if(!plus.isEmpty()){
            sum += plus.poll();
        }

        while(minus.size() > 1){
            int n1 = minus.poll();
            int n2 = minus.poll();
            sum += n1 * n2;
        }
        if(zero == 0 && !minus.isEmpty()){
            sum += minus.poll();
        }

        System.out.println(sum);
    }
}
