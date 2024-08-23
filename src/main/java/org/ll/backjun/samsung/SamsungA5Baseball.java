package org.ll.backjun.samsung;

import javax.swing.plaf.IconUIResource;
import java.util.*;

public class SamsungA5Baseball {
    private final Ground ground;
    private int out; // 아웃 카운트
    private int totalInning;
    private int[][] innings;
    private int[] players = new int[9];

    private int maxScore = Integer.MIN_VALUE;

    private SamsungA5Baseball() {
        ground = new Ground();
    }

    public SamsungA5Baseball(int innings, int[][] expects) {
        this();
        totalInning = innings;
        this.innings = expects;
    }

    public int findMax() {
        boolean[] select = new boolean[9];
        select[3] = true;
        players[3] = 1;
        players(2, select);
        return maxScore;
    }

    public void play() {
        ground.startNewGame();
        // 첫 타자
        int order = 0;
        int batter;
        // 이닝 수만큼 진행
        for (int i = 0; i < totalInning; ) {
            while (true) {
                batter = players[order++];
                int runs = innings[i][batter - 1];
                if (runs == 0) {
                    out++;
                    if (out == 3) {
                        ground.startInning();
                        out = 0;
                        i++;
                        break;
                    }
                } else {
                    hit(runs);
                }
                if (order == 9) {
                    order = 0;
                }
            }

        }

        maxScore = Math.max(maxScore, ground.getScore());
    }

    private void players(int player, boolean[] select) {
        // 종료 조건
        if (player == 10) {
            play();
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (select[i]) {
                continue;
            }
            select[i] = true;
            players[i] = player;
            players(player + 1, select);
            select[i] = false;
        }
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

        public void startNewGame() {
            score = 0;
        }

        public void startInning() {
            bases = new boolean[3];
        }


        // hit를 반복을 과하게 진행하여 시간초과
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