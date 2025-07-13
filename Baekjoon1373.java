import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1373 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String binary = br.readLine();
        StringBuilder sb = new StringBuilder();

        // 자릿수 맞추기 (앞에서부터 3의 배수로 만들기 위해 앞에 0 추가)
        int padding = (3 - (binary.length() % 3)) % 3;
        for (int i = 0; i < padding; i++) {
            binary = "0" + binary;
        }

        // 3자리씩 끊어서 8진수로 변환
        for (int i = 0; i < binary.length(); i += 3) {
            String group = binary.substring(i, i + 3);
            int octalDigit = Integer.parseInt(group, 2);
            sb.append(octalDigit);
        }

        bw.write(sb.toString());
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
