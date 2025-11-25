import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon3047 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            Arrays.sort(tokens);
            String order = br.readLine().trim();

            for (int i = 0; i < 3; i++) {
                char ch = order.charAt(i);
                if (ch == 'A')
                    bw.write(tokens[0] + " ");
                else if (ch == 'B')
                    bw.write(tokens[1] + " ");
                else if (ch == 'C')
                    bw.write(tokens[2] + " ");
            }
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
