package org.ll.backjun;

import java.io.*;
import java.util.*;

/**
 *문제<br>
 * 모든 유리수는 다음과 같이 특정한 수가 반복되는 순환 소수 형태로 표현될 수 있다.<br>
 * \[\frac{1}{3} = \frac{3}{9} = 0.3333333\dots = 0.\overline{3} \\ \frac{1}{7} = \frac{142857}{999999} = 0.14285714285714\dots = 0.\overline {142857} \\ \frac{3}{14} = \frac{2142855}{9999990} = 0.214285714285714 = 0.2\overline{142857}\] <br>
 * 위에서 분모가 9와 0으로 이루어진 부분을 보면 알 수 있듯이 순환 소수가 주어졌을 때 이를 분수로 바꾸는 방법은 공식화 되어있다. 하지만 공식의 형태가 아닌 분수가 주어졌을 때 이를 이용하기는 어려우므로 일반적으로 분수를 순환 소수로 바꾸는 가장 쉬운 방법은 나눗셈을 반복하는 것이다. 하지만 이 작업은 손으로 하기엔 너무 어려우니 이를 수행하는 프로그램을 작성해보자.<hr>
 * 입력<br>
 * 입력의 첫 번째 줄에는 테스트 케이스의 수 T (1 ≤ T ≤ 15,000)가 주어진다.<br>
 * 이후 각 테스트 케이스에 대해 한 줄에 분자와 분모 (0 ≤ 분자 < 1024, 0 < 분모 < 1024)가 공백으로 구분되어 정수로 주어진다.<hr>
 * 출력<br>
 * 한 줄에 한 개씩 각 테스트 케이스에 대한 순환소수를 출력한다(순환부는 괄호로 감싸서 표시한다).<hr>
 * 예제 입력 1 <br>
 * 6<br>
 * 1 2<br>
 * 1 7<br>
 * 10 5<br>
 * 11 5<br>
 * 995 476<br>
 * 991 994<hr>
 * 예제 출력 1<br>
 * 0.5(0)<br>
 * 0.(142857)<br>
 * 2.(0)<br>
 * 2.2(0)<br>
 * 2.09(033613445378151260504201680672268907563025210084)<br>
 * 0.9(969818913480885311871227364185110663983903420523138832997987927565392354124748490945674044265593561368209255533199195171026156941649899396378269617706237424547283702213279678068410462776659959758551307847082494)<br>
 */
public class FractionToDecimal {

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringBuilder answer = new StringBuilder();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int[] res;
            // 정수부분 계산
            res = divide(a, b);
            answer.append(res[0]);
            answer.append(".");
            a = res[1] * 10;

            // 0. init 목:{인덱스, 나머지}
            Map<Integer, List<int[]>> map = new HashMap<>();
            // 소수부분
            List<Integer> ground = new ArrayList<>();

            int start = -1;
            int end = -1;
            int k = 0;
            boolean find = false;
            while (!find) {
//                System.out.printf("분모 : %d, 분자 : %d\n", a, b);
                // 1. 나눈다.
                res = divide(a, b);
                // 나눈 값을 적용
                if (res[0] == 0 && res[1] == 0) {
                    // 나누어 떨어진다.
                    break;
                }
                ground.add(res[0]);


                // 2. 순환이 있는지 확인한다. => 패턴이 있다 = 몫과 나머지가 일치한다.
                // 몫이 같은 친구들을 불러온다.
                List<int[]> list = map.getOrDefault(res[0], new ArrayList<>());
                for (int[] arr : list) {
                    // 나머지가 같다.
                    if (res[1] == arr[1]) {
                        start = arr[0];
                        end = k;
                        find = true;
                        break;
                    }
                }
                list.add(new int[]{k++, res[1]});
                map.put(res[0], list);
                // 분모 갱신
                a = res[1] * 10;
                // 3. 1번으로
            }
//            System.out.println(ground);
            // 답안지 작성
            if (start == -1) {
                for (Integer num : ground) {
                    answer.append(num);
                }
                answer.append("(0)");
            } else {
                for (int x = 0; x < start; x++) {
                    answer.append(ground.get(x));
                }
                answer.append("(");
                for (int x = start; x < end; x++) {
                    answer.append(ground.get(x));
                }
                answer.append(")");
            }
            bw.write(answer + "\n");
            bw.flush();
            bw.close();

        }
    }

    public static int[] divide(int son, int parent) {
        // 몫, 나머지
        int[] res = new int[2];
        // 0은 나눌 수 없다.
        if (son == 0) {
            return res;
        }
        res[0] = son / parent;
        res[1] = son % parent;
//        System.out.println(Arrays.toString(res));
        return res;
    }

    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringBuilder answer = new StringBuilder();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int[] res;
            // 정수부분 계산
            res = divide(a, b);
            answer.append(res[0]);
            answer.append(".");
            a = res[1] * 10;

            // 0. init 목:{인덱스, 나머지}
            Map<Integer, List<int[]>> map = new HashMap<>();
            // 소수부분
            List<Integer> ground = new ArrayList<>();

            int start = -1;
            int end = -1;
            int k = 0;
            boolean find = false;
            while (!find) {
//                System.out.printf("분모 : %d, 분자 : %d\n", a, b);
                // 1. 나눈다.
                res = divide(a, b);
                // 나눈 값을 적용
                if (res[0] == 0 && res[1] == 0) {
                    // 나누어 떨어진다.
                    break;
                }
                ground.add(res[0]);


                // 2. 순환이 있는지 확인한다. => 패턴이 있다 = 몫과 나머지가 일치한다.
                // 몫이 같은 친구들을 불러온다.
                List<int[]> list = map.getOrDefault(res[0], new ArrayList<>());
                for (int[] arr : list) {
                    // 나머지가 같다.
                    if (res[1] == arr[1]) {
                        start = arr[0];
                        end = k;
                        find = true;
                        break;
                    }
                }
                list.add(new int[]{k++, res[1]});
                map.put(res[0], list);
                // 분모 갱신
                a = res[1] * 10;
                // 3. 1번으로
            }
//            System.out.println(ground);
            // 답안지 작성
            if (start == -1) {
                for(Integer num : ground){
                    answer.append(num);
                }
                answer.append("(0)");
            } else {
                for(int x = 0;x<start;x++){
                    answer.append(ground.get(x));
                }
                answer.append("(");
                for (int x = start; x < end; x++) {
                    answer.append(ground.get(x));
                }
                answer.append(")");
            }
            System.out.println(answer);

        }

    }
}
