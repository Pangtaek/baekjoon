import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon11718 {
    public static void main(String[] args) throws IOException {
        Baekjoon11718 baekjoon11718 = new Baekjoon11718();
        baekjoon11718.answer();
    }

    public void answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "";

        while ((str = br.readLine()) != null) {
            System.out.println(str);
        }

        br.close();
    }
}