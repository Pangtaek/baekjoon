import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon2745 {
    public static void main(String[] args) throws IOException {
        Baekjoon2745 baekjoon2745 = new Baekjoon2745();
        System.out.println(baekjoon2745.answer());
    }

    // 목표: B진법의 수 N을 10진법으로 변환하여 출력
    public int answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // B진법의 수 N
        String N = st.nextToken();
        int B = Integer.parseInt(st.nextToken());

        br.close();

        int digit = 1;
        int answer = 0;

        for (int i = N.length() - 1; i >= 0; i--) {
            char C = N.charAt(i);

            if ('A' <= C && C <= 'Z') {
                answer += (C - 'A' + 10) * digit;
            } else {
                answer += (C - '0') * digit;
            }
            digit *= B;
        }

        return answer;
    }

}
