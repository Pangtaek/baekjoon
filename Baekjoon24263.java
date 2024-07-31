import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon24263 {
    public static void main(String[] args) throws IOException {
        new Baekjoon24263().answer();
    }

    public void answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        br.close();

        System.out.println(n);
        System.out.println(1);
    }
}
