import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1507 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] dist = new int[N][N];
        int[][] original = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());
                original[i][j] = dist[i][j];
            }
        }

        // 불필요한 도로 제거 체크
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j || i ==  k || j == k)
                        continue;

                    if (dist[i][j] == dist[i][k] + dist[k][j]) {
                        original[i][j] = 0;
                    }

                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        System.out.println("-1"); // 유효하지 않은 입력
                        return;
                    }
                }
            }
        }

        // 필요한 도로들의 총합
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                sum += original[i][j];
            }
        }

        System.out.println(sum);
    }
}
