package org.ll.backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * 문제<br>
 * 민혁이는 소셜 네트워크 사이트에서 친구를 만드는 것을 좋아하는 친구이다. 우표를 모으는 취미가 있듯이, 민혁이는 소셜 네트워크 사이트에서 친구를 모으는 것이 취미이다.
 * 어떤 사이트의 친구 관계가 생긴 순서대로 주어졌을 때, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.
 * 친구 네트워크란 친구 관계만으로 이동할 수 있는 사이를 말한다.<hr>
 * 입력<br>
 * 첫째 줄에 테스트 케이스의 개수가 주어진다. 각 테스트 케이스의 첫째 줄에는 친구 관계의 수 F가 주어지며, 이 값은 100,000을 넘지 않는다. 다음 F개의 줄에는 친구 관계가 생긴 순서대로 주어진다. 친구 관계는 두 사용자의 아이디로 이루어져 있으며, 알파벳 대문자 또는 소문자로만 이루어진 길이 20 이하의 문자열이다.<hr>
 * 출력<br>
 * 친구 관계가 생길 때마다, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.<hr>
 * 예제 입력 1 <br>
 * 2<br>
 * 3<br>
 * Fred Barney<br>
 * Barney Betty<br>
 * Betty Wilma<br>
 * 3<br>
 * Fred Barney<br>
 * Betty Wilma<br>
 * Barney Betty<hr>
 * 예제 출력 1 <br>
 * 2<br>
 * 3<br>
 * 4<br>
 * 2<br>
 * 2<br>
 * 4
 */
public class FriendNetwork {
    // 이름에 ID를 부여
    private static Map<String, Integer> nameToId;
    private static int[] parent;
    private static int[] size;

    /**
     * Union-find 자료구조를 사용하여 구현
     * @param arg
     * @throws IOException
     */
    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        int N = Integer.parseInt(br.readLine());
        // 케이스  N회
        for(int n = 0; n<N;n++){
            int F = Integer.parseInt(br.readLine());
            parent =new int[F*2];
            size = new int[F*2];
            nameToId = new HashMap<>();
            int id = 0;

            for(int f = 0; f<F;f++){
                String[] line = br.readLine().split(" ");

                if(!nameToId.containsKey(line[0])){
                    nameToId.put(line[0], id);
                    parent[id] = id;
                    size[id]++;
                    id++;
                }
                if(!nameToId.containsKey(line[1])){
                    nameToId.put(line[1], id);
                    parent[id] = id;
                    size[id]++;
                    id++;
                }
                int p1 = nameToId.get(line[0]), p2 = nameToId.get(line[1]);
                union(p1, p2);
                System.out.println(nameToId);
                System.out.println(Arrays.toString(parent));
                System.out.println(Arrays.toString(size));
                System.out.println(size[find(p1)]);
            }
        }


    }

    /**
     * 부모 찾기<br>
     * 속해 있는 집답의 루트를 찾아서 반환
     * @param x 가 어떤 집합에 속해 있는지 찾기
     * @return x가 속해 있는 집합의 최종 루트 반환
     */
    private static int find(int x){
        if(parent[x] != x){
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /**
     * 집합 합치기<br>
     * 합치고자 하는 집합의 어떤 원소 x,y를 사용하여 두 집합을 크기 기준으로 합한다.
     * @param x 어떤 집합의 한 원소
     * @param y 어떤 집합의 한 원소
     */
     private static void union(int x, int y){
        int rx = find(x);
        int ry = find(y);
        if(rx != ry){
            if(size[rx] > size[ry]){
                size[rx] = size[rx] + size[ry];
                parent[ry] = rx;
            }else{
                size[ry] = size[ry] + size[rx];
                parent[rx] = ry;
            }
        }
     }
}
