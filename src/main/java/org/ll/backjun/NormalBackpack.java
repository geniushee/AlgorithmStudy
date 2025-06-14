package org.ll.backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * 문제<br>
 * 이 문제는 아주 평범한 배낭에 관한 문제이다.<br>
 * 한 달 후면 국가의 부름을 받게 되는 준서는 여행을 가려고 한다. 세상과의 단절을 슬퍼하며 최대한 즐기기 위한 여행이기 때문에, 가지고 다닐 배낭 또한 최대한 가치 있게 싸려고 한다.<br>
 * 준서가 여행에 필요하다고 생각하는 N개의 물건이 있다. 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다. 아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다. 준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.<hr>
 * 입력<br>
 * 첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다. 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.<br>
 * 입력으로 주어지는 모든 수는 정수이다.<hr>
 * 출력<br>
 * 한 줄에 배낭에 넣을 수 있는 물건들의 가치합의 최댓값을 출력한다.<hr>
 * 예제 입력 1 <br>
 * 4 7<br>
 * 6 13<br>
 * 4 8<br>
 * 3 6<br>
 * 5 12<hr>
 * 예제 출력 1<br>
 * 14
 */
public class NormalBackpack {
    private static int n;
    private static int k;
    private static int[] maxValueOfweight;
    private static Thing[] things;
    private static boolean[] visited;

    public static void main(String[] args){

    }

    public static void run(String args) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(args));
        // 초기화
        String[] line = br.readLine().split(" ");
        n = Integer.parseInt(line[0]);
        k = Integer.parseInt(line[1]);
        maxValueOfweight = new int[k + 1];
        maxValueOfweight[0] = 0;

        things = new Thing[n];
        for(int i = 0; i<n;i++) {
            line = br.readLine().split(" ");
            things[i] =  new Thing(Integer.parseInt(line[0]),Integer.parseInt(line[1]));
        }
        visited = new boolean[n];

        // 첫번째 물건 넣기
        for(int i = 0; i < n; i++){
            dfs(i, things[i].weight, things[i].value);
        }

        int maxValue = 0;
        for(int value : maxValueOfweight){
            if(maxValue < value){
                maxValue = value;
            }
        }
        System.out.println("최대 가치는 " + maxValue);
    }

    private static void dfs(int idx, int curW, int curV) {
        // 최대치를 넘으면 나가기
        if(curW > k){
            return ;
        }

        // 현재 무게에서 가치가 부족하면 나가기
        if(curV < maxValueOfweight[curW]){
            return ;
        }

        // n번째 물건 방문처리 + 가치 더하기
        visited[idx] = true;

        maxValueOfweight[curW] = curV;

        // n + 1번째 물건 넣기
        for(int i  = idx + 1; i < n; i++){
            if(!visited[i]){
                dfs(i, curW + things[i].weight, curV + things[i].value);
            }
        }

        // 방문처리 원복
        visited[idx] = false;
    }

    static class Thing{
        private final int weight;
        private final int value;

        private Thing(int w, int v){
            weight = w;
            value = v;
        }
    }


}
