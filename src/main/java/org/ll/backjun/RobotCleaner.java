package org.ll.backjun;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제<br>
 * 로봇 청소기와 방의 상태가 주어졌을 때, 청소하는 영역의 개수를 구하는 프로그램을 작성하시오.<br>
 * 로봇 청소기가 있는 방은
 * $N \times M$ 크기의 직사각형으로 나타낼 수 있으며,
 * $1 \times 1$ 크기의 정사각형 칸으로 나누어져 있다. 각각의 칸은 벽 또는 빈 칸이다. 청소기는 바라보는 방향이 있으며, 이 방향은 동, 서, 남, 북 중 하나이다. 방의 각 칸은 좌표
 * $(r, c)$로 나타낼 수 있고, 가장 북쪽 줄의 가장 서쪽 칸의 좌표가
 * $(0, 0)$, 가장 남쪽 줄의 가장 동쪽 칸의 좌표가
 * $(N-1, M-1)$이다. 즉, 좌표
 * $(r, c)$는 북쪽에서
 * $(r+1)$번째에 있는 줄의 서쪽에서
 * $(c+1)$번째 칸을 가리킨다. 처음에 빈 칸은 전부 청소되지 않은 상태이다.<br>
 * 로봇 청소기는 다음과 같이 작동한다.<br>
 * 1.현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.<br>
 * 2.현재 칸의 주변
 * $4$칸 중 청소되지 않은 빈 칸이 없는 경우,<br>
 * 1.바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.<br>
 * 2.바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.<br>
 * 3.현재 칸의 주변
 * $4$칸 중 청소되지 않은 빈 칸이 있는 경우,<br>
 * 1.반시계 방향으로
 * $90^\circ$ 회전한다.<br>
 * 2.바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.<br>
 * 3.1번으로 돌아간다.<hr>
 * 입력<br>
 * 첫째 줄에 방의 크기<br>
 * $N$과
 * $M$이 입력된다.
 * $(3 \le N, M \le 50)$  둘째 줄에 처음에 로봇 청소기가 있는 칸의 좌표
 * $(r, c)$와 처음에 로봇 청소기가 바라보는 방향
 * $d$가 입력된다.
 * $d$가
 * $0$인 경우 북쪽,
 * $1$인 경우 동쪽,
 * $2$인 경우 남쪽,
 * $3$인 경우 서쪽을 바라보고 있는 것이다.<br>
 * 셋째 줄부터
 * $N$개의 줄에 각 장소의 상태를 나타내는
 * $N \times M$개의 값이 한 줄에
 * $M$개씩 입력된다.
 * $i$번째 줄의
 * $j$번째 값은 칸
 * $(i, j)$의 상태를 나타내며, 이 값이
 * $0$인 경우
 * $(i, j)$가 청소되지 않은 빈 칸이고,
 * $1$인 경우
 * $(i, j)$에 벽이 있는 것이다. 방의 가장 북쪽, 가장 남쪽, 가장 서쪽, 가장 동쪽 줄 중 하나 이상에 위치한 모든 칸에는 벽이 있다. 로봇 청소기가 있는 칸은 항상 빈 칸이다.<hr>
 * 출력<br>
 * 로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수를 출력한다.<hr>
 * 예제 입력 1<br>
 * 3 3<br>
 * 1 1 0<br>
 * 1 1 1<br>
 * 1 0 1<br>
 * 1 1 1<hr>
 * 예제 출력 1<br>
 * 1<hr>
 * 예제 입력 2<br>
 * 11 10<br>
 * 7 4 0<br>
 * 1 1 1 1 1 1 1 1 1 1<br>
 * 1 0 0 0 0 0 0 0 0 1<br>
 * 1 0 0 0 1 1 1 1 0 1<br>
 * 1 0 0 1 1 0 0 0 0 1<br>
 * 1 0 1 1 0 0 0 0 0 1<br>
 * 1 0 0 0 0 0 0 0 0 1<br>
 * 1 0 0 0 0 0 0 1 0 1<br>
 * 1 0 0 0 0 0 1 1 0 1<br>
 * 1 0 0 0 0 0 1 1 0 1<br>
 * 1 0 0 0 0 0 0 0 0 1<br>
 * 1 1 1 1 1 1 1 1 1 1<hr>
 * 예제 출력 2<br>
 * 57
 */
public class RobotCleaner {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // n * m
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());
        int[][] room = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j < m;j++){
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Robot robot =new Robot(sx,sy,direction,room);
        int count = robot.start();
        bw.write(count + "\n");
        bw.flush();
        bw.close();
    }

    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // n * m
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());
        int[][] room = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0;j < m;j++){
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Robot robot =new Robot(sx,sy,direction,room);
        int count = robot.start();
        System.out.println(count);




    }

    static class Robot{
        // 북:0, 동:1, 남:2, 서:3
        private int direction;
        private final int[] dx = {-1,0,1,0};
        private final int[] dy = {0,1,0,-1};

        // position
        private int x;
        private int y;
        // 0: 미청소, 1: 벽, 2: 청소완
        private final int[][] room;
        private Robot(int sx, int sy, int direction, int[][] room){
            x = sx;
            y = sy;
            this.direction = direction;
            this.room = room;
        }
        private void go(){
//            System.out.println("전진");
            x += dx[direction];
            y += dy[direction];
        }
        private boolean canGo(int nx, int ny){
            if(nx < 0 || nx >= room.length){
                return false;
            }
            if(ny < 0 || ny >= room[0].length){
                return false;
            }
            return room[nx][ny] != 1;
        }
        private void back(){
//            System.out.println("후진");
            x -= dx[direction];
            y -= dy[direction];
        }
        private void clean(){
//            System.out.println("청소");
            room[x][y] = 2;
        }
        private boolean cleanable(int nx, int ny){
//            System.out.printf("%d, %d 청소가능?\n", nx, ny);
            return room[nx][ny] == 0;
        }
        private void rotate(){
//            System.out.println("회전");
            if(direction>0){
                direction--;
            }else{
                direction = 3;
            }
        }
        private boolean hasNonCleanArea(){
            int nx,ny;
            for(int i = 0; i<4;i++){
                nx = x + dx[i];
                ny = y + dy[i];
                if(cleanable(nx, ny)){
                    return true;
                }
            }
            return false;
        }

        public int start() {
            int cleanArea = 0;
            int nx, ny;

            while(true) {
                // 1.청소할 수 있음 한다.
                if (cleanable(x, y)) {
                    clean();
                    cleanArea++;
                }
                // 2. 청소할 곳이 없으면 후진
                if (!hasNonCleanArea()) {
                    nx = x - dx[direction];
                    ny = y - dy[direction];
                    // 후진
                    if (canGo(nx, ny)) {
                        back();
                    } else {
                        break;
                    }
                } else {
                    for(int i = 0; i< 4;i++) {
                        rotate();
                        nx = x + dx[direction];
                        ny = y + dy[direction];
                        if (cleanable(nx, ny)) {
                            go();
                            break;
                        }
                    }
                }
            }
            return cleanArea;
        }
        private void currentState(){
            System.out.println("---현재 상태---");
            System.out.printf("현재위치: %d, %d, 방향: %d\n", x, y, direction);
            for(int[] ints : room) {
                System.out.println(Arrays.toString(ints));
            }
        }
    }


}
