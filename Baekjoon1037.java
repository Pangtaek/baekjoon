import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon1037 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        
        @SuppressWarnings("unused")
        int N = Integer.parseInt(br.readLine()); // 약수의 개수
        int[] divisors = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int min = Arrays.stream(divisors).min().getAsInt();
        int max = Arrays.stream(divisors).max().getAsInt();

        bw.write(Integer.toString(min * max));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
