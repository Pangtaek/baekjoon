import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Baekjoon11005
 */
public class Baekjoon11005 {
    public static void main(String[] args) throws IOException {
        Baekjoon11005 baekjoon11005 = new Baekjoon11005();
        System.err.println(baekjoon11005.answer());
    }

    // 목표: 10진법 수 N을 B진법으로 출력한다.
    public StringBuilder answer() throws IOException {
        StringBuilder answer = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        br.close();

        // 대문자 65~90
        while (N != 0) {
            if (N % B >= 10)
                answer.append((char) ((N % B) + 55));
            else
                answer.append(N % B);
            N /= B;
        }

        return answer.reverse();
    }

}
