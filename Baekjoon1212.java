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
                sb.append(binary);
            } else {
                sb.append("000".substring(binary.length())).append(binary);
            }
        }

        bw.write(sb.toString());
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
