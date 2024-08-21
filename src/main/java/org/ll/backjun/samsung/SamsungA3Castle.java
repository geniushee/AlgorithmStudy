package org.ll.backjun.samsung;

import java.util.*;

public class SamsungA3Castle {
    int enemyCount;
    int row;
    int col;
    int[][] board;
    boolean[] castle;
    int range; // 궁수 사거리
    int count = Integer.MIN_VALUE;

    public SamsungA3Castle(int row, int col, int[][] board, int range) {
        this.row = row;
        this.col = col;
        this.board = board;
        for (int[] r : board) {
            for (int c : r) {
                if (c == 1) enemyCount++;
            }
        }
        castle = new boolean[this.board[0].length];
        this.range = range;
    }

    // 게임 부분
    public int maxDefeatEnemy(){
        setShooter(0,0,new boolean[col]);
        for(boolean[] archers : archerPlacements){
            int[][] newBoard = newBoard();
            int kill = 0;
            int enemySave = enemyCount;
            while(enemyCount > 0){
                kill = attack(archers, kill);
                moveEnemy();
//                System.out.println(toString() + "\n적군 숫자 : "+enemyCount);
            }

            if(kill > count){
                count = kill;
                if(enemySave == count){
                    return count;
                }
            }
            enemyCount = enemySave;
            board = newBoard;
        }

        return count;
    }

    private int[][] newBoard() {
        int[][] _new = new int[row][col];
        for(int i = 0; i < row; i++){
            _new[i] = Arrays.copyOf(board[i], col);
        }
        return _new;
    }

    private void moveEnemy(){
        for(int point : board[row - 1]){
            if(point == 1){
                enemyCount--;
            }
        }
        for(int i = row -1; i > 0; i--){
            board[i] = board[i -1];
        }
        board[0] = new int[col];
    }

    // 궁수의 공격 부분
    public void showTarget() {
        setShooter(0, 0, new boolean[col]);
        for (boolean[] archers : archerPlacements) {
            System.out.println("궁수 : " + Arrays.toString(archers));
            attack(archers, 0);
            System.out.println("타깃 확인");
            for (int[] target : targets) {
                System.out.println(Arrays.toString(target));
            }
            targets.clear();
        }
    }



    // 타깃
    List<int[]> targets = new ArrayList<>();

    // 슈터 공격
    // 슈터 위치는 n+1, idx
    // 공격은 row - 1 -> row - 1/col - 1 -> row -2/col -> row-1/col + 1
    private int attack(boolean[] archers, int curKill) {
        boolean[][] map = new boolean[row][col];
        Queue<int[]> points = new LinkedList<>();

        for (int i = 0; i < archers.length; i++) {
            if (archers[i]) {
//                System.out.println(i + 1 + "번째 타깃 찾기");
                // 궁수의 바로 앞부터 시작, 거리는 1
                search(row - 1, i, 1, map, points);

                // 맵 초기화
                points.clear();
                map = new boolean[row][col];
            }
        }

        // 공격
        for(int[] target:targets){
            int x = target[0];
            int y = target[1];
            if(board[x][y] == 1){
                board[x][y] = 0;
                curKill++;
                enemyCount--;
            }
        }
        targets.clear();

        return curKill;
    }

    private void search(int row, int col, int distance, boolean[][] map, Queue<int[]> points) {
//        System.out.println("확인위치 : " + row + ", " + col);
        // 적 체크
        if (board[row][col] == 1) {
            // 타깃 저장
//            System.out.println("타깃 저장");
            targets.add(new int[]{row, col});
            return;
        }

        // 방문 체크
        map[row][col] = true;

        // 거리 확인
        if (distance + 1 <= range) {
            if (col - 1 >= 0 && !map[row][col - 1]) {
                // 왼쪽
                points.add(new int[]{row, col - 1, distance + 1});
            }
            if (row - 1 >= 0 && !map[row - 1][col]) {
                // 위
                points.add(new int[]{row - 1, col, distance + 1});
            }
            if (col + 1 < this.col && !map[row][col + 1]) {
                // 오른쪽
                points.add(new int[]{row, col + 1, distance + 1});
            }
        }
        int _row = row;
        int _col = col;
        int _d = distance;

        while (map[_row][_col]) {
            int[] point = points.poll();

            if(point == null){
                return ;
            }

            _row = point[0];
            _col = point[1];
            _d = point[2];
        }

        search(_row, _col, _d, map, points);

    }


    // 슈터 자리 선정
    List<boolean[]> archerPlacements = new ArrayList<>();

    public void listArchers() {
        setShooter(0, 0, new boolean[col]);
        for (boolean[] bs : archerPlacements) {
            System.out.println(Arrays.toString(bs));
        }
    }

    private void setShooter(int idx, int curArchers, boolean[] curState) {
        if (curArchers == 3) {
            archerPlacements.add(Arrays.copyOf(curState, curState.length));
            return;
        }

        if (idx >= curState.length) {
            return;
        }

        for (int i = idx; i < curState.length; i++) {
            curState[i] = true;
            setShooter(i + 1, curArchers + 1, curState);
            curState[i] = false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // 보드판
        sb.append("""
                ---보드 현황판---""").append("\n");
        for (int[] row : board) {
            sb.append(Arrays.toString(row)).append("\n");
        }

        // 궁수배치
//        for(boolean b : castle){
//            if(b){
//                sb.append("1 ");
//            }else{
//                sb.append("0 ");
//            }
//        }
//        sb.append("\n");

        return sb.toString();
    }
}
