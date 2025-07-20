import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2744 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] input = br.readLine().toCharArray();

        for (int i = 0; i < input.length; i++) {
            if (Character.isUpperCase(input[i])) {
                bw.write(Character.toLowerCase(input[i]));
            } else {
                bw.write(Character.toUpperCase(input[i]));
            }
        }
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
