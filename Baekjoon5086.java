import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon5086 {
    public static void main(String[] args) throws IOException {
        Baekjoon5086 baekjoon5086 = new Baekjoon5086();
        System.out.println(baekjoon5086.answer());
    }

    public StringBuilder answer() throws IOException {
        StringBuilder answer = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int x, y;

        String f = "factor\n";
        String m = "multiple\n";
        String n = "neither\n";

        while (true) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken()); // 첫 번째 수
            y = Integer.parseInt(st.nextToken()); // 두 번째 수

            if (x == 0 && y == 0) {
                break; // 0 0 입력 시 종료
            }

            if (x == 0 || y == 0) {
                answer.append(n);
            } else if (y % x == 0) {
                answer.append(m);
            } else if (x % y == 0) {
                answer.append(f);
            } else {
                answer.append(n);
            }
        }

        br.close();

        return answer;
    }
}
