package org.ll.backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * 문제<br>
 * N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다. 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 도시의 번호는 1부터 N까지이다.<hr>
 * 입력<br>
 * 첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다. 그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
 * 그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다. 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.<hr>
 * 출력<br>
 * 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.<hr>
 * 예제 입력 1 <br>
 * 5<br>
 * 8<br>
 * 1 2 2<br>
 * 1 3 3<br>
 * 1 4 1<br>
 * 1 5 10<br>
 * 2 4 2<br>
 * 3 4 1<br>
 * 3 5 1<br>
 * 4 5 3<br>
 * 1 5<hr>
 * 예제 출력 1<br>
 * 4
 */
public class MinCost {

    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] minDistance = new int[N + 1];
        // idx = 출발지점, [0] = 도착지점, [1] = cost
        List<int[]>[] buses = new ArrayList[N + 1];
        for (int n = 0; n < N + 1; n++) {
            buses[n] = new ArrayList<>();
            minDistance[n] = Integer.MAX_VALUE;
        }

        // 버스 초기화
        StringTokenizer st;
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            buses[idx]
                    .add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        minDistance[start] = 0;
        heap.offer(new int[]{start, 0});

        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int curNode = cur[0];
            int curCost = cur[1];

            System.out.printf("현재 node %d / 현재 코스트 %d\n", curNode, curCost);
            if (curCost > minDistance[curNode]) {
                continue;
            }

            for (int[] bus : buses[curNode]) {
                int next = bus[0];
                int totalCost = curCost + bus[1];
                if (minDistance[next] > totalCost) {
                    System.out.println("다음으로");
                    minDistance[next] = totalCost;
                    heap.offer(new int[]{next, totalCost});
                }
            }
        }
        System.out.println(minDistance[end]);
    }
}
