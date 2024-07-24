import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon27323 {
    public static void main(String[] args) throws IOException {
        System.out.println(new Baekjoon27323().answer());
    }

    public int answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int y = Integer.parseInt(br.readLine());

        br.close();

        return x * y;
    }
}
