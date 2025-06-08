import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1924 {
    public static void main(String[] args) throws IOException {
        final int[] months = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        final String[] days = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] tokens = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int month = tokens[0];
        int day = tokens[1];
        int sum = 0;

        for (int i = 0; i < month - 1; i++) {
            sum += months[i];
        }

        sum += day;

        bw.write(days[sum % 7]);
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
