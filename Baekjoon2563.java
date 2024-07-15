import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2563 {

    public static void main(String[] args) throws IOException {
        Baekjoon2563 baekjoon2563 = new Baekjoon2563();
        baekjoon2563.answer();
    }

    public void answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[][] paper = new boolean[100][100]; // 도화지 크기
        int totalArea = 0;

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            String[] sa = str.split(" ");
            int x = Integer.parseInt(sa[0]);
            int y = Integer.parseInt(sa[1]);

            // 10x10 크기의 색종이를 도화지에 표시
            for (int j = x; j < x + 10; j++) {
                for (int k = y; k < y + 10; k++) {
                    if (!paper[j][k]) {
                        paper[j][k] = true;
                        totalArea++;
                    }
                }
            }
        }

        System.out.println(totalArea);
        br.close();
    }
}
