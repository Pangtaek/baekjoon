import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon28239 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            long m = Long.parseLong(br.readLine());
            int x = -1, y = -1;

            // 비트 인덱스 확인 (0부터 시작)
            for (int bit = 0; bit < 63; bit++) {
                if (((m >> bit) & 1) == 1) {
                    if (x == -1)
                        x = bit;
                    else if (y == -1) {
                        y = bit;
                        break;
                    }
                }
            }

            // 1비트만 있을 때는 앞의 인덱스를 사용
            if (y == -1) {
                x = x - 1;
                y = x;
            }

            bw.write(x + " " + y);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
