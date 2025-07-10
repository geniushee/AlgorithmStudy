import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ll.algorithm.bfs.Emoticon;
import org.ll.algorithm.bfs.SearchPrime;
import org.ll.algorithm.bfs.ShortestDistance;
import org.ll.algorithm.binarySearch.BuildSharing;
import org.ll.algorithm.dp.ContinuousMaxSum;
import org.ll.algorithm.gridy.MeetingRoom2;
import org.ll.backjun.*;

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

    @Test
    @DisplayName("그리디 알고리즘 - 미팅룸")
    public void meetingRoom2() throws IOException {
        int result = MeetingRoom2.run("""
                11
                1 4
                3 5
                0 6
                5 7
                3 8
                5 9
                6 10
                8 11
                8 12
                2 13
                12 14""");

        Assertions.assertEquals(4,result);
    }

    @Test
    @DisplayName("공유기 설치")
    public void buildSharing() throws IOException {
        int distance = BuildSharing.run("""
                5 3
                1
                2
                8
                4
                9""");

        Assertions.assertEquals(3, distance);
    }

    @Test
    @DisplayName("수강신청")
    public void Num13414() throws IOException {
        int[] correct = new int[]{20103324, 20133221,20140101};
        int[] res = Num13414.main("""
                1 8
                20103324
                20133221
                20133221
                20093778
                20140101
                01234567
                20093778
                20103325""");
        for(int i=0;i<3;i++){
            Assertions.assertEquals(res[i],correct[i]);
        }

    }

    @Test
    @DisplayName("비밀번호 만들기")
    public void makePassword() throws IOException {

        MakePassword.main("""
                4 6
                a t c i s w""");
    }

    @Test
    @DisplayName("연구소")
    public void laboratory() throws IOException {
        int max = Laboratory.run("""
                7 7
                2 0 0 0 1 1 0
                0 0 1 0 1 2 0
                0 1 1 0 1 0 0
                0 1 0 0 0 0 0
                0 0 0 0 0 1 1
                0 1 0 0 0 0 0
                0 1 0 0 0 0 0""");
        System.out.println("max = "+max);
        max = Laboratory.run("""
                4 6
                0 0 0 0 0 0
                1 0 0 0 0 2
                1 1 1 0 0 2
                0 0 0 0 0 2""");
        System.out.println("max = "+max);
        max = Laboratory.run("""
                8 8
                2 0 0 0 0 0 0 2
                2 0 0 0 0 0 0 2
                2 0 0 0 0 0 0 2
                2 0 0 0 0 0 0 2
                2 0 0 0 0 0 0 2
                0 0 0 0 0 0 0 0
                0 0 0 0 0 0 0 0
                0 0 0 0 0 0 0 0""");
        System.out.println("max = "+max);
    }

    @Test
    @DisplayName("평범한 배낭")
    public void normalBackpack() throws IOException {
        NormalBackpack.run("""
                4 7
                6 13
                4 8
                3 6
                5 12""");
        NormalBackpack.run("""
                10 18
                6 13
                4 8
                3 6
                5 12
                9 20
                10 9
                7 14
                2 5
                11 23
                14 17
                """);
    }

    @Test
    @DisplayName("AC")
    public void ac() throws IOException {
        AC.main("""
                4
                RDDDD
                4
                [1,2,3,4]
                DD
                1
                [42]
                RRD
                6
                [1,1,2,3,5,8]
                D
                0
                []""");
    }

    @Test
    @DisplayName("tomato")
    public void tomato() throws IOException {
        Tomato.run("""
                6 4
                0 0 0 0 0 0
                0 0 0 0 0 0
                0 0 0 0 0 0
                0 0 0 0 0 1""");
        Tomato.run("""
                6 4
                0 -1 0 0 0 0
                -1 0 0 0 0 0
                0 0 0 0 0 0
                0 0 0 0 0 1""");
        Tomato.run("""
                6 4
                1 -1 0 0 0 0
                0 -1 0 0 0 0
                0 0 0 0 -1 0
                0 0 0 0 -1 1""");
        Tomato.run("""
                5 5
                -1 1 0 0 0
                0 -1 -1 -1 0
                0 -1 -1 -1 0
                0 -1 -1 -1 0
                0 0 0 0 0""");
        Tomato.run("""
                2 2
                1 -1
                -1 1""");

    }
    @Test
    @DisplayName("카드 정렬하기")
    public void orderCard() throws IOException {

        CardOrdering.run("""
                3
                10
                20
                40""");
    }

    @Test
    @DisplayName("LCS")
    public void LCS() throws IOException {

        LCS.run("""
                ACAYKP
                CAPCAK""");
    }

    @Test
    @DisplayName("NQueen")
    public void nQueen() throws IOException {

        NQueen.run("81");
    }

    @Test
    @DisplayName("적록색약")
    public void redGreen() throws IOException {

        RedGreen.run("""
                5
                RRRBB
                GGBBB
                BBBRR
                BBRRR
                RRRRR""");
    }

    @Test
    @DisplayName("두 용액")
    public void twoLiquid() throws IOException {

        TwoLiquid.run("""
                5
                -2 4 -99 -1 98""");
        TwoLiquid.run("""
                4
                -3 50 -99 -49""");
        TwoLiquid.run("""
                4
                -3 -50 -99 -49""");
        TwoLiquid.run("""
                4
                3 50 99 49""");
        TwoLiquid.run("""
                2
                1 2""");
        TwoLiquid.run("""
                2
                -1 -2""");
        TwoLiquid.run("""
                2
                -1 1""");
        TwoLiquid.run("""
                4
                -5 -2 3 6""");
    }

    @Test
    @DisplayName("다각형 넓이")
    public void AreaOfPolygon() throws IOException {

        AreaOfPolygon.run("""
                4
                0 0
                4 6
                10 8
                10 0""");
    }
    @Test
    @DisplayName("트리의 지름")
    public void diameterOfTree() throws IOException {

        DiameterOfTree.run("""
                12
                1 2 3
                1 3 2
                2 4 5
                3 5 11
                3 6 9
                4 7 1
                4 8 7
                5 9 15
                5 10 4
                6 11 6
                6 12 10""");
    }
    @Test
    @DisplayName("연속된 소수의 합")
    public void consecutiveSum() throws IOException {

        ConsecutiveSum.run("""
                20""");
        ConsecutiveSum.run("""
                3""");
        ConsecutiveSum.run("""
                41""");
        ConsecutiveSum.run("""
                53""");
        ConsecutiveSum.run("""
                4000000""");

    }
    @Test
    @DisplayName("구간 합")
    public void intervalSum() throws IOException {

        IntervalSum.run("""
                5 2 2
                1
                2
                200
                4
                5
                1 3 6
                2 2 5
                1 5 2
                2 3 5""");
    }
    @Test
    @DisplayName("가장 긴 증가하는 부분 수열")
    public void longestPartSequence() throws IOException {

        LongestPartSequence.run("""
                6
                10 20 10 30 20 50""");
        LongestPartSequence.run("""
                8
                10 20 10 30 20 50 30 10""");
        LongestPartSequence.run("""
                1
                10""");
        LongestPartSequence.run("""
                8
                10 0 0 0 0 0 0 0""");
        LongestPartSequence.run("""
                11
                10 20 30 40 50 60 70 80 90 100 110""");
        LongestPartSequence.run("""
                2
                10 20""");

    }

    @Test
    @DisplayName("나3곱2")
    public void na3gop2() throws IOException {

        Na3Gop2.run("""
                6
                4 8 6 3 12 9""");
        Na3Gop2.run("""
                4
                42 28 84 126""");
    }
    @Test
    @DisplayName("로봇 청소기")
    public void robotCleaner() throws IOException {

        RobotCleaner.run("""
                3 3
                1 1 0
                1 1 1
                1 0 1
                1 1 1""");
        RobotCleaner.run("""
                11 10
                7 4 0
                1 1 1 1 1 1 1 1 1 1
                1 0 0 0 0 0 0 0 0 1
                1 0 0 0 1 1 1 1 0 1
                1 0 0 1 1 0 0 0 0 1
                1 0 1 1 0 0 0 0 0 1
                1 0 0 0 0 0 0 0 0 1
                1 0 0 0 0 0 0 1 0 1
                1 0 0 0 0 0 1 1 0 1
                1 0 0 0 0 0 1 1 0 1
                1 0 0 0 0 0 0 0 0 1
                1 1 1 1 1 1 1 1 1 1""");
    }
    @Test
    @DisplayName("분수를 소수로")
    public void fractionToDecimal() throws IOException {

        FractionToDecimal.run("""
                6
                1 2
                1 7
                10 5
                11 5
                995 476
                991 994""");
    }
    @Test
    @DisplayName("부분합")
    public void partialSum() throws IOException {

        PartialSum.run("""
                10 15
                5 1 3 5 10 7 4 9 2 8""");
    }

    @Test
    @DisplayName("이항계수3")
    public void binomialCoefficient3() throws IOException {

        BinomialCoefficient3.run("""
                10 7""");
        BinomialCoefficient3.run("""
                5 2""");
        BinomialCoefficient3.run("""
                4000000 200000""");
    }

    @Test
    @DisplayName("토마토2")
    public void tomato2() throws IOException {

        Tomato2.run("""
                5 3 1
                0 -1 0 0 0
                -1 -1 0 1 1
                0 0 0 1 1""");
        Tomato2.run("""
                5 3 2
                0 0 0 0 0
                0 0 0 0 0
                0 0 0 0 0
                0 0 0 0 0
                0 0 1 0 0
                0 0 0 0 0""");
        Tomato2.run("""
                4 3 2
                1 1 1 1
                1 1 1 1
                1 1 1 1
                1 1 1 1
                -1 -1 -1 -1
                1 1 1 -1""");

    }

    @Test
    @DisplayName("수 묶기")
    public void bindNumber() throws IOException {

        BindingNumber.run("""
                4
                -1
                2
                1
                3""");
        BindingNumber.run("""
                6
                0
                1
                2
                4
                3
                5""");
        BindingNumber.run("""
                1
                -1""");
        BindingNumber.run("""
                3
                -1
                0
                1""");
        BindingNumber.run("""
                2
                1
                1""");

    }

    @Test
    @DisplayName("알파벳")
    public void alphabet() throws IOException {

        Alphabet.run("""
                2 4
                CAAB
                ADCB""");
        Alphabet.run("""
                3 6
                HFDFFB
                AJHGDH
                DGAGEH""");
        Alphabet.run("""
                5 5
                IEFCJ
                FHFKC
                FFALF
                HFGCF
                HMCHH""");
        Alphabet.run("""
                1 1
                I""");
    }
}
