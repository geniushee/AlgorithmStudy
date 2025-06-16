package org.ll.backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * 문제<br>
 * 선영이는 주말에 할 일이 없어서 새로운 언어 AC를 만들었다. AC는 정수 배열에 연산을 하기 위해 만든 언어이다. 이 언어에는 두 가지 함수 R(뒤집기)과 D(버리기)가 있다.<br>
 * 함수 R은 배열에 있는 수의 순서를 뒤집는 함수이고, D는 첫 번째 수를 버리는 함수이다. 배열이 비어있는데 D를 사용한 경우에는 에러가 발생한다.<br>
 * 함수는 조합해서 한 번에 사용할 수 있다. 예를 들어, "AB"는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다. 예를 들어, "RDD"는 배열을 뒤집은 다음 처음 두 수를 버리는 함수이다.<br>
 * 배열의 초기값과 수행할 함수가 주어졌을 때, 최종 결과를 구하는 프로그램을 작성하시오.<hr>
 * 입력<br>
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. T는 최대 100이다.<br>
 * 각 테스트 케이스의 첫째 줄에는 수행할 함수 p가 주어진다. p의 길이는 1보다 크거나 같고, 100,000보다 작거나 같다.<br>
 * 다음 줄에는 배열에 들어있는 수의 개수 n이 주어진다. (0 ≤ n ≤ 100,000)<br>
 * 다음 줄에는 [x1,...,xn]과 같은 형태로 배열에 들어있는 정수가 주어진다. (1 ≤ xi ≤ 100)<br>
 * 전체 테스트 케이스에 주어지는 p의 길이의 합과 n의 합은 70만을 넘지 않는다.<hr>
 * 출력<br>
 * 각 테스트 케이스에 대해서, 입력으로 주어진 정수 배열에 함수를 수행한 결과를 출력한다. 만약, 에러가 발생한 경우에는 error를 출력한다.<hr>
 * 예제 입력 1<br>
 * 4<br>
 * RDD<br>
 * 4<br>
 * [1,2,3,4]<br>
 * DD<br>
 * 1<br>
 * [42]<br>
 * RRD<br>
 * 6<br>
 * [1,1,2,3,5,8]<br>
 * D<br>
 * 0<br>
 * []<hr>
 * 예제 출력 1<br>
 * [2,1]<br>
 * error<br>
 * [1,2,3,5,8]<br>
 * error<br>
 */
public class AC {
    private static int[] arr;
    private static int front;
    private static int end;
    private static String[] commands;
    private static boolean asc;

    public static void main(String[] arg){

    }

    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        int count = Integer.parseInt(br.readLine());

        for(int i = 0; i< count;i++){
            for(int j = 0; j<3;j++){
                switch (j){
                    case 0 -> {
                        asc = true;
                        front = 0;
                        end = Integer.parseInt(br.readLine()) - 1;
                        if(end < 0){
                            break;
                        }
                    }
                    case 1 -> arr = stringToArray(br.readLine());
                    case 2 -> commands = br.readLine().split("");
                }
            }
            int idx = 0;
            while(idx < commands.length){
                if(commands[idx].equals("D")){
                    if(asc){
                        front++;
                    }else{
                        front--;
                    }
                }else{
                    swap();
                }
            }
            StringBuilder answer = new StringBuilder("[");
            for(int j = 0;j<arr.length;j++){
                answer.append(arr[j]);
            }
            answer.append("]");
            System.out.println(answer.toString());
        }
    }

    public static int[] stringToArray(String str){
        str.replace("[", "");
        str.replace("]", "");
        String[] strArr = str.split(",");
        int[] result = new int[strArr.length];
        for(int i = 0; i < strArr.length;i++){
            result[i] = Integer.parseInt(strArr[i]);
        }
        return result;
    }

    public static void swap(){
        int temp = front;
        front = end;
        end = temp;
        asc = !asc;
    }

}
