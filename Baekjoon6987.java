import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon6987 {
    static int[][] matches = {
            // 홈 Vs. 어웨이
            { 0, 1 },
            { 0, 2 },
            { 0, 3 },
            { 0, 4 },
            { 0, 5 },
            { 1, 2 },
            { 1, 3 },
            { 1, 4 },
            { 1, 5 },
            { 2, 3 },
            { 2, 4 },
            { 2, 5 },
            { 3, 4 },
            { 3, 5 },
            { 4, 5 } };
    static int[][] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        score = new int[6][3];

        for (int testcase = 0; testcase < 4; testcase++) {
            int totalMatches = 0;
            int[] tokens = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int index = 0;
            for (int team = 0; team < 6; team++) {
                for (int result = 0; result < 3; result++) {
                    score[team][result] = tokens[index++];
                    totalMatches += score[team][result];
                }
            }

            if (totalMatches > 30) { // 31경기 이상 진행한 경우
                bw.write(Integer.toString(0) + " "); // 불가능
                continue;
            }

            if (play(0)) {
                bw.write(Integer.toString(1) + " ");
            } else {
                bw.write(Integer.toString(0) + " ");
            }
        }
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean play(int game) {
        if (game == 15) {
            return true;
        }

        // 홈 팀 승리
        if (score[matches[game][0]][0] > 0 && score[matches[game][1]][2] > 0) {
            score[matches[game][0]][0]--;
            score[matches[game][1]][2]--;
            if (play(game + 1))
                return true;
            score[matches[game][0]][0]++;
            score[matches[game][1]][2]++;
        }

        // 어웨이 팀 승리
        if (score[matches[game][0]][2] > 0 && score[matches[game][1]][0] > 0) {
            score[matches[game][0]][2]--;
            score[matches[game][1]][0]--;
            if (play(game + 1))
                return true;
            score[matches[game][0]][2]++;
            score[matches[game][1]][0]++;
        }

        // 무승부
        if (score[matches[game][0]][1] > 0 && score[matches[game][1]][1] > 0) {
            score[matches[game][0]][1]--;
            score[matches[game][1]][1]--;
            if (play(game + 1))
                return true;
            score[matches[game][0]][1]++;
            score[matches[game][1]][1]++;
        }

        return false;
    }
}