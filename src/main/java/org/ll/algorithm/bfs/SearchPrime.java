package org.ll.algorithm.bfs;

import java.util.*;

/**
 * 소수 경로<br>
 * 4자리 소수로 구성된 두 비밀번호가 주어질 때, 한 비밀번호에서 다른 비밀번호로 변환하기 위한 최소 단계를 구하는 프로그램을 작성하세요.
 * 변환 규칙:<br>
 * 1. 한 번에 한 자리의 숫자만 변경할 수 있습니다.<br>
 * 2. 변환 과정의 모든 숫자는 4자리 소수여야 합니다.<br>
 * 3. 비밀번호는 1000 이상의 4자리 수여야 합니다.<br>
 */
public class SearchPrime {
    private static HashSet<Integer> primes = new HashSet<>();

    public SearchPrime() {
        primes.add(2);
    }

    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        if (primes.contains(num)) {
            return true;
        }
        for (int i = 2; i * i < num; i++) {
            if(isPrime(i)){
                if (num % i == 0) {
                    return false;
                }
            }
        }
        primes.add(num);
        return true;
    }

    public static int searchMinStep(int start, int target){
        Map<String, Integer> visited = new HashMap<>();
        Queue<String> readyQueue = new LinkedList<>();

        visited.put(String.valueOf(start), 0);
        readyQueue.add(String.valueOf(start));

        if(start == target){
            return 0;
        }

        // bfs 구현
        while(!readyQueue.isEmpty()){
            String q = readyQueue.poll();

            for(int i = 0; i < 4; i++){
                String prefix = q.substring(0,i);
                String endWith = q.substring(i+1);
                String mid = q.substring(i);
                String newOne;

                for(int j = 0;j < 10; j++){
                    if(i == 0 && j == 0){
                        continue;
                    }
                    if(mid.equals(String.valueOf(j))){
                        continue;
                    }
                    newOne = prefix + j + endWith;
                    if(!visited.containsKey(newOne) && isPrime(Integer.parseInt(newOne))){
//                        System.out.println(newOne);
                        if(newOne.equals(String.valueOf(target))){
                            return visited.get(q)+1;
                        }
                        visited.put(newOne,visited.get(q) + 1);
                        readyQueue.add(newOne);
                    }
                }
            }
        }
        return -1;
    }
}
