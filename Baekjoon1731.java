import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1731 {
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int N = Integer.parseInt(br.readLine());

            int[] sequence = new int[N];
            for (int i = 0; i < N; i++) {
                sequence[i] = Integer.parseInt(br.readLine());
            }

            int a1 = sequence[0];
            int a2 = sequence[1];
            int a3 = sequence[2];

            int next;

            // 등차수열인지 확인
            if (a2 - a1 == a3 - a2) {
                int diff = a2 - a1;
                next = sequence[N - 1] + diff;
            } else {
                int ratio = a2 / a1;
                next = sequence[N - 1] * ratio;
            }

            bw.write(String.valueOf(next));
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
