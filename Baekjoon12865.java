import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon12865 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 무게마다 가치를 저장할 배열
        int dp[] = new int[K + 1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            int W = Integer.parseInt(st2.nextToken());
            int V = Integer.parseInt(st2.nextToken());

            // 무게 뒤에서 부터 현재 해당 무게의 가치와
            // 현재 물건을 더했을 때 해당 무게와 같아지는 가치 중
            // 더 큰 가치를 해당 무게의 가치로 저장
            for (int j = K; j >= W; j--) {
                dp[j] = Math.max(dp[j], dp[j - W] + V);
            }
        }

        System.out.println(dp[K]);
    }
}
