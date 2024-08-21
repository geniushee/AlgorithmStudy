package org.ll;

import org.ll.backjun.samsung.SamsungA2Pipe;
import org.ll.backjun.samsung.SamsungA3Castle;
import org.ll.backjun.samsung.SamsungA4ColorPaper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //세팅
        boolean button = true;
        Scanner sc = new Scanner(System.in);

        while(button){
            System.out.print("문제풀기 : a, 종료 : q\n입력 : ");
            String word = sc.next();
            if(word.equals("q")){
                button = false;
                continue;
            }

            if(!word.equals("a")){
                continue;
            }

            int[][] board = new int[10][10];

            for(int r = 0;r < 10; r++) {
                for(int c= 0 ; c < 10; c++) {
                    board[r][c] = sc.nextInt();
                }
            }

            //시작 시간 기록
            long startTime = System.nanoTime();

            // 연산함수 자리
            SamsungA4ColorPaper data = new SamsungA4ColorPaper(board);
            System.out.println(data.findMin());
//            System.out.println(data);

            // 종료 시간 기록
            long endTime = System.nanoTime();

            // 연산 시간 계산 (나노초 단위)
            long duration = endTime - startTime;

            // 나노초 단위를 밀리초 단위로 변환
            double milliseconds = duration / 1_000_000.0;

            System.out.println("연산 시간: " + duration + " 나노초");
            System.out.println("연산 시간: " + milliseconds + " 밀리초");

        }

        // 종료 시퀀스
        sc.close();
        System.out.println("종료");


//         연산할 함수 호출
//        for(int i = 0; i < 1; i++){
//            //시작 시간 기록
//            long startTime = System.nanoTime();
//
//            // 연산함수 자리
//
//            // 종료 시간 기록
//            long endTime = System.nanoTime();
//
//            // 연산 시간 계산 (나노초 단위)
//            long duration = endTime - startTime;
//
//            // 나노초 단위를 밀리초 단위로 변환
//            double milliseconds = duration / 1_000_000.0;
//
//            System.out.println("연산 시간: " + duration + " 나노초");
//            System.out.println("연산 시간: " + milliseconds + " 밀리초");
//
//            System.out.println("문제 :");
//            System.out.println("답안 :");
//
//            System.out.println("정답? ");
        }


    }

