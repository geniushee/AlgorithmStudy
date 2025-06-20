package org.ll.backjun;

import java.io.*;

/**
 * 문제<br>
 * N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.<br>
 * N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.<hr>
 * 입력<br>
 * 첫째 줄에 N이 주어진다. (1 ≤ N < 15)<hr>
 * 출력<br>
 * 첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.<hr>
 * 예제 입력 1 <br>
 * 8<hr>
 * 예제 출력 1<br>
 * 92<hr>
 * visited처리를 조금 다르게 하면 더 빠르게도 된다.
 */
public class NQueen {
    private static int answer;

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        answer = 0;
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int j = 0; j < N; j++) {
            dfs(board, 0, j, 0);
        }
        bw.write(answer+"\n");
        bw.flush();
        bw.close();
    }

    public static void dfs(int[][] board, int x, int y, int now) {
//        System.out.printf("%d, %d, %d\n", x, y, now);
        // col에 있는가?
        for (int i = 0; i < board.length; i++) {
            if (board[i][y] == 1) {
//                System.out.printf("(%d, %d)에서 걸림\n", i, y);
                return;
            }
        }
        // 좌상향에 있는가?
        for (int i = 0; i <= x; i++) {
            if(y - x + i < 0){
                continue;
            }
            if (board[i][y - x + i] == 1) {
//                System.out.printf("(%d, %d)에서 걸림\n", i, y - x + i);
                return;
            }
        }
        // 우상향에 있는가?
        for (int i = 0; i <= x; i++) {
            if(x + y - i >= board.length){
                continue;
            }
            if (board[i][x + y - i] == 1) {
//                System.out.printf("(%d, %d)에서 걸림\n", i, x + y - i);
                return;
            }
        }
//        System.out.println(Arrays.deepToString(board));
        board[x][y] = 1;
//        System.out.println(Arrays.deepToString(board));
        int renew = now + 1;
        if (renew == board.length) {
            answer += 1;
//            System.out.println("오예!");
            board[x][y] = 0;
            return;
        }
        for (int i = 0; i < board.length; i++) {
            dfs(board, x + 1, i, renew);
        }
        board[x][y] = 0;
    }

    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        answer = 0;
        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int j = 0; j < N; j++) {
            dfs(board, 0, j, 0);
        }
        System.out.println(answer);
    }
}
