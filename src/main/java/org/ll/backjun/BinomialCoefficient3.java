package org.ll.backjun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * 문제<br>
 * 자연수
 * N과 정수
 * K가 주어졌을 때 이항 계수
 * \(\binom{N}{K}\)를 1,000,000,007로 나눈 나머지를 구하는 프로그램을 작성하시오.<hr>
 *
 * 입력<br>
 * 첫째 줄에
 * \(N\)과
 * \(K\)가 주어진다. (1 ≤
 * \(N\) ≤ 4,000,000, 0 ≤
 * \(K\) ≤
 * \(N\))<hr>
 * 출력<br>
 *
 * \(\binom{N}{K}\)를 1,000,000,007로 나눈 나머지를 출력한다.<hr>
 *
 * 예제 입력 1 <br>
 * 5 2<hr>
 * 예제 출력 1<br>
 * 10
 */
public class BinomialCoefficient3 {

    public static void run(String arg) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(arg));
        String[] line = br.readLine().split(" ");
        int mod = 1000000007;
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);

        long[] fac = new long[n+1];
        long[] inverse = new long[n+1];

        //  팩토리얼
        fac[0] = 1;
        for(int i = 1;i <= n;i++){
            fac[i] = fac[i-1] * i % mod;
        }

        // 리버스
        inverse[n] = modPow(fac[n], mod - 2, mod);
        for(int i = n;i>0;i--){
            inverse[i-1] = inverse[i] * i % mod;
        }

        System.out.println(fac[n] * inverse[n-k] % mod *inverse[k] % mod);
    }

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int mod = 1000000007;
        int n = Integer.parseInt(line[0]);
        int k = Integer.parseInt(line[1]);

        long[] fac = new long[n+1];
        long[] inverse = new long[n+1];

        //  팩토리얼
        fac[0] = 1;
        for(int i = 1;i <= n;i++){
            fac[i] = fac[i-1] * i % mod;
        }

        // 리버스
        inverse[n] = modPow(fac[n], mod - 2, mod);
        for(int i = n;i>0;i--){
            inverse[i-1] = inverse[i] * i % mod;
        }

        System.out.println(fac[n] * inverse[n-k] % mod *inverse[k] % mod);
    }

    public static long modPow(long a, long b, long mod) {
        long result = 1;
        a %= mod;

        while (b > 0) {
            if ((b & 1) == 1) {  // b가 홀수면 곱하기
                result = (result * a) % mod;
            }
            a = (a * a) % mod;
            b >>= 1;  // b를 절반으로 나눔
        }

        return result;
    }
}
