package org.ll.backjun.samsung;

public class SamsungA4ColorPaper {
    private final int[][] board;
    private final int[] colors = new int[]{0, 5, 5, 5, 5, 5};

    private int min = Integer.MAX_VALUE;

    public SamsungA4ColorPaper(int[][] board) {
        this.board = board;
    }

    public int findMin() {
        // board 복사
        int[][] tempBoard = new int[10][10];
        for (int r = 0; r < 10; r++) {
            System.arraycopy(board[r], 0, tempBoard[r], 0, 10);
        }
        // 종이 개수 복사
        int[] papers = new int[6];
        System.arraycopy(colors, 0, papers, 0, 6);

        dfs(0, 0, tempBoard, 0, papers);

        if (min == (int) Integer.MAX_VALUE) {
            return -1;
        }

        return min;
    }

    private void dfs(int row, int col, int[][] board, int minCount, int[] papers) {
        // 현재 최소보다 크면 의미 없다.
        if (minCount >= min) {
            return;
        }

        // 보드를 다 돌았는데 보드에 남은 부분이 있다면 의미 없다.
        boolean foundOne = false;

        for (int r = row; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (board[r][c] == 1) {
                    foundOne = true;
                    int maxSize = isPossible(r, c, board);
                    for (int size = maxSize; size > 0; size--) {
                        // 5개 보다 많이 사용하면 안됨.
                        if (papers[size] > 0) {
                            papers[size]--;
                            setPaper(r, c, board, size, 0);
                            dfs(r, c, board, minCount + 1, papers);
                            setPaper(r, c, board, size, 1);
                            papers[size]++;
                        }
                    }
                    return;
                }
            }
        }
        min = Math.min(min, minCount);
    }

    private int remainOnes(int[][] board) {
        int count = 0;
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (board[r][c] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private int isPossible(int row, int col, int[][] board) {
        int maxSize = 1;
        for (int color = 2; color <= 5; color++) {
            if (row + color > 10 || col + color > 10) {
                return maxSize;
            }

            boolean possible = true;
            for (int r = row; r < row + color && possible; r++) {
                for (int c = col; c < col + color; c++) {
                    if (board[r][c] != 1) {
                        possible = false;
                        break;
                    }
                }
            }
            if (possible) {
                maxSize = color;
            } else {
                break;
            }
        }
        // 배치 가능
        return maxSize;
    }

    private void setPaper(int row, int col, int[][] board, int color, int value) {
        // 확인 완료 후 색종이 배치
        for (int r = row; r < row + color; r++) {
            for (int c = col; c < col + color; c++) {
                board[r][c] = value;
            }
        }
    }

    private boolean hasPoint(int[][] tempBoard) {
        for (int[] r : tempBoard) {
            for (int c : r) {
                if (c == 1) return true;
            }
        }
        return false;
    }
}
