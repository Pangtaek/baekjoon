import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon5691 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            while (true) {
                int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                int A = tokens[0];
                int B = tokens[1];

                // 종료 조건
                if (A == 0 && B == 0) {
                    break;
                }

                // C = 2 * A - B
                int C = 2 * A - B;

                bw.write(String.valueOf(C));
                bw.write("\n");
            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
