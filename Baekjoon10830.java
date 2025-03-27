import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon10830 {
    static int N;
    static long B;
    static int[][] origin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        origin = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }

        int[][] result = power(origin, B);

        StringBuilder sb = new StringBuilder();
        for (int[] row : result) {
            for (int val : row) {
                sb.append(val).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    // 행렬 제곱 (분할 정복)
    static int[][] power(int[][] matrix, long exp) {
        if (exp == 1) {
            return matrix;
        }

        int[][] temp = power(matrix, exp / 2);
        temp = multiply(temp, temp);

        if (exp % 2 == 1) {
            temp = multiply(temp, origin);
        }

        return temp;
    }

    // 행렬 곱셈
    static int[][] multiply(int[][] A, int[][] B) {
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += A[i][k] * B[k][j];
                }
                result[i][j] = sum % 1000;
            }
        }

        return result;
    }
}
