import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Baekjoon15894
 */
public class Baekjoon15894 {

    public static void main(String[] args) throws IOException {
        System.out.println(new Baekjoon15894().answer());
    }

    public long answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());

        br.close();

        return N * 4;
    }
}