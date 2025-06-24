package org.ll.backjun;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 문제<br>
 * 트리(tree)는 사이클이 없는 무방향 그래프이다. 트리에서는 어떤 두 노드를 선택해도 둘 사이에 경로가 항상 하나만 존재하게 된다. 트리에서 어떤 두 노드를 선택해서 양쪽으로 쫙 당길 때, 가장 길게 늘어나는 경우가 있을 것이다. 이럴 때 트리의 모든 노드들은 이 두 노드를 지름의 끝 점으로 하는 원 안에 들어가게 된다.<br>
 * 이런 두 노드 사이의 경로의 길이를 트리의 지름이라고 한다. 정확히 정의하자면 트리에 존재하는 모든 경로들 중에서 가장 긴 것의 길이를 말한다.<br>
 * 입력으로 루트가 있는 트리를 가중치가 있는 간선들로 줄 때, 트리의 지름을 구해서 출력하는 프로그램을 작성하시오. 아래와 같은 트리가 주어진다면 트리의 지름은 45가 된다.<br>
 * 트리의 노드는 1부터 n까지 번호가 매겨져 있다.<hr>
 * 입력<br>
 * 파일의 첫 번째 줄은 노드의 개수 n(1 ≤ n ≤ 10,000)이다. 둘째 줄부터 n-1개의 줄에 각 간선에 대한 정보가 들어온다. 간선에 대한 정보는 세 개의 정수로 이루어져 있다. 첫 번째 정수는 간선이 연결하는 두 노드 중 부모 노드의 번호를 나타내고, 두 번째 정수는 자식 노드를, 세 번째 정수는 간선의 가중치를 나타낸다. 간선에 대한 정보는 부모 노드의 번호가 작은 것이 먼저 입력되고, 부모 노드의 번호가 같으면 자식 노드의 번호가 작은 것이 먼저 입력된다. 루트 노드의 번호는 항상 1이라고 가정하며, 간선의 가중치는 100보다 크지 않은 양의 정수이다.<hr>
 * 출력<br>
 * 첫째 줄에 트리의 지름을 출력한다.<hr>
 * 예제 입력 1 <br>
 * 12<br>
 * 1 2 3<br>
 * 1 3 2<br>
 * 2 4 5<br>
 * 3 5 11<br>
 * 3 6 9<br>
 * 4 7 1<br>
 * 4 8 7<br>
 * 5 9 15<br>
 * 5 10 4<br>
 * 6 11 6<br>
 * 6 12 10<hr>
 * 예제 출력 1 <br>
 * 45
 */
public class DiameterOfTree {
    private static int longestNode = 0;
    private static int max = Integer.MIN_VALUE;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // init
        int n = Integer.parseInt(br.readLine());
        List<Node>[] graph = new ArrayList[n+1];
        for(int i = 0; i < n + 1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < n-1;i++){
            String[] nums = br.readLine().split(" ");
            graph[Integer.parseInt(nums[0])]
                    .add(new Node(Integer.parseInt(nums[1]), Integer.parseInt(nums[2])));
            graph[Integer.parseInt(nums[1])]
                    .add(new Node(Integer.parseInt(nums[0]), Integer.parseInt(nums[2])));
        }
        dfs(graph, 0,1,0);
        // max init
        max = Integer.MIN_VALUE;
        dfs(graph, 0, longestNode,0);
        bw.write(max + "\n");
        bw.flush();
        bw.close();
    }

    private static void dfs(List<Node>[] graph, int preNode, int curNode, int length) {
            if(max < length){
                max = length;
                longestNode = curNode;
            }

        for(Node node : graph[curNode]){
            if(node.to == preNode){
                continue;
            }
            dfs(graph, curNode, node.to,length + node.weight);
        }
    }

    static class Node{
        int to, weight;
        Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        // init
        int n = Integer.parseInt(br.readLine());
        List<Node>[] graph = new ArrayList[n+1];
        for(int i = 0; i < n + 1; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 0; i < n-1;i++){
            String[] nums = br.readLine().split(" ");
            graph[Integer.parseInt(nums[0])]
                    .add(new Node(Integer.parseInt(nums[1]), Integer.parseInt(nums[2])));
            graph[Integer.parseInt(nums[1])]
                    .add(new Node(Integer.parseInt(nums[0]), Integer.parseInt(nums[2])));
        }
        dfs(graph, 0,1,0);
        // max init
        max = Integer.MIN_VALUE;
        dfs(graph, 0, longestNode,0);
        System.out.printf(max + "\n");
    }
}
