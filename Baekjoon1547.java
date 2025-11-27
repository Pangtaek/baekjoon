import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon1547 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int M = Integer.parseInt(br.readLine()); // 스왑 횟수
            int ball = 1; // 처음 공은 1번 컵 아래

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                if (ball == X) {
                    ball = Y;
                } else if (ball == Y) {
                    ball = X;
                }
            }

            bw.write(String.valueOf(ball));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
