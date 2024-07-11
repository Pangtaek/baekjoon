import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon10988 {
    public static void main(String[] args) throws IOException {
        Baekjoon10988 baekjoon10988 = new Baekjoon10988();
        System.out.println(baekjoon10988.answer());
    }

    public int answer() throws IOException {
        // true: 1
        // false: 0
        int answer = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return 0;
            }
        }

        br.close();

        return answer;
    }
}
