import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon24264 {
    public static void main(String[] args) throws IOException {
        new Baekjoon24264().answer();
    }

    public void answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        System.out.println((long) (Math.pow(n, 2)));
        System.out.println(2);

        br.close();
    }
}
