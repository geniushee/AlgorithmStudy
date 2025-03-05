package org.ll.algorithm.binarySearch;

import java.util.Arrays;

/**
 * <p>랜선 자르기<br>
 * 오영식은 박성원의 부름을 받고 N개의 랜선을 만들어야 합니다. 오영식은 현재 K개의 랜선을 가지고 있는데, 각각의 랜선 길이가 다릅니다.
 * 랜선을 자를 때는 다음과 같은 규칙을 따릅니다:
 * 모든 랜선은 동일한 길이로 잘라야 합니다.
 * 자를 때는 센티미터 단위의 정수 길이로만 자를 수 있습니다.
 * 잘랐을 때 남는 부분은 버려도 됩니다.
 * 이미 자른 랜선은 붙일 수 없습니다.
 * N개보다 많이 만드는 것도 N개를 만드는 것에 포함됩니다.
 * 오영식이 만들 수 있는 최대 랜선의 길이를 구하는 프로그램을 작성하세요.
 *</p>
 * <p>입력 형식<br>
 * 첫째 줄에 두 개의 정수 K와 N이 주어집니다.<br>
 * K: 현재 가지고 있는 랜선의 개수 (1 ≤ K ≤ 10,000)<br>
 * N: 필요한 랜선의 개수 (1 ≤ N ≤ 1,000,000)<br>
 * K ≤ N<br>
 * 둘째 줄부터 K개의 줄에 걸쳐 각 랜선의 길이가 주어집니다.
 * 각 랜선의 길이는 자연수이며 2^31-1 이하입니다.
 *</p>
 * <p>출력 형식<br>
 * N개를 만들 수 있는 랜선의 최대 길이를 센티미터 단위의 정수로 출력합니다.
 *</p>
 * <p>예제 입력 1<br>
 * 4 11<br>
 * 802<br>
 * 743<br>
 * 457<br>
 * 539<br>
 * 예제 출력 1<br>
 * 200
 * </p>
 * <p>힌트<br>
 * 802cm 랜선에서 4개, 743cm 랜선에서 3개, 457cm 랜선에서 2개, 539cm 랜선에서 2개를 잘라내 모두 11개를 만들 수 있습니다.
 * 이진 탐색을 사용하여 가능한 랜선의 길이를 찾아보세요. 랜선의 길이는 1부터 가장 긴 랜선의 길이까지가 될 수 있습니다.
 * </p>
 */
public class CountLanWire {
    public static int maxLength(int K, int N, int[] wires){
        int max = Arrays.stream(wires).min().getAsInt();
        int min = 1;
        int mid = (min + max) / 2;

        while (min != max) {
            int count = 0;
            for (int wire : wires) {
                count += wire / mid;
            }
            if (count >= N) {
                min = mid;
            } else {
                max = mid - 1;
            }
            mid = (min + max + 1) / 2;

        }
        return mid;
    }
}
