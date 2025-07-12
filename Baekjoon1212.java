import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1212 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String octal = br.readLine();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < octal.length(); i++) {
            int digit = octal.charAt(i) - '0';
            String binary = Integer.toBinaryString(digit);

            if (i == 0) {
                sb.append(binary); // 첫 자릿수는 앞의 0을 생략
            } else {
                sb.append(String.format("%3s", binary).replace(' ', '0')); // 나머지는 3자리 채우기
            }
        }

        bw.write(sb.toString());
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
