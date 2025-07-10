package org.ll.backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 문제<br>
 * 세로
 * $R$칸, 가로
 * $C$칸으로 된 표 모양의 보드가 있다. 보드의 각 칸에는 대문자 알파벳이 하나씩 적혀 있고, 좌측 상단 칸 (
 * $1$행
 * $1$열) 에는 말이 놓여 있다.
 * 말은 상하좌우로 인접한 네 칸 중의 한 칸으로 이동할 수 있는데, 새로 이동한 칸에 적혀 있는 알파벳은 지금까지 지나온 모든 칸에 적혀 있는 알파벳과는 달라야 한다. 즉, 같은 알파벳이 적힌 칸을 두 번 지날 수 없다.
 * 좌측 상단에서 시작해서, 말이 최대한 몇 칸을 지날 수 있는지를 구하는 프로그램을 작성하시오. 말이 지나는 칸은 좌측 상단의 칸도 포함된다.<hr>
 * 입력<br>
 * 첫째 줄에
 * $R$과
 * $C$가 빈칸을 사이에 두고 주어진다. (
 * $1 ≤ R,C ≤ 20$) 둘째 줄부터
 * $R$개의 줄에 걸쳐서 보드에 적혀 있는
 * $C$개의 대문자 알파벳들이 빈칸 없이 주어진다.<hr>
 * 출력<br>
 * 첫째 줄에 말이 지날 수 있는 최대의 칸 수를 출력한다.<hr>
 * 예제 입력 1 <br>
 * 2 4<br>
 * CAAB<br>
 * ADCB<hr>
 * 예제 출력 1<br>
 * 3<hr>
 * 예제 입력 2<br>
 * 3 6<br>
 * HFDFFB<br>
 * AJHGDH<br>
 * DGAGEH<hr>
 * 예제 출력 2<br>
 * 6<hr>
 * 예제 입력 3<br>
 * 5 5<br>
 * IEFCJ<br>
 * FHFKC<br>
 * FFALF<br>
 * HFGCF<br>
 * HMCHH<hr>
 * 예제 출력 3<br>
 * 10
 */
public class Alphabet {
    private static int[] dx = new int[]{-1,1,0,0};
    private static int[] dy = new int[]{0,0,-1,1};

    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        String line = br.readLine();
        int r = Integer.parseInt(line.split(" ")[0]);
        int c = Integer.parseInt(line.split(" ")[1]);
        final int[][] board = new int[r][c];
        Set<Integer> read = new HashSet<>();

        for(int i = 0;i<r;i++){
            line = br.readLine();
            int j = 0;
            for(char c1 : line.toCharArray()){
                board[i][j++] = c1;
            }
        }

        int count = dfs(read, board,0,0,0);

        System.out.println(Arrays.deepToString(board));
        System.out.println(count == 0? 1:count);
    }

    private static int dfs(Set<Integer> read, int[][] board,int r, int c, int count){
        if(read.contains(board[r][c])){
            return count;
        }
        int max = 0;
        read.add(board[r][c]);

        int nr, nc;
        for(int i = 0; i < 4; i++){
            nr = r + dx[i];
            nc = c + dy[i];
            if(nr < 0 || nr >= board.length || nc < 0 || nc >= board[0].length){
                continue;
            }
            max = Math.max(max, dfs(read,board,nr,nc,count + 1));
        }

        read.remove(board[r][c]);

        return max;
    }
}
