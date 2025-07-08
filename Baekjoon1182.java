import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1182 {

    private static int N;
    private static int S;
    private static int[] A;

    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = tokens[0];
        S = tokens[1];

        A = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        dfs(0, 0);

        // 공집합 제외
        if (S == 0)
            answer--;

        bw.write(Integer.toString(answer));
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
    }

    private static void dfs(int idx, int sum) {
        if (idx == N) {
            if (sum == S)
                answer++;
            return;
        }

        // 현재 원소 포함
        dfs(idx + 1, sum + A[idx]);

        // 현재 원소 미포함
        dfs(idx + 1, sum);
    }
}
