import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon14889 {
    static int answer = Integer.MAX_VALUE;
    static int N;
    static int[][] S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        S = new int[N][N];

        for (int i = 0; i < N; i++) {
            S[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        boolean[] selected = new boolean[N];
        dfs(selected, 0, 0);

        bw.write(Integer.toString(answer));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(boolean[] selected, int depth, int index) {
        if (depth == N / 2) {
            int diff = calculateDifference(selected);
            answer = Math.min(answer, diff);
            return;
        }

        for (int i = index; i < N; i++) {
            if (!selected[i]) {
                selected[i] = true;
                dfs(selected, depth + 1, i + 1);
                selected[i] = false;
            }
        }
    }

    static int calculateDifference(boolean[] selected) {
        int start = 0;
        int link = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (selected[i] && selected[j]) {
                    start += S[i][j];
                } else if (!selected[i] && !selected[j]) {
                    link += S[i][j];
                }
            }
        }

        return Math.abs(start - link);
    }
}
