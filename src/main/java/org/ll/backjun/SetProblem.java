package org.ll.backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 문제<br>
 * 비어있는 공집합 S가 주어졌을 때, 아래 연산을 수행하는 프로그램을 작성하시오.<br>
 * <p>
 * add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.<br>
 * remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.<br>
 * check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)<br>
 * toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)<br>
 * all: S를 {1, 2, ..., 20} 으로 바꾼다.<br>
 * empty: S를 공집합으로 바꾼다.
 * <hr>
 * 입력<br>
 * 첫째 줄에 수행해야 하는 연산의 수 M (1 ≤ M ≤ 3,000,000)이 주어진다.<br>
 * 둘째 줄부터 M개의 줄에 수행해야 하는 연산이 한 줄에 하나씩 주어진다.
 * <hr>
 * 출력<br>
 * check 연산이 주어질때마다, 결과를 출력한다.
 */
public class SetProblem {
    private static int set = 0;

    public static List<Integer> run(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        List<Integer> result = new ArrayList<>();
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line = br.readLine();
        // 연산 개수 M
        int M = Integer.parseInt(line.trim());

        // input 처리
        while((line = br.readLine())!=null) {
            String[] oper = line.split(" ");
            if(oper.length == 1){
                oper = Arrays.copyOf(oper, 2);
                oper[1] = "";
            }
            System.out.println(oper[0] + " " + oper[1]);
            switch (oper[0]) {
                case "add" -> add(Integer.parseInt(oper[1]));
                case "remove" -> remove(Integer.parseInt(oper[1]));
                case "check" -> {
                    if (contain(Integer.parseInt(oper[1]))) {
                        result.add(1);
//                        bw.write(1);
                    } else {
                        result.add(0);
                    }
                    ;
                }
                case "toggle" -> toggle(Integer.parseInt(oper[1]));
                case "all" -> all();
                case "empty" -> empty();
            }
            System.out.println(Integer.toBinaryString(set));
        }
        return result;
    }


    private static void empty() {
        set = 0;
    }

    private static void all() {
        set = 0x1ffffe;
    }

    private static void toggle(int num) {
        if (!contain(num)) {
            add(num);
        } else {
            remove(num);
        }
    }

    private static void remove(int num) {
        int mask = 1 << num;
        set &= ~mask;
    }

    private static void add(int num) {
        int mask = 1 << num;
        set |= mask;
    }

    /**
     * num자리가 1인가?
     */
    private static boolean contain(int num) {
        int mask = 1 << num;
        return (set & mask) != 0;
    }
}

    // 메모리초과
//    private static int[] set = new int[20];
//    private static int size = 0;
//
//    public static List<Integer> run(String input) throws IOException {
//        BufferedReader br = new BufferedReader(new StringReader(input));
//        List<Integer> result = new ArrayList<>();
////        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        String line = br.readLine();
//        // 연산 개수 M
//        int M = Integer.parseInt(line.trim());
//        String[][] operators = new String[M][2];
//
//        // input 처리
//        for (int i = 0; i < M; i++) {
//            line = br.readLine();
//            String[] arr = line.split(" ");
//            if (arr.length != 2) {
//                arr = Arrays.copyOf(arr, 2);
//                arr[1] = "";
//            }
//            operators[i] = arr;
//        }
//
//        for (String[] oper : operators) {
//            System.out.println(oper[0]+" "+oper[1]);
//            switch (oper[0]) {
//                case "add" -> add(Integer.parseInt(oper[1]));
//                case "remove" -> remove(Integer.parseInt(oper[1]));
//                case "check" -> {
//                    if (contain(Integer.parseInt(oper[1])) != -1) {
//                        result.add(1);
////                        bw.write(oper[1]);
//                    } else {
//                        result.add(0);
//                    }
//                    ;
//                }
//                case "toggle" -> toggle(Integer.parseInt(oper[1]));
//                case "all" -> set = all();
//                case "empty" -> set = empty();
//            }
//        }
//
////        bw.flush();
////        bw.close();
//        System.out.println(result);
//        return result;
//    }
//
//    private static int[] empty() {
//        size=0;
//        return new int[20];
//    }
//
//    private static int[] all() {
//        size=20;
//        int[] newArr = new int[20];
//        for (int i = 0; i < 20; i++) {
//            newArr[i] = i + 1;
//        }
//        return newArr;
//    }
//
//    private static void toggle(int num) {
//        if (add(num)) {
//            return;
//        }
//        remove(num);
//    }
//
//    private static boolean remove(int num) {
//        int index = contain(num);
//        if (index == -1) {
//            return false;
//        }
//        if (index == size - 1) {
//            set[--size] = 0;
//        } else if (index != size - 1) {
//            for (int i = index; i < size - 1; i++) {
//                set[i] = set[i + 1];
//            }
//            set[19] = 0;
//            size--;
//        }
//        return true;
//    }
//
//    private static boolean add(int num) {
//        if (contain(num) != -1) {
//            return false;
//        }
//        set[size++] = num;
//        return true;
//    }
//
//    /**
//     * 포함되어 있으면 index 반환, 없으면 -1.
//     */
//    private static int contain(int num) {
//        if (size == 0) {
//            return -1;
//        }
//        for (int i = 0; i < size; i++) {
//            if ((set[i] ^ num) == 0) {
//                return i;
//            }
//        }
//        return -1;
//    }

