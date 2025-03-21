import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon16987 {

    public static int N;
    public static int[][] eggs;
    public static int maxBrokenEggs = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i][0] = Integer.parseInt(st.nextToken()); // 내구도
            eggs[i][1] = Integer.parseInt(st.nextToken()); // 무게
        }

        dfs(0, 0);
        System.out.println(maxBrokenEggs);
    }

    public static void dfs(int current, int brokenCount) {
        if (current == N) {
            maxBrokenEggs = Math.max(maxBrokenEggs, brokenCount);
            return;
        }

        // 현재 계란이 이미 깨져있다면 다음 계란으로 넘어감
        if (eggs[current][0] <= 0) {
            dfs(current + 1, brokenCount);
            return;
        }

        boolean hit = false;
        for (int i = 0; i < N; i++) {
            if (i == current || eggs[i][0] <= 0)
                continue;

            hit = true;
            eggs[current][0] -= eggs[i][1]; // 현재 계란 내구도 감소
            eggs[i][0] -= eggs[current][1]; // 상대 계란 내구도 감소

            int newBrokenCount = brokenCount;
            if (eggs[current][0] <= 0)
                newBrokenCount++;
            if (eggs[i][0] <= 0)
                newBrokenCount++;

            dfs(current + 1, newBrokenCount);

            // 백트래킹 (상태 복원)
            eggs[current][0] += eggs[i][1];
            eggs[i][0] += eggs[current][1];
        }

        // 깨뜨릴 계란이 없는 경우 다음 계란으로 넘어감
        if (!hit) {
            dfs(current + 1, brokenCount);
        }
    }
}
