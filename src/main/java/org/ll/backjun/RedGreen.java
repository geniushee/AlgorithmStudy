package org.ll.backjun;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 문제<br>
 * 적록색약은 빨간색과 초록색의 차이를 거의 느끼지 못한다. 따라서, 적록색약인 사람이 보는 그림은 아닌 사람이 보는 그림과는 좀 다를 수 있다.<br>
 * 크기가 N×N인 그리드의 각 칸에 R(빨강), G(초록), B(파랑) 중 하나를 색칠한 그림이 있다. 그림은 몇 개의 구역으로 나뉘어져 있는데, 구역은 같은 색으로 이루어져 있다. 또, 같은 색상이 상하좌우로 인접해 있는 경우에 두 글자는 같은 구역에 속한다. (색상의 차이를 거의 느끼지 못하는 경우도 같은 색상이라 한다)<br>
 * 예를 들어, 그림이 아래와 같은 경우에<br>
 * RRRBB<br>
 * GGBBB<br>
 * BBBRR<br>
 * BBRRR<br>
 * RRRRR<br>
 * 적록색약이 아닌 사람이 봤을 때 구역의 수는 총 4개이다. (빨강 2, 파랑 1, 초록 1) 하지만, 적록색약인 사람은 구역을 3개 볼 수 있다. (빨강-초록 2, 파랑 1)<br>
 * 그림이 입력으로 주어졌을 때, 적록색약인 사람이 봤을 때와 아닌 사람이 봤을 때 구역의 수를 구하는 프로그램을 작성하시오.<hr>
 * 입력<br>
 * 첫째 줄에 N이 주어진다. (1 ≤ N ≤ 100)<br>
 * 둘째 줄부터 N개 줄에는 그림이 주어진다.<hr>
 * 출력<br>
 * 적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.<hr>
 * 예제 입력 1 <br>
 * 5<br>
 * RRRBB<br>
 * GGBBB<br>
 * BBBRR<br>
 * BBRRR<br>
 * RRRRR<hr>
 * 예제 출력 1<br>
 * 4 3
 */
public class RedGreen {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // init
        String line = br.readLine();
        int N = Integer.parseInt(line);
        int[][] image = new int[N][N];
        boolean[][] red = new boolean[N][N];
        boolean[][] green = new boolean[N][N];
        boolean[][] blue = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        int normal = 0;
        int blind = 0;

        // set image, red = 1, green = 2, blue = 3
        for (int i = 0; i < N; i++) {
            line = br.readLine();
            int j = 0;
            for (String s : line.split("")) {
                int e = 0;
                switch (s) {
                    case "R" -> e = 1;
                    case "G" -> e = 2;
                    case "B" -> e = 3;
                }
                image[i][j++] = e;
            }
        }

