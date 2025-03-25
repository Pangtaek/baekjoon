import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon12865 {

    static int N, K;
    static int[] weight;
    static int[] value;
    static int maxValue = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 물건 개수
        K = Integer.parseInt(st.nextToken()); // 최대 무게

        weight = new int[N];
        value = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, 0); // 시작: index, 누적무게, 누적가치

        System.out.println(maxValue);
    }

    public static void dfs(int index, int currentWeight, int currentValue) {
        // 종료 조건: 모든 아이템을 고려한 경우
        if (index == N) {
            maxValue = Math.max(maxValue, currentValue);
            return;
        }

        // 선택하지 않는 경우
        dfs(index + 1, currentWeight, currentValue);

        // 선택하는 경우 (무게 초과 체크)
        if (currentWeight + weight[index] <= K) {
            dfs(index + 1, currentWeight + weight[index], currentValue + value[index]);
        }
    }
}
