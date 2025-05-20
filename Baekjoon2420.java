import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2420 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] tokens = br.readLine().split(" ");
        long N = Long.parseLong(tokens[0]);
        long M = Long.parseLong(tokens[1]);

        long result = Math.abs(N - M);
        bw.write(Long.toString(result));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
