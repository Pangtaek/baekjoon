import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1182 {

    static int N;
    static int S;
    static int[] A;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        N = tokens[0];
        S = tokens[1];
        A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        dfs(0, 0); // 부분수열 탐색 시작

        // S가 0인 경우, 공집합도 포함되므로 제외해야 함
        if (S == 0)
            count--;

        bw.write(Integer.toString(count));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int index, int sum) {
        if (index == N) {
            if (sum == S) {
                count++;
            }
            return;
        }

        // 현재 index 값을 포함
        dfs(index + 1, sum + A[index]);

        // 현재 index 값을 포함하지 않음
        dfs(index + 1, sum);
    }
}
