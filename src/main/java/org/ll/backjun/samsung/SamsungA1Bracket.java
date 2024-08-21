package org.ll.backjun.samsung;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


//Scanner sc = new Scanner(System.in);
//        int a = sc.nextInt();
//        String arg = sc.next();
//        int answer = SamsungA.addBracket(a, arg);
//        System.out.println(answer);

public class SamsungA1Bracket {

    public static int addBracket(int length, String formula) {
        if(length == 1){
            return Integer.parseInt(formula);
        }

        String[] elements = formula.split("");

        res.clear();
        pointer.clear();

        per(elements.length);
//            System.out.println(res.size());
//            System.out.println(res);

        List<Integer> answer = new ArrayList<>();

        for (List<Integer> order : res) {
            String[] temp = Arrays.copyOf(elements, elements.length);
            while (order.size() > 0) {
                Integer start = order.remove(0);
                temp = calOneSet(temp, start - 1);
                order = order.stream()
                        .map(integer -> {
                            if (integer > start) {
                                return integer - 2;
                            }
                            return integer;
                        })
                        .collect(Collectors.toList());
//                System.out.println(order);
            }
            answer.add(Integer.parseInt(temp[0]));
        }
//        System.out.println("답 후보" + answer);
        return Collections.max(answer);
    }

    private static int cal(String e1, String e2, String e3) {
        int res = 0;
        switch (e2) {
            case "+" -> res = Integer.parseInt(e1) + Integer.parseInt(e3);
            case "-" -> res = Integer.parseInt(e1) - Integer.parseInt(e3);
            case "*" -> res = Integer.parseInt(e1) * Integer.parseInt(e3);
        }
        return res;
    }

    private static String[] calOneSet(String[] arr, int startIdx) {
        if (startIdx + 2 >= arr.length) {
            return arr;
        }

        int res = cal(arr[startIdx], arr[startIdx + 1], arr[startIdx + 2]);

        arr[startIdx] = String.valueOf(res);

        String[] newOne = new String[arr.length - 2];

        int i = 0;
        int j = 0;
        while (j < newOne.length) {
            newOne[j++] = arr[i++];
            if (i == startIdx + 1) {
                i += 2;
            }
        }

        return newOne;
    }

    static List<Integer> pointer = new ArrayList<>();

    static List<List<Integer>> res = new ArrayList<>();

    public static void per(int num) {
        // 초기 nums 배열 설정
        Integer[] nums = new Integer[num / 2];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = 1 + i * 2;
        }
//        System.out.println("nums : " + Arrays.toString(nums));

        List<Integer> current = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            current.add(nums[i]);
            pointer.add(i);
            boolean left = false;
            boolean right = false;
            if(i - 1 >= 0){
                pointer.add(i-1);
                left = true;
            }
            if(i + 1 < nums.length){
                pointer.add(i+1);
                right = true;
            }
            newCurrent(nums.length, i, nums, current);
            current.remove(nums[i]);
            pointer.remove(Integer.valueOf(i));
            if(i - 1 > 0 && left){
                pointer.remove(Integer.valueOf(i-1));
            }
            if(i + 1 < nums.length && right){
                pointer.remove(Integer.valueOf(i+1));
            }
        }
    }

    private static void newCurrent(int max, Integer cur, Integer[] nums, List<Integer> current) {
        List<Integer> temp = new ArrayList<>(current);
        if(temp.size() != max){
            Arrays.stream(nums).filter(num -> !current.contains(num)).forEach(temp::add);
        }
        res.add(new ArrayList<>(temp));

        if (pointer.size() == max) {
            return;
        }

        for (int i = 0; i < max; i++) {
            if (pointer.contains(Integer.valueOf(i))) {
                continue;
            }
            current.add(nums[i]);
            pointer.add(i);
            boolean left = false;
            boolean right = false;
            if(i -1 > 0 && !pointer.contains(Integer.valueOf(i-1))){
                pointer.add(i-1);
                left = true;
            }
            if(i + 1 < nums.length && !pointer.contains(Integer.valueOf(i+1))){
                pointer.add(i+1);
                right = true;
            }
            newCurrent(max, i, nums, current);

            current.remove(nums[i]);
            pointer.remove(Integer.valueOf(i));
            if(i -1 > 0 && left){
                pointer.remove(Integer.valueOf(i-1));
            }
            if(i + 1 < nums.length && right){
                pointer.remove(Integer.valueOf(i+1));
            }
        }
    }
}