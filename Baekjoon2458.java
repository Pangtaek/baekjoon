import java.io.*;
import java.util.*;

public class Baekjoon2458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] line1 = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int N = line1[0]; // 학생들의 수
        int M = line1[1]; // 두 학생 키를 비교한 횟수
        boolean[][] matrix = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            int[] input = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            matrix[input[0]][input[1]] = true;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (matrix[i][k] && matrix[k][j]) {
                        matrix[i][j] = true;
                    }
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (matrix[i][j] || matrix[j][i])
                    count++;
            }

            if (count == N - 1)
                answer++;
        }
        
        System.out.println(answer);
    }
}
