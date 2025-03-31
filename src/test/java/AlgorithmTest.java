import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ll.algorithm.bfs.Emoticon;
import org.ll.algorithm.bfs.SearchPrime;
import org.ll.algorithm.bfs.ShortestDistance;
import org.ll.algorithm.dp.ContinuousMaxSum;
import org.ll.backjun.SetProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class AlgorithmTest {
    @Test
    public void continuousMaxSum() {
        String inputData = "10 -4 3 1 5 6 -35 12 21 -1";
        int[] arr = Arrays.stream(inputData.trim().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int max = ContinuousMaxSum.maxSum(arr);

        Assertions.assertEquals(54, max);
    }

    @Test
    public void searchPrimeLoot() throws IOException {
        String input = """
                3
                1033 8179
                1373 8017
                1033 1033
                """;
        BufferedReader br = new BufferedReader(new StringReader(input));
        String line = br.readLine();
        int count = Integer.parseInt(line);
        int[] answer = new int[count];
        int[][] arr = new int[count][2];
        for (int i = 0; i < count; i++) {
            line = br.readLine();
            String[] strs = line.split(" ");
            arr[i][0] = Integer.parseInt(strs[0]);
            arr[i][1] = Integer.parseInt(strs[1]);
        }

        for (int i = 0; i < count; i++) {
            int step = SearchPrime.searchMinStep(arr[i][0], arr[i][1]);
            answer[i] = step;
            System.out.println("start: " + arr[i][0] + " target: " + arr[i][1] + " step: " + step);
        }
        Assertions.assertEquals(6, answer[0]);
        Assertions.assertEquals(7, answer[1]);
        Assertions.assertEquals(0, answer[2]);
    }

    @Test
    @DisplayName("이모티콘 갯수가 같은가?")
    public void countEmoticon() {
        Assertions.assertEquals(2, Emoticon.bfs(2));
        Assertions.assertEquals(4, Emoticon.bfs(4));
        Assertions.assertEquals(5, Emoticon.bfs(6));
    }

    @Test
    @DisplayName("최단거리-가중치")
    public void shortestDistance() {
        int V = 5;
        int E = 6;
        int K = 1;
        Integer[][] arr;
        arr = new Integer[][]{new Integer[]{5, 1, 1}, new Integer[]{1, 2, 2}, new Integer[]{1, 3, 3}, new Integer[]{2, 3, 4}, new Integer[]{2, 4, 5}, new Integer[]{3, 4, 6}};
        Integer[] result = ShortestDistance.search(V, E, K, arr);
        Integer[] correct = new Integer[]{0, 0, 2, 3, 7, -1};
        for (int i = 1; i <= result.length; i++) {
            Assertions.assertEquals(correct[i], result[i]);
        }
    }

    @Test
    @DisplayName("집합")
    public void set() throws IOException {
        String input = """
                26
                add 1
                add 2
                check 1
                check 2
                check 3
                remove 2
                check 1
                check 2
                toggle 3
                check 1
                check 2
                check 3
                check 4
                all
                check 10
                check 20
                toggle 10
                remove 20
                check 10
                check 20
                empty
                check 1
                toggle 1
                check 1
                toggle 1
                check 1""";
        List<Integer> result = SetProblem.run(input);
        StringTokenizer st = new StringTokenizer("""
                1
                1
                0
                1
                0
                1
                0
                1
                0
                1
                1
                0
                0
                0
                1
                0""");
        int i = 0;
        while (st.hasMoreTokens()) {
            int cur = Integer.parseInt(st.nextToken());
            System.out.println((i + 1) + "번째 : " + result.get(i) + " / " + cur);
            Assertions.assertEquals(result.get(i++), cur);
        }
    }

}
