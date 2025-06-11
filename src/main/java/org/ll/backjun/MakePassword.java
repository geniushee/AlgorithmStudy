package org.ll.backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

/**
 * 백준 1759번 <hr>
 * 문제<br>
 * 바로 어제 최백준 조교가 방 열쇠를 주머니에 넣은 채 깜빡하고 서울로 가 버리는 황당한 상황에 직면한 조교들은, 702호에 새로운 보안 시스템을 설치하기로 하였다. 이 보안 시스템은 열쇠가 아닌 암호로 동작하게 되어 있는 시스템이다.<br>
 * 암호는 서로 다른 L개의 알파벳 소문자들로 구성되며 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되어 있다고 알려져 있다. 또한 정렬된 문자열을 선호하는 조교들의 성향으로 미루어 보아 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되었을 것이라고 추측된다. 즉, abc는 가능성이 있는 암호이지만 bac는 그렇지 않다.<br>
 * 새 보안 시스템에서 조교들이 암호로 사용했을 법한 문자의 종류는 C가지가 있다고 한다. 이 알파벳을 입수한 민식, 영식 형제는 조교들의 방에 침투하기 위해 암호를 추측해 보려고 한다. C개의 문자들이 모두 주어졌을 때, 가능성 있는 암호들을 모두 구하는 프로그램을 작성하시오.<hr>
 * 입력<br>
 * 첫째 줄에 두 정수 L, C가 주어진다. (3 ≤ L ≤ C ≤ 15) 다음 줄에는 C개의 문자들이 공백으로 구분되어 주어진다. 주어지는 문자들은 알파벳 소문자이며, 중복되는 것은 없다.<hr>
 * 출력<br>
 * 각 줄에 하나씩, 사전식으로 가능성 있는 암호를 모두 출력한다.<hr>
 * 예제 입력 1 <hr>
 * 4 6<br>
 * a t c i s w<hr>
 * 예제 출력 1 <br>
 * acis<br>
 * acit<br>
 * aciw<br>
 * acst<br>
 * acsw<br>
 * actw<br>
 * aist<br>
 * aisw<br>
 * aitw<br>
 * astw<br>
 * cist<br>
 * cisw<br>
 * citw<br>
 * istw
 */
public class MakePassword {
    public static Queue<String> search(final int c,final int l,final String[] strs){
        // 오름차순 정렬
        Arrays.sort(strs);
        // answer
        Queue<String> answer = new LinkedList<>();

        String current = "";
        for(int i = 0; i < l; i++){
            dfs(c, l, i, current + strs[i] ,answer, strs);
        }

        return answer;
    }

    private static void dfs(int c, int l, int i, String current, Queue<String> answer, String[] strs) {
        if(current.length() == c){
            int mo = 0;
            int ja = 0;
            List<Character> aeiou = List.of('a', 'e', 'i', 'o','u');
            for(Character a : current.toCharArray()){
                if(aeiou.contains(a)){
                    mo++;
                }else{
                    ja++;
                }

                if(mo > 0 && ja > 1){
                    answer.add(current);
                    return ;
                }
            }
            return ;
        }

        for(int j = i+1; j < l; j++){
            dfs(c,l,j,current + strs[j], answer, strs);
        }
    }

    public static void main(String s) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(s));
        String line = br.readLine();

        StringTokenizer st = new StringTokenizer(line);
        int c = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        String[] strs = new String[l];
        int i = 0;
        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            strs[i++] = st.nextToken();
        }

        for(String bit : search(c,l, strs)){
            System.out.println(bit);
        }
    }
}
