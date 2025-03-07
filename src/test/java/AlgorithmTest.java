import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ll.algorithm.bfs.SearchPrime;
import org.ll.algorithm.dp.ContinuousMaxSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

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

        for(int i = 0; i < count; i++){
            int step = SearchPrime.searchMinStep(arr[i][0], arr[i][1]);
            answer[i] = step;
            System.out.println("start: " + arr[i][0] + " target: " + arr[i][1] + " step: " + step);
        }
        Assertions.assertEquals(6,answer[0]);
        Assertions.assertEquals(7,answer[1]);
        Assertions.assertEquals(0,answer[2]);
    }
}
