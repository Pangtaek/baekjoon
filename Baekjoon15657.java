import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon15657 {

    public static int N;
    public static int M;
    public static int[] arr;
    public static int[] data;

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        data = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        solution(0, 0);

        System.out.print(sb);
    }

    public static void solution(int depth, int index) {
        if (depth == M) {
            for (int num : data) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = index; i < N; i++) {
            data[depth] = arr[i];
            solution(depth + 1, i);
        }
    }
}
