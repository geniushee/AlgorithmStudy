package org.ll.backjun.samsung;

import javax.swing.plaf.IconUIResource;
import java.util.*;

public class SamsungA5Baseball {
    private final Ground ground;
    private int out; // 아웃 카운트
    private int totalInning;
    private int[][] innings;
    private List<List<Integer>> players = new ArrayList<>();

    private int maxScore = Integer.MIN_VALUE;

    private SamsungA5Baseball() {
        ground = new Ground();
    }

    public SamsungA5Baseball(int innings, int[][] expects) {
        this();
        totalInning = innings;
        this.innings = expects;
        boolean[] visited = new boolean[10];
        List<Integer> start = new ArrayList<>();
        start.add(2);
        players(start);
    }

    public int findMax(){
        ArrayList<Queue<Integer>> list = makeOrder();
//        ArrayList<Queue<Integer>> list = new ArrayList<>();
//        Queue<Integer> q = new LinkedList<>();
//        q.add(2);
//        q.add(3);
//        q.add(4);
//        q.add(1);
//        q.add(6);
//        q.add(7);
//        q.add(8);
//        q.add(9);
//        q.add(5);
//        list.add(q);
        int i = 1;
        while(!list.isEmpty()){
//            System.out.println(i+"번째 게임");
            ground.startNewGame();
            int score = play(list.remove(0));
            maxScore = Math.max(maxScore,score);
            i++;
        }
        return maxScore;
    }

    public int play(Queue<Integer> queue){
//        System.out.println("선수 목록 : " + queue);
        for(int i = 0; i < totalInning;){
            Integer playerNum = queue.poll();
//            System.out.println(playerNum+"번 선수");
            int runs = innings[i][playerNum - 1];
//            System.out.println(runs + "루타");
            queue.add(playerNum);
            if(runs == 0){
                out++;
                if(out == 3){
//                    System.out.println(out + "아웃 -> 새 이닝");
                    ground.startInning();
                    out = 0;
                    i++;
                }
            }else{
                ground.hit(runs);
//                System.out.println("score : " + ground.getScore());
            }
        }
//        System.out.println("score : " + ground.getScore());
        return ground.getScore();
    }

    public ArrayList<Queue<Integer>> makeOrder() {
        ArrayList<Queue<Integer>> totalQ = new ArrayList<>();
        for (List<Integer> list : players) {
            int i = 0;
            while (i < 8) {
            Queue<Integer> q = new LinkedList<>();
                int d = i;
                for(int j = 0;j<9;j++) {
                    if (j == 3) {
                        q.add(1);
                    } else {
                        q.add(list.get(d % 8));
                        d++;
                    }
                }
                totalQ.add(new LinkedList<>(q));
                i++;
            }
        }
        return totalQ;
        // 검증
//        System.out.println(totalQ.size());
    }

    private void players(List<Integer> order) {
        // 종료 조건
        if (order.size() == 8) {
            players.add(new ArrayList<>(order));
            return;
        }

        // 원순열 체크
        // 1,2
        // 1,2,3
        // 1,3,2
        // 1,2,3,4
        // 1,2,4,3
        // 1,3,2,4
        // 1,3,4,2
        // 1,4,2,3
        // 1,4,3,2
        for (int i = 3; i < 10; i++) {
            if (order.contains(i)) {
                continue;
            }
            order.add(i);
            players(order);
            order.remove(Integer.valueOf(i));
        }
    }

    public void showPlayers() {
        System.out.println(players.size());
    }

    public void hit(int runs) {
        ground.hit(runs);
    }

    public void showScore() {
        System.out.println("점수 : " + ground.getScore());
    }


    private static class Ground {
        private boolean[] bases;
        private int score;

        public Ground() {
            bases = new boolean[3];
            score = 0;
        }

        public void startNewGame(){
            score = 0;
        }

        public void startInning() {
            for (int i =0; i < 3; i++) {
                bases[i] = false;
            }
        }

        public void hit(int runs) {
            // 타자 설정
            boolean batter = true;
            // 몇루타 인가?
            while (runs > 0) {
                // 타자 이동
                for (int base = 2; base >= 0; base--) {
                    if (base == 2) {
                        if (bases[base]) {
                            score++;
                            bases[base] = false;
                        }
                    } else if (bases[base]) {
                        bases[base + 1] = true;
                        bases[base] = false;
                    }
                }
                // 타자는 처음만 나간다.
                if (batter) {
                    bases[0] = true;
                    batter = false;
                }
                runs--;
            }
        }

        public int getScore() {
            return score;
        }
    }
}