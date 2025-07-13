package org.ll.backjun;

import java.io.*;
import java.util.*;

/**
 * 문제<br>
 * 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오. 단, 모든 간선의 가중치는 10 이하의 자연수이다.<hr>
 * 입력<br>
 * 첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. (1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000) 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다. 둘째 줄에는 시작 정점의 번호 K(1 ≤ K ≤ V)가 주어진다. 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다. 이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. u와 v는 서로 다르며 w는 10 이하의 자연수이다. 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.<hr>
 * 출력<br>
 * 첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다. 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.
 */
public class ShortestRoute {
    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        // init graph
        List<int[]>[] graph = new ArrayList[V];
        for(int i = 0; i<V; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i<E;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u-1].add(new int[]{v-1,w});
        }

        // 최단거리를 위한 배열
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[K-1] = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        heap.offer(new int[]{K-1,0});

        while(!heap.isEmpty()){
            // 현재 노드
            int[] cur = heap.poll();
            int node = cur[0], dis = cur[1];

            // 최단거리 보다 크면 거르기
            if(dis > distance[node]) continue;

            for(int[] next: graph[node]){
                int newDist = dis + next[1];
//                System.out.println("현재 : " + node + " / "+dis + "다음 : "+next[0]+" / "+next[1]);
                if(newDist < distance[next[0]]){
                    distance[next[0]] = newDist;
                    heap.offer(new int[]{next[0], newDist});
                }

            }
        }

        // 결과 출력
        for(int i = 0; i < V;i++){
            if(distance[i] == Integer.MAX_VALUE){
                bw.write("INF\n");
            }else{
                bw.write(distance[i] +"\n");
            }
            bw.flush();
        }
        bw.close();
    }
}
