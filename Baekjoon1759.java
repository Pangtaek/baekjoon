import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon1759 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[] data = br.readLine().replace(" ", "").toCharArray();

        System.out.println(solution(L, C, data));
    }

    public static String solution(int L, int C, char[] data) {
        StringBuilder answer = new StringBuilder();

        Arrays.sort(data); // 사전순 정렬
        dfs(0, L, new char[L], 0, C, data, answer);

        return answer.toString();
    }

    public static void dfs(int currentDepth, int maximumDepth, char[] arr, int start, int C, char[] data,
            StringBuilder memory) {
        if (currentDepth == maximumDepth) {
            if (isValid(arr)) {
                memory.append(arr).append("\n");
            }
            return;
        }

        for (int i = start; i < C; i++) {
            arr[currentDepth] = data[i];
            dfs(currentDepth + 1, maximumDepth, arr, i + 1, C, data, memory);
        }
    }

    public static boolean isValid(char[] arr) {
        int jaum = 0;
        int moum = 0;

        for (char c : arr) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                moum++;
            } else {
                jaum++;
            }
        }

        return jaum >= 2 && moum >= 1;
    }
}
