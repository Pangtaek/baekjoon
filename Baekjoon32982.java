import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon32982 {

    static class Meal {
        int start, end;

        Meal(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int N = tokens[0]; // 복용 일 수
        int K = tokens[1]; // 약효 지속 시간

        tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        // 아침, 점심, 저녁 식사 시간
        Meal[] meals = new Meal[3];
        meals[0] = new Meal(tokens[0], tokens[1]); // 아침
        meals[1] = new Meal(tokens[2], tokens[3]); // 점심
        meals[2] = new Meal(tokens[4], tokens[5]); // 저녁

        int curr = meals[0].end; // 최근 약 복용 시간: 첫날 아침식사의 종료시간
        boolean isCovered = true;

        for (int day = 1; day <= N; day++) {
            // 아침 약 → 점심 시작 전까지
            if (curr + K < meals[1].start) {
                isCovered = false;
                break;
            }
            curr = Math.min(curr + K, meals[1].end);

            // 점심 약 → 저녁 시작 전까지
            if (curr + K < meals[2].start) {
                isCovered = false;
                break;
            }
            curr = Math.min(curr + K, meals[2].end);

            // 저녁 약 → 다음날 아침 시작 전까지
            if (day < N) {
                if (curr + K - 1440 < meals[0].start) {
                    isCovered = false;
                    break;
                }
                curr = Math.min(curr + K - 1440, meals[0].end);
            }
        }

        // 출력
        bw.write(isCovered ? "YES" : "NO");
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
