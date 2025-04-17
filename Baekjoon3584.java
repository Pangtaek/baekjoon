import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon3584 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] parent = new int[N + 1];

            for (int i = 0; i < N - 1; i++) {
                int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt).toArray();
                parent[tokens[1]] = tokens[0];
            }

            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
            int num1 = tokens[0];
            int num2 = tokens[1];

            boolean[] visited = new boolean[N + 1];

            // 1. num1의 조상들을 모두 기록
            while (num1 != 0) {
                visited[num1] = true;
                num1 = parent[num1];
            }

            // 2. num2의 조상을 타고 올라가며 visited된 첫 번째 노드가 공통 조상
            while (!visited[num2]) {
                num2 = parent[num2];
            }

            System.out.println(num2);
        }
    }
}
