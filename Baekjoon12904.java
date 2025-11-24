import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon12904 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String S = br.readLine();
            String T = br.readLine();

            while (S.length() < T.length()) {
                char last = T.charAt(T.length() - 1);
                T = T.substring(0, T.length() - 1); // 마지막 문자 제거

                if (last == 'B') {
                    T = new StringBuilder(T).reverse().toString();
                }
            }

            bw.write(S.equals(T) ? "1\n" : "0\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
