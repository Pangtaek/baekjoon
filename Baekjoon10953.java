import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon10953 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int[] tokens = Arrays.stream(br.readLine().split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            bw.write(Integer.toString(tokens[0] + tokens[1]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
