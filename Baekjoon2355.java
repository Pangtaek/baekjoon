import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon2355 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
        long A = tokens[0];
        long B = tokens[1];

        long min = Math.min(A, B);
        long max = Math.max(A, B);

        long count = max - min + 1; // 항의 개수
        long sum = (count * (min + max)) / 2; // 등차수열 합 공식

        bw.write(Long.toString(sum));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
