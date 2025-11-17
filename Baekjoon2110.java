import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon2110 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int N = tokens[0]; // 집의 개수
            int C = tokens[1]; // 공유기의 개수

            int[] routers = new int[N];

            for (int i = 0; i < N; i++) {
                routers[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(routers); // 집 위치 정렬

            int left = 1; // 최소 거리
            int right = routers[N - 1] - routers[0]; // 최대 거리
            int answer = 0;

            while (left <= right) {
                int mid = (left + right) / 2;
                int count = 1; // 첫 번째 집에는 무조건 설치
                int prev = routers[0];

                for (int i = 1; i < N; i++) {
                    if (routers[i] - prev >= mid) {
                        count++;
                        prev = routers[i];
                    }
                }

                if (count >= C) {
                    answer = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            bw.write(answer + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
