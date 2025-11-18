import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2138 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine());
            char[] start = br.readLine().toCharArray();
            char[] goal = br.readLine().toCharArray();

            int result = Math.min(
                    flipBulbs(start.clone(), goal, false),
                    flipBulbs(start.clone(), goal, true));

            bw.write(result == Integer.MAX_VALUE ? "-1\n" : result + "\n");
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int flipBulbs(char[] bulbs, char[] goal, boolean firstFlip) {
        int count = 0;
        int N = bulbs.length;

        if (firstFlip) {
            flip(bulbs, 0);
            count++;
        }

        for (int i = 1; i < N; i++) {
            if (bulbs[i - 1] != goal[i - 1]) {
                flip(bulbs, i);
                count++;
            }
        }

        // 마지막 전구 상태가 목표와 일치하는지 확인
        for (int i = 0; i < N; i++) {
            if (bulbs[i] != goal[i]) {
                return Integer.MAX_VALUE; // 불가능한 경우
            }
        }
        return count;
    }

    private static void flip(char[] bulbs, int index) {
        // 현재 인덱스 전구 상태 토글
        toggle(bulbs, index);
        // 이전 전구 토글 가능하면 토글
        if (index - 1 >= 0)
            toggle(bulbs, index - 1);
        // 다음 전구 토글 가능하면 토글
        if (index + 1 < bulbs.length)
            toggle(bulbs, index + 1);
    }

    private static void toggle(char[] bulbs, int idx) {
        bulbs[idx] = bulbs[idx] == '0' ? '1' : '0';
    }
}
