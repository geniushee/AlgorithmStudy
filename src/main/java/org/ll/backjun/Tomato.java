package org.ll.backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Set;

/**
 * 문제<br>
 * 철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다. 토마토는 아래의 그림과 같이 격자 모양 상자의 칸에 하나씩 넣어서 창고에 보관한다.<br>
 * 창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다. 보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다. 하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다. 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다. 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지, 그 최소 일수를 알고 싶어 한다.<br>
 * 토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라. 단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.<hr>
 * 입력<br>
 * 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N이 주어진다. M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다. 단, 2 ≤ M,N ≤ 1,000 이다. 둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다. 즉, 둘째 줄부터 N개의 줄에는 상자에 담긴 토마토의 정보가 주어진다. 하나의 줄에는 상자 가로줄에 들어있는 토마토의 상태가 M개의 정수로 주어진다. 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.<br>
 * 토마토가 하나 이상 있는 경우만 입력으로 주어진다.<hr>
 * 출력<br>
 * 여러분은 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다. 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.<hr>
 * 예제 입력 1 <br>
 * 6 4<br>
 * 0 0 0 0 0 0<br>
 * 0 0 0 0 0 0<br>
 * 0 0 0 0 0 0<br>
 * 0 0 0 0 0 1<hr>
 * 예제 출력 1 <br>
 * 8<hr>
 * 예제 입력 2<br>
 * 6 4<br>
 * 0 -1 0 0 0 0<br>
 * -1 0 0 0 0 0<br>
 * 0 0 0 0 0 0<br>
 * 0 0 0 0 0 1<hr>
 * 예제 출력 2 <br>
 * -1<hr>
 * 예제 입력 3<br>
 * 6 4<br>
 * 1 -1 0 0 0 0<br>
 * 0 -1 0 0 0 0<br>
 * 0 0 0 0 -1 0<br>
 * 0 0 0 0 -1 1<hr>
 * 예제 출력 3 <br>
 * 6<hr>
 * 예제 입력 4<br>
 * 5 5<br>
 * -1 1 0 0 0<br>
 * 0 -1 -1 -1 0<br>
 * 0 -1 -1 -1 0<br>
 * 0 -1 -1 -1 0<br>
 * 0 0 0 0 0<hr>
 * 예제 출력 4 <br>
 * 14<hr>
 * 예제 입력 5<br>
 * 2 2<br>
 * 1 -1<br>
 * -1 1<hr>
 * 예제 출력 5<br>
 * 0
 */
public class Tomato {
    private static int[][] box;
    private static int totalCount;
    private static int unripeCount;
    private static Set<Axis> set;
    private static int[] dx = new int[]{-1,1,0,0};
    private static int[] dy = new int[]{0,0,-1,1};

    class Axis{
        private int x;
        private int y;
        public Axis(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Integer.parseInt("" + this.x + this.y);
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) return true;
            if(!(obj instanceof Axis b)){
                return false;
            }
            return this.x == b.x && this.y == b.y;
        }
    }

    public static void main(String[] arg){

    }
    public void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        String[] line = br.readLine().split(" ");
        int m = Integer.parseInt(line[0]);
        int n = Integer.parseInt(line[1]);

        // 박스, 전체, 안익은 토마토 정리
        box = new int[n][m];
        for(int i = 0; i < n; i++){
            line = br.readLine().split(" ");
            for(int j = 0; j < line.length; j++){
                int tomato = Integer.parseInt(line[j]);
                if(tomato != 0){
                    totalCount++;
                    if(tomato == -1){
                        unripeCount++;
                    }
                }
                box[i][j] = tomato;
            }
        }

        if(unripeCount == 0){
            System.out.println(0+"\n");
        }

        // 익은 토마토 찾고 주변 안 익은 토마토 찾기
        boolean change;
        int day = 0;
        while(true){
            // change 초기화
            change = false;
            //익은 토마토 주변 토마토 확인
            for(int i = 0; i<n; i++){
                for(int j =0; j<m;j++){
                    if(box[i][j] == 1){
                        for(int k = 0; k < 4; k++){
                            int x = i + dx[k];
                            int y = j + dy[k];
                            if(x >=0 && x <n && y >=0 && y <m && box[x][y] == -1){
                                change = true;
                                set.add(new Axis(x,y));
                            }
                        }
                    }
                }
            }
            if(!change){
                break;
            }

            // 토마토 변경, 날짜 세기
            for(Axis axis : set){
                box[axis.x][axis.y] = 1;
                unripeCount--;
            }
            day++;

        }
        if(unripeCount != 0){
            System.out.println(-1 + "\n");
            return;
        }

        System.out.println("다 익는데 걸린 시간 : " +day);
    }
}
