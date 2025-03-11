import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon15650 {

    public static int[] memory;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        memory = new int[M];

        solution(N, M, 1, 0);
    }

    public static void solution(int N, int M, int start, int depth) {
        if (depth == M) {
            for (int num : memory) {
                System.out.print(num + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i <= N; i++) {
            memory[depth] = i;
            solution(N, M, i + 1, depth + 1);
        }
    }
}
