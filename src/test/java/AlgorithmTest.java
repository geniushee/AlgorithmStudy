import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ll.algorithm.dp.ContinuousMaxSum;

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
}
