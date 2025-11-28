import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon1264 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                if (line.equals("#"))
                    break;

                int count = 0;
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);
                    if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
                            ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
                        count++;
                    }
                }

                bw.write(String.valueOf(count));
                bw.newLine();
            }

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
