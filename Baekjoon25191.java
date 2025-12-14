import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon25191 {
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine()); // 치킨집에 있는 치킨 수

            int[] tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int A = tokens[0]; // 콜라 개수
            int B = tokens[1]; // 맥주 개수

            int maxByDrinks = A / 2 + B; // 집에 있는 음료로 만들 수 있는 최대 치킨 수
            int answer = Math.min(N, maxByDrinks);

            bw.write(String.valueOf(answer));
            bw.write("\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
