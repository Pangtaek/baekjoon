import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon5717 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens;
        while (true) {
            tokens = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (tokens[0] == 0 && tokens[1] == 0)
                break;
                
            bw.write(Integer.toString(tokens[0] + tokens[1]));
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