        // find section
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean flag = false;
                if (!red[i][j] && !green[i][j] && !blue[i][j]) {
                    switch (image[i][j]) {
                        case 1 -> {
                            if (red[i][j]) {
                                continue;
                            }
                            red[i][j] = true;
                        }
                        case 2 -> {
                            if (green[i][j]) {
                                continue;
                            }
                            green[i][j] = true;
                        }
                        case 3 -> {
                            if (blue[i][j]) {
                                continue;
                            }
                            blue[i][j] = true;
                        }
                    }
                    queue.offer(new int[]{i, j, image[i][j]});
                    flag = true;
                }
                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    for (int k = 0; k < 4; k++) {
                        int nx = now[0] + dx[k];
                        int ny = now[1] + dy[k];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                            continue;
                        }
                        if (now[2] == image[nx][ny]) {
                            switch (now[2]) {
                                case 1 -> {
                                    if (red[nx][ny]) {
                                        continue;
                                    }
                                    red[nx][ny] = true;
                                }
                                case 2 -> {
                                    if (green[nx][ny]) {
                                        continue;
                                    }
                                    green[nx][ny] = true;
                                }
                                case 3 -> {
                                    if (blue[nx][ny]) {
                                        continue;
                                    }
                                    blue[nx][ny] = true;
                                }
                            }
                            queue.offer(new int[]{nx, ny, image[nx][ny]});
                        }
                    }
                }
                if (flag) {
                    normal++;
                }
            }
        }
        bw.write(normal+"\n");

        //init
        red = new boolean[N][N];
        blue = new boolean[N][N];
        // find section
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean flag = false;
                if (!red[i][j] && !blue[i][j]) {
                    switch (image[i][j]) {
                        case 1, 2 -> {
                            if (red[i][j]) {
                                continue;
                            }
                            red[i][j] = true;
                        }
                        case 3 -> {
                            if (blue[i][j]) {
                                continue;
                            }
                            blue[i][j] = true;
                        }
                    }
                    queue.offer(new int[]{i, j, image[i][j]});
                    flag = true;
                }
                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    for (int k = 0; k < 4; k++) {
                        int nx = now[0] + dx[k];
                        int ny = now[1] + dy[k];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                            continue;
                        }
                        if (now[2] == 3 && image[nx][ny] == 3) {
                            if (blue[nx][ny]) {
                                continue;
                            }
                            blue[nx][ny] = true;
                            queue.offer(new int[]{nx, ny, image[nx][ny]});
                        } else if(now[2] != 3 && (image[nx][ny] == 1 || image[nx][ny] == 2)) {
                            if (red[nx][ny]) {
                                continue;
                            }
                            red[nx][ny] = true;
                            queue.offer(new int[]{nx, ny, image[nx][ny]});
                        }
                    }
                }
                if (flag) {
                    blind++;
                }
            }
        }
        bw.write(blind+"\n");
        bw.flush();
        bw.close();
    }

    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        // init
        String line = br.readLine();
        int N = Integer.parseInt(line);
        int[][] image = new int[N][N];
        boolean[][] red = new boolean[N][N];
        boolean[][] green = new boolean[N][N];
        boolean[][] blue = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        int[] dx = new int[]{-1, 1, 0, 0};
        int[] dy = new int[]{0, 0, -1, 1};
        int normal = 0;
        int blind = 0;

        // set image, red = 1, green = 2, blue = 3
        for (int i = 0; i < N; i++) {
            line = br.readLine();
            int j = 0;
            for (String s : line.split("")) {
                int e = 0;
                switch (s) {
                    case "R" -> e = 1;
                    case "G" -> e = 2;
                    case "B" -> e = 3;
                }
                image[i][j++] = e;
            }
        }
        System.out.println(Arrays.deepToString(image));

        // find section
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean flag = false;
                if (!red[i][j] && !green[i][j] && !blue[i][j]) {
                    switch (image[i][j]) {
                        case 1 -> {
                            if (red[i][j]) {
                                continue;
                            }
                            red[i][j] = true;
                        }
                        case 2 -> {
                            if (green[i][j]) {
                                continue;
                            }
                            green[i][j] = true;
                        }
                        case 3 -> {
                            if (blue[i][j]) {
                                continue;
                            }
                            blue[i][j] = true;
                        }
                    }
                    queue.offer(new int[]{i, j, image[i][j]});
                    flag = true;
                }
                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    System.out.printf("현재위치 : %d, %d, %d\n", now[0], now[1], now[2]);
                    for (int k = 0; k < 4; k++) {
                        int nx = now[0] + dx[k];
                        int ny = now[1] + dy[k];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                            continue;
                        }
                        if (now[2] == image[nx][ny]) {
                            switch (now[2]) {
                                case 1 -> {
                                    if (red[nx][ny]) {
                                        continue;
                                    }
                                    red[nx][ny] = true;
                                }
                                case 2 -> {
                                    if (green[nx][ny]) {
                                        continue;
                                    }
                                    green[nx][ny] = true;
                                }
                                case 3 -> {
                                    if (blue[nx][ny]) {
                                        continue;
                                    }
                                    blue[nx][ny] = true;
                                }
                            }
                            queue.offer(new int[]{nx, ny, image[nx][ny]});
                        }
                    }
                }
                if (flag) {
                    System.out.println("한 구역 종료");
                    normal++;
                }
            }
        }
        System.out.println("일반: " + normal);

        //init
        red = new boolean[N][N];
        blue = new boolean[N][N];
        // find section
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                boolean flag = false;
                if (!red[i][j] && !blue[i][j]) {
                    switch (image[i][j]) {
                        case 1, 2 -> {
                            if (red[i][j]) {
                                continue;
                            }
                            red[i][j] = true;
                        }
                        case 3 -> {
                            if (blue[i][j]) {
                                continue;
                            }
                            blue[i][j] = true;
                        }
                    }
                    queue.offer(new int[]{i, j, image[i][j]});
                    flag = true;
                }
                while (!queue.isEmpty()) {
                    int[] now = queue.poll();
                    System.out.printf("현재위치 : %d, %d, %d\n", now[0], now[1], now[2]);
                    for (int k = 0; k < 4; k++) {
                        int nx = now[0] + dx[k];
                        int ny = now[1] + dy[k];
                        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                            continue;
                        }
                        if (now[2] == 3 && image[nx][ny] == 3) {
                            if (blue[nx][ny]) {
                                continue;
                            }
                            blue[nx][ny] = true;
                            queue.offer(new int[]{nx, ny, image[nx][ny]});
                        } else if(now[2] != 3 && (image[nx][ny] == 1 || image[nx][ny] == 2)) {
                            if (red[nx][ny]) {
                                continue;
                            }
                            red[nx][ny] = true;
                            queue.offer(new int[]{nx, ny, image[nx][ny]});
                        }
                    }
                }
                if (flag) {
                    System.out.println("한 구역 종료");
                    blind++;
                }
            }
        }
        System.out.println("색약: " + blind);
    }
}
