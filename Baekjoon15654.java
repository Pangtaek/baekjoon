import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon15654 {

    public static StringBuilder sb = new StringBuilder();
    public static int N, M;
    public static int[] data;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        data = new int[N];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(data);
        solution(0, new int[M]);

        System.out.println(sb.toString());
    }
    
    public static void solution(int depth, int[] arr) {

        if (depth == M) {
            for (int i : arr) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                arr[depth] = data[i];
                visited[i] = true;
                solution(depth + 1, arr);
                visited[i] = false;
            }
        }
    }
}