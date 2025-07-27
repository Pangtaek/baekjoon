import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Baekjoon14002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] A = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] dp = new int[N];
        int[] prev = new int[N]; // 경로 추적용
        Arrays.fill(prev, -1);

        int maxLen = 0;
        int lastIndex = 0;

        // DP 계산
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                lastIndex = i;
            }
        }

        // 경로 추적
        List<Integer> lis = new ArrayList<>();
        int cur = lastIndex;
        while (cur != -1) {
            lis.add(A[cur]);
            cur = prev[cur];
        }
        Collections.reverse(lis);

        // 출력
        bw.write(maxLen + "\n");
        for (int num : lis) {
            bw.write(num + " ");
        }
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
