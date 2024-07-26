import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1085 {
    public static void main(String[] args) throws IOException {
        System.out.println(new Baekjoon1085().answer());
    }

    public int answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split(" ");
        int x = Integer.parseInt(token[0]);
        int y = Integer.parseInt(token[1]);
        int w = Integer.parseInt(token[2]);
        int h = Integer.parseInt(token[3]);

        br.close();
        int min1 = Math.min(x, y);
        int min2 = Math.min(Math.abs(x - w), Math.abs(y - h));
        return Math.min(min1, min2);
    }
}
