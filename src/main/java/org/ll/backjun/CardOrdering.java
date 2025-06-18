package org.ll.backjun;

import java.io.*;
import java.util.Arrays;

/**
 *문제<br>
 * 정렬된 두 묶음의 숫자 카드가 있다고 하자. 각 묶음의 카드의 수를 A, B라 하면 보통 두 묶음을 합쳐서 하나로 만드는 데에는 A+B 번의 비교를 해야 한다. 이를테면, 20장의 숫자 카드 묶음과 30장의 숫자 카드 묶음을 합치려면 50번의 비교가 필요하다.<br>
 * 매우 많은 숫자 카드 묶음이 책상 위에 놓여 있다. 이들을 두 묶음씩 골라 서로 합쳐나간다면, 고르는 순서에 따라서 비교 횟수가 매우 달라진다. 예를 들어 10장, 20장, 40장의 묶음이 있다면 10장과 20장을 합친 뒤, 합친 30장 묶음과 40장을 합친다면 (10 + 20) + (30 + 40) = 100번의 비교가 필요하다. 그러나 10장과 40장을 합친 뒤, 합친 50장 묶음과 20장을 합친다면 (10 + 40) + (50 + 20) = 120 번의 비교가 필요하므로 덜 효율적인 방법이다.<br>
 * N개의 숫자 카드 묶음의 각각의 크기가 주어질 때, 최소한 몇 번의 비교가 필요한지를 구하는 프로그램을 작성하시오.<hr>
 * 입력<br>
 * 첫째 줄에 N이 주어진다. (1 ≤ N ≤ 100,000) 이어서 N개의 줄에 걸쳐 숫자 카드 묶음의 각각의 크기가 주어진다. 숫자 카드 묶음의 크기는 1,000보다 작거나 같은 양의 정수이다.<hr>
 * 출력<br>
 * 첫째 줄에 최소 비교 횟수를 출력한다.<hr>
 * 예제 입력 1 <br>
 * 3<br>
 * 10<br>
 * 20<br>
 * 40<hr>
 * 예제 출력 1<br>
 * 100
 * <hr>
 * 힙을 사용해서 개선할 수 있다.
 */
public class CardOrdering {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 초기화
        String line = br.readLine();
        int[] cards = new int[Integer.parseInt(line)];
        int pointer = 0;
        int sum = 0;
        int partSum = 0;
        for(int i = 0; i< cards.length;i++){
            cards[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(cards);


        // 계산 가장 작은 애들
        while(pointer != cards.length -1){
            partSum = cards[pointer] + cards[pointer + 1];
            sum += partSum;
            cards[pointer + 1] = partSum;
            cards[pointer++] = 0;
            Arrays.sort(cards);
        }

        bw.write(sum+"\n");
        bw.flush();
        bw.close();
    }
    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        // 초기화
        String line = br.readLine();
        int[] cards = new int[Integer.parseInt(line)];
        int pointer = 0;
        int sum = 0;
        int partSum = 0;
        for(int i = 0; i< cards.length;i++){
            cards[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(cards);


        // 계산 가장 작은 애들
        while(pointer != cards.length -1){
            partSum = cards[pointer] + cards[pointer + 1];
            sum += partSum;
            cards[pointer++] = 0;
            cards[pointer] = partSum;
            sort(pointer, cards);
        }

        System.out.println(sum);
    }

    public static void sort(int start, int[] target){
        for(int i = start + 1; i < target.length; i++){
            if(target[i -1] > target[i]){
                int temp = target[i];
                target[i] = target[i-1];
                target[i-1] = temp;
            }else{
                return;
            }
        }
    }
}
