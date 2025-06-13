package org.ll.backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 백준 14502번<hr>
 * 인체에 치명적인 바이러스를 연구하던 연구소에서 바이러스가 유출되었다. 다행히 바이러스는 아직 퍼지지 않았고, 바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 한다.
 * 연구소는 크기가 N×M인 직사각형으로 나타낼 수 있으며, 직사각형은 1×1 크기의 정사각형으로 나누어져 있다. 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다.
 * 일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다. 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.
 * 예를 들어, 아래와 같이 연구소가 생긴 경우를 살펴보자.<br>
 * 2 0 0 0 1 1 0<br>
 * 0 0 1 0 1 2 0<br>
 * 0 1 1 0 1 0 0<br>
 * 0 1 0 0 0 0 0<br>
 * 0 0 0 0 0 1 1<br>
 * 0 1 0 0 0 0 0<br>
 * 0 1 0 0 0 0 0<br>
 * 이때, 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳이다. 아무런 벽을 세우지 않는다면, 바이러스는 모든 빈 칸으로 퍼져나갈 수 있다.
 * 2행 1열, 1행 2열, 4행 6열에 벽을 세운다면 지도의 모양은 아래와 같아지게 된다.<br>
 * 2 1 0 0 1 1 0<br>
 * 1 0 1 0 1 2 0<br>
 * 0 1 1 0 1 0 0<br>
 * 0 1 0 0 0 1 0<br>
 * 0 0 0 0 0 1 1<br>
 * 0 1 0 0 0 0 0<br>
 * 0 1 0 0 0 0 0<br>
 * 바이러스가 퍼진 뒤의 모습은 아래와 같아진다.<br>
 * 2 1 0 0 1 1 2<br>
 * 1 0 1 0 1 2 2<br>
 * 0 1 1 0 1 2 2<br>
 * 0 1 0 0 0 1 2<br>
 * 0 0 0 0 0 1 1<br>
 * 0 1 0 0 0 0 0<br>
 * 0 1 0 0 0 0 0<br>
 * 벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다. 위의 지도에서 안전 영역의 크기는 27이다.
 * 연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.<hr>
 * 입력<br>
 * 첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)<br>
 * 둘째 줄부터 N개의 줄에 지도의 모양이 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다. 2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수이다.<br>
 * 빈 칸의 개수는 3개 이상이다.<hr>
 * 출력<br>
 * 첫째 줄에 얻을 수 있는 안전 영역의 최대 크기를 출력한다.<hr>
 * 예제 입력 1 <br>
 * 7 7<br>
 * 2 0 0 0 1 1 0<br>
 * 0 0 1 0 1 2 0<br>
 * 0 1 1 0 1 0 0<br>
 * 0 1 0 0 0 0 0<br>
 * 0 0 0 0 0 1 1<br>
 * 0 1 0 0 0 0 0<br>
 * 0 1 0 0 0 0 0<br>
 * 예제 출력 1 <br>
 * 27<hr>
 * 예제 입력 2<br>
 * 4 6<br>
 * 0 0 0 0 0 0<br>
 * 1 0 0 0 0 2<br>
 * 1 1 1 0 0 2<br>
 * 0 0 0 0 0 2<br>
 * 예제 출력 2 <br>
 * 9<hr>
 * 예제 입력 3<br>
 * 8 8<br>
 * 2 0 0 0 0 0 0 2<br>
 * 2 0 0 0 0 0 0 2<br>
 * 2 0 0 0 0 0 0 2<br>
 * 2 0 0 0 0 0 0 2<br>
 * 2 0 0 0 0 0 0 2<br>
 * 0 0 0 0 0 0 0 0<br>
 * 0 0 0 0 0 0 0 0<br>
 * 0 0 0 0 0 0 0 0<br>
 * 예제 출력 3 <br>
 * 3
 */
public class Laboratory {
    private static int n;
    private static int m;
    private static int[][] lab;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    }

    public static int run(String input) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        String[] nm = br.readLine().split(" ");

        n = Integer.parseInt(nm[0]);
        m = Integer.parseInt(nm[1]);
        lab = new int[n][m];
        max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                lab[i][j] = Integer.parseInt(row[j]);
            }
        }
        System.out.println("---최초 보드---");
        System.out.println(Arrays.deepToString(lab));
        System.out.println("---초기 0개수: "+countMax(lab));

        // 벽세우기 시작
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, 3);
            }
        }

        return max;
    }

    public static void dfs(int row, int col, int wallCount) {
        if (lab[row][col] != 0) {
            return;
        }

        // 벽세우기
        lab[row][col] = 1;
        if (wallCount - 1 == 0) {
            // 바이러스 전파
            int[][] virus = virus();
            // max 갱신
            int temp = countMax(virus);
            if(max < temp){
                max = temp;
                System.out.println(Arrays.deepToString(lab)+", 이번 안전지대: " + temp);
                System.out.println(Arrays.deepToString(virus));
            }


            // 나가기 전 원복
            lab[row][col] = 0;
            return;
        }

        int nRow = row;
        int nCol = col;
        while(nRow < n){
            dfs(nRow,nCol++,wallCount -1);
            if(nCol == m){
                nCol = 0;
                nRow++;
            }
        }

        // 나가기 전 원복
        lab[row][col] = 0;
    }

    private static int countMax(int[][] safeZone) {
        int count = 0;
        for(int i =0;i < n;i++){
            for(int j =0; j<m;j++){
                if(safeZone[i][j] == 0){
                    count++;
                }
            }
        }
        return count;
    }

    public static int[][] virus(){
        // 바이러스가 퍼질 연구소 복사
        int[][] temp = new int[n][m];
        for(int i =0;i<n;i++){
            temp[i] = Arrays.copyOf(lab[i],m);
        }

        // 바이러스 퍼트리기
        Queue<Integer[]> queue = new LinkedList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(temp[i][j] == 2){
                    queue.add(new Integer[]{i,j});
                    while(!queue.isEmpty()) {
                        Integer[] e = queue.poll();
                        int row = e[0];
                        int col = e[1];
                        // 좌
                        if (col - 1 >= 0 && temp[row][col-1] == 0) {
                            temp[row][col-1] = 2;
                            queue.add(new Integer[]{row, col - 1});
                        }
                        // 우
                        if (col + 1 < m && temp[row][col+1] == 0) {
                            temp[row][col+1] = 2;
                            queue.add(new Integer[]{row, col + 1});
                        }
                        // 상
                        if (row - 1 >= 0 && temp[row-1][col] == 0) {
                            queue.add(new Integer[]{row - 1, col});
                            temp[row-1][col] = 2;
                        }
                        // 하
                        if (row + 1 < n && temp[row+1][col] == 0) {
                            queue.add(new Integer[]{row + 1, col});
                            temp[row+1][col] = 2;
                        }
                    }
                }
            }
        }

        return temp;
    }
}
