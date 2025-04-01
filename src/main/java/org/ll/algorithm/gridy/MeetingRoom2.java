package org.ll.algorithm.gridy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * 문제<br>
 * 한 개의 회의실이 있는데 이를 사용하고자 하는 N개의 회의에 대하여 회의실 사용표를 만들려고 한다. 각 회의 I에 대해 시작시간과 끝나는 시간이 주어져 있고, 각 회의가 겹치지 않게 하면서 회의실을 사용할 수 있는 회의의 최대 개수를 찾아보자. 단, 회의는 한번 시작하면 중간에 중단될 수 없으며 한 회의가 끝나는 것과 동시에 다음 회의가 시작될 수 있다. 회의의 시작시간과 끝나는 시간이 같을 수도 있다. 이 경우에는 시작하자마자 끝나는 것으로 생각하면 된다.
 *<hr>
 * 입력<br>
 * 첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다. 시작 시간과 끝나는 시간은 231-1보다 작거나 같은 자연수 또는 0이다.
 */
public class MeetingRoom2 {

    public static int run(String s) throws IOException {
        // 초기 세팅
        BufferedReader br = new BufferedReader(new StringReader(s));
        int N = Integer.parseInt(br.readLine());
        Integer[][] meetings = new Integer[N][2];
        for(int i =0;i<N;i++){
            String line = br.readLine();
            String[] bit = line.split(" ");
            meetings[i][0] = Integer.parseInt(bit[0]);
            meetings[i][1] = Integer.parseInt(bit[1]);
        }

        // 미팅룸 끝나는 시간이 늦은 순으로 정렬, 끝나는 시간이 같다면 더 늦게 시작하는 순서로.
        Arrays.sort(meetings, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                if(!Objects.equals(o1[1], o2[1])){
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

//        System.out.println(Arrays.deepToString(meetings));

        int start = meetings[0][0], end = meetings[0][1];
        int count = 1;

        for(int i=1;i<N;i++){
            if(end <= meetings[i][0]){
//                System.out.println(Arrays.toString(meetings[i]));
                start = meetings[i][0];
                end = meetings[i][1];
                count++;
            }
        }

        return count;
    }
}
