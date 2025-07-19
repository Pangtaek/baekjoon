import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2576 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean isFind = false;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 7; i++) {
            int input = Integer.parseInt(br.readLine());

            if (input % 2 != 0) {
                isFind = true;
                sum += input;
                min = Math.min(min, input);
            }
        }

        if (!isFind) {
            bw.write(Integer.toString(-1));
            bw.newLine();
        } else {
            bw.write(sum + "\n");
            bw.write(min + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
