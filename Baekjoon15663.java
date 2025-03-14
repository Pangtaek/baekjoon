import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon15663 {

    public static int N;
    public static int M;
    public static int[] arr;
    public static int[] data;
    public static boolean[] visited;

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        data = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        solution(0);

        System.out.print(sb);
    }

    public static void solution(int depth) {
        if (depth == M) {
            for (int num : data) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prevNum = -1; 
        for (int i = 0; i < N; i++) {
            if (!visited[i] && arr[i] != prevNum) { 
                visited[i] = true;
                data[depth] = arr[i];
                prevNum = arr[i];
                solution(depth + 1);
                visited[i] = false;
            }
        }
    }
}
