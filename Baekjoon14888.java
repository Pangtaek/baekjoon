import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon14888 {

    private static int N;
    private static int[] A;
    private static int[] operators; // +, -, *, /
    private static long max = Long.MIN_VALUE;
    private static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        operators = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        dfs(1, A[0]);

        bw.write(getResult());

        bw.flush();
        br.close();
    }

    private static String getResult() {
        return max + "\n" + min + "\n";
    }

    private static void dfs(int depth, int current) {
        if (depth == N) {
            max = Math.max(max, current);
            min = Math.min(min, current);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operators[i] > 0) {
                operators[i]--;

                int next = 0;
                switch (i) {
                    case 0:
                        next = current + A[depth];
                        break;
                    case 1:
                        next = current - A[depth];
                        break;
                    case 2:
                        next = current * A[depth];
                        break;
                    case 3:
                        next = current / A[depth];
                        break;
                }

                dfs(depth + 1, next);

                operators[i]++;
            }
        }
    }
}
