package org.ll.algorithm.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 최단경로<br>
 * 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하세요. 단, 모든 간선의 가중치는 10 이하의 자연수입니다.<br>
 * 입력 형식<br>
 * 첫째 줄에 두 개의 정수 V와 E가 주어집니다.<br>
 * V: 정점의 개수 (1 ≤ V ≤ 20,000)<br>
 * E: 간선의 개수 (1 ≤ E ≤ 300,000)<br>
 * 둘째 줄에 시작 정점의 번호 K가 주어집니다. (1 ≤ K ≤ V)<br>
 * 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어집니다.<br>
 * u: 시작 정점<br>
 * v: 도착 정점<br>
 * w: 가중치 (w는 10 이하의 자연수)<br>
 * u와 v는 서로 다름<br>
 * 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수 있음<br>
 * 출력 형식<br>
 * 첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력합니다.<br>
 * 시작점 자신은 0으로 출력<br>
 * 경로가 존재하지 않는 경우에는 INF를 출력<br>
 * 예제 입력 1<br>
 * 5 6<br>
 * 1<br>
 * 5 1 1<br>
 * 1 2 2<br>
 * 1 3 3<br>
 * 2 3 4<br>
 * 2 4 5<br>
 * 3 4 6<br>
 * 예제 출력 1<br>
 * 0<br>
 * 2<br>
 * 3<br>
 * 7<br>
 * -1
 */
public class ShortestDistance {

    static class Node implements Comparable<Node> {
        int vertext, weight;

        public Node(int vertext, int weight){
            this.vertext = vertext;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static Integer[] search(int E, int V, int K, Integer[][] arr) {
        // 입력 처리, 간선을 맵에 번호로 정리
        ArrayList<Node>[] graph = setGraph(E,arr);
        // 거리 초기화
        int[] distance = new int[E + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[K] = 0;

        // 시작점 간선 heap에 넣기
        PriorityQueue<Node> heap = new PriorityQueue<>();
        heap.add(new Node(K,0));

        while(!heap.isEmpty()){
            Node node = heap.poll();

            if(distance[node.vertext] < node.weight){
                continue;
            }

            for(Node next :graph[node.vertext]){
                int len = distance[node.vertext] + next.weight;
                if(distance[next.vertext] > len){
                    distance[next.vertext] = len;
                    heap.add(new Node(next.vertext,len));
                }
            }

        }

        return new Integer[]{};
    }

    private static ArrayList<Node>[] setGraph(int E, Integer[][] arr){
        ArrayList<Node>[] graph = new ArrayList[E+1];
        Arrays.fill(graph, new ArrayList<>());
        for(Integer[] array : arr){
            graph[array[0]].add(new Node(array[1],array[2]));
        }

        return graph;
    }
}
