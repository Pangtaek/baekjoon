import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2558 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int sum = 0;
        for (int i = 0; i < 2; i++) {
            sum += Integer.parseInt(br.readLine());
        }
        
        bw.write(Integer.toString(sum));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
