import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon11404 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine()); // 도시의 개수
            int m = Integer.parseInt(br.readLine()); // 버스의 개수

            int[][] priceList = new int[n][n];
            // 그래프 초기화 (모든 경로를 INF로 설정)
            for (int i = 0; i < n; i++) {
                Arrays.fill(priceList[i], Integer.MAX_VALUE);
            priceList[i][i] = 0; // 자기 자신으로 가는 비용은 0
            }
            for (int i = 0; i < m; i++) {
                int[] data = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int from = data[0] - 1, to = data[1] - 1, cost = data[2];

            priceList[from][to] = Math.min(priceList[from][to], cost);
            }

            for (int baseArea = 0; baseArea < n; baseArea++) {
                for (int from = 0; from < n; from++) {
                    for (int to = 0; to < n; to++) {
                        if (priceList[from][baseArea] != Integer.MAX_VALUE &&priceList[baseArea][to] != Integer.MAX_VALUE) {
                        priceList[from][to] = Math.min(priceList[from][to],priceList[from][baseArea] +priceList[baseArea][to]);
                        }
                    }
                }
            }

             Arrays.stream(priceList)
                    .forEach(row -> {
                        Arrays.stream(row).forEach(col -> System.out.print((col == Integer.MAX_VALUE ? 0 : col) + " "));
                        System.out.println();
                    });
        } catch (Exception e) {
            System.out.println("error");
        }

    }
}
