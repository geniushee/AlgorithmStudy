package org.ll.algorithm.dp;

import java.util.Arrays;

public class ContinuousMaxSum {
    public static int maxSum(int[] arr){
        int[] dp1 = new int[arr.length];
        int[] dp2 = new int[arr.length];

        // 첫 번째 값
        dp1[0] = arr[0];
        // 첫 번째를 제외한 값
        dp2[0] = 0;
        //최종 결과
        int result = dp1[0];

        for(int i = 1;i <arr.length; i++){
            dp1[i] = Math.max(dp1[i-1] + arr[i], arr[i]);
            dp2[i] = Math.max(dp1[i-1], dp2[i-1]+arr[i]);
            result = Math.max(result, Math.max(dp1[i], dp2[i]));
            System.out.println("dp1 : "+ Arrays.toString(dp1));
            System.out.println("dp2 : "+ Arrays.toString(dp2));
            System.out.println("result : "+ result);
        }
        return result;
    }
}
