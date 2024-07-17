import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2903 {
    public static void main(String[] args) throws IOException {
        Baekjoon2903 baekjoon2903 = new Baekjoon2903();
        System.out.println("res: " + baekjoon2903.answer());
    }

    public int answer() throws IOException {
        int answer = 4;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();
        // 변의 길이
        int edge = 2;
        for (int i = 0; i < N; i++) {
            int node = edge - 1;
            edge += node;
            answer = (int) Math.pow(edge, 2);
        }
        return answer;
    }
}
