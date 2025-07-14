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
    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        int N = Integer.parseInt(br.readLine());
        // 케이스  N회
        for(int n = 0; n < N; n++){
            // 초기화
            int F = Integer.parseInt(br.readLine());
            List<String>[] friends = new ArrayList[2*F];
            int id = 0;
            Map<String, Integer> personId = new HashMap<>();



            // readLine
            for(int f = 0; f<F;f++){
                String[] line = br.readLine().split(" ");
                Integer pId = personId.get(line[0]);
                if(pId == null){
                    personId.put(line[0], id++);
                    pId = personId.get(line[0]);
                }
                if(friends[pId] == null){
                    friends[pId] = new ArrayList<>();
                }
                friends[pId].add(line[1]);

                pId = personId.get(line[1]);
                if(pId == null){
                    personId.put(line[1], id++);
                    pId = personId.get(line[1]);
                }
                if(friends[pId] == null){
                    friends[pId] = new ArrayList<>();
                }
                friends[pId].add(line[0]);
//System.out.println(Arrays.deepToString(friends));
                showNetwork(friends, personId,line);
            }


        }
    }
    public static void showNetwork(List<String>[] friends, Map<String, Integer> ids, String[] twoPerson){
        Queue<String> nodes = new LinkedList<>();
        Set<String> unique = new HashSet<>();
        nodes.add(twoPerson[0]);
        nodes.add(twoPerson[1]);

        while(!nodes.isEmpty()){
            String person = nodes.poll();
//            System.out.println(person);
            if(!unique.contains(person)){
                unique.add(person);

                for(String f : friends[ids.get(person)]){
                    if(!unique.contains(f)){
                        nodes.add(f);
                    }
                }
            }
        }
        System.out.println(unique.size());
    }
}
