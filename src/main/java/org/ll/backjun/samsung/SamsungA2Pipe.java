package org.ll.backjun.samsung;

import java.util.Arrays;

public class SamsungA2Pipe {

    private int size;
    private int[][] board;

    private int count = 0;

    public SamsungA2Pipe(int size, int[][] board){
        this.size = size + 2;
        this.board = new int[this.size][this.size];

        for(int r = 0; r < size + 2; r++){
            for(int c = 0; c<size + 2; c++){
                if(isEdge(r, c)){
                    this.board[r][c] = 1;
                }else {
                    this.board[r][c] = board[r-1][c-1];
                }
            }
        }
    }

    public int maxMove(){
        int idx = 0;
        move(1,1,1,2);

        return count;
    }

    private void move(int row1, int col1, int row2, int col2) {
        System.out.println("현재 위치 : " + "["+row1+","+col1+"]["+row2+","+col2+"]");
        if(isEdge(row2, col2)){
            System.out.println("엣지");
            return;
        }
        if(isEnd(row2, col2)){
            System.out.println("추가");
            count++;
            return;
        }

        int _row1, _col1, _row2, _col2;

        switch (position(row1, col1, row2, col2)){
            case 1 -> {
                System.out.println("현재 상태 : " + "가로");
                if(!isBlockToH(row2, col2)){
                    _row1 = row2;
                    _col1 = col2;
                    _row2 = row2;
                    _col2 = col2 + 1;
                    move(_row1, _col1, _row2, _col2);
                }
                if(!isBlockToD(row2, col2)){
                    _row1 = row2;
                    _col1 = col2;
                    _row2 = row2 + 1;
                    _col2 = col2 + 1;
                    move(_row1, _col1, _row2, _col2);
                }
            }
            case 2 -> {
                System.out.println("현재 상태 : " + "세로");
                if(!isBlockToV(row2, col2)){
                    _row1 = row2;
                    _col1 = col2;
                    _row2 = row2 + 1;
                    _col2 = col2;
                    move(_row1, _col1, _row2, _col2);
                }
                if(!isBlockToD(row2, col2)){
                    _row1 = row2;
                    _col1 = col2;
                    _row2 = row2 + 1;
                    _col2 = col2 + 1;
                    move(_row1, _col1, _row2, _col2);
                }
            }
            case 3 -> {
                System.out.println("현재 상태 : " + "대각");
                if(!isBlockToH(row2, col2)){
                    _row1 = row2;
                    _col1 = col2;
                    _row2 = row2;
                    _col2 = col2 + 1;
                    move(_row1, _col1, _row2, _col2);
                }
                if(!isBlockToV(row2, col2)){
                    _row1 = row2;
                    _col1 = col2;
                    _row2 = row2 + 1;
                    _col2 = col2;
                    move(_row1, _col1, _row2, _col2);
                }
                if(!isBlockToD(row2, col2)){
                    _row1 = row2;
                    _col1 = col2;
                    _row2 = row2 + 1;
                    _col2 = col2 + 1;
                    move(_row1, _col1, _row2, _col2);
                }
            }
        }

    }


    private boolean isEnd(int row2, int col2) {
        return row2 == size - 2 && col2 == size -2;
    }

    private int position(int row1, int col1, int row2, int col2){
        int row = row2 - row1;
        int col = col2 - col1;
        // 가로는 1, 세로는 2, 대각은 3
        if(row == 0){
            return 1;
        } else if(col == 0){
            return 2;
        } else{
            return 3;
        }
    }

    // H는 가로 V는 세로 D는 대각선
    private boolean isBlockToH(int row2, int col2){
        return board[row2][col2+1] == 1;
    }

    private boolean isBlockToV(int row2, int col2){
        return board[row2+1][col2] == 1;
    }
    private boolean isBlockToD(int row2, int col2){
        if(isBlockToH(row2, col2)){
            return true;
        }
        if(isBlockToV(row2, col2)){
            return true;
        }

        return board[row2 + 1][col2 + 1] == 1;
    }

    private boolean isEdge(int row, int col) {
        boolean edge = false;
        if(row == 0) edge = true;
        if(col == 0) edge = true;
        if(row == size -1) edge = true;
        if(col == size -1) edge = true;
        return edge;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("""
                보드판 현황
                -----------------""").append("\n");
        for(int[] r : board){
            sb.append(Arrays.toString(r)).append("\n");
        }
        return sb.toString();
    }
}
