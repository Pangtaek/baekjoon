import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon11721 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input = br.readLine();
        int len = input.length();

        for (int i = 0; i < len; i += 10) {
            int end = Math.min(i + 10, len);
            bw.write(input.substring(i, end));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
