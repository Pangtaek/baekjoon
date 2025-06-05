import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int X = Integer.parseInt(br.readLine());

        Arrays.sort(arr);
        int left = 0;
        int right = N - 1;
        int count = 0;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == X) {
                count++;
                left++;
                right--;
            } else if (sum < X) {
                left++;
            } else {
                right--;
            }
        }

        bw.write(Integer.toString(count));
        bw.newLine();
        bw.flush();
        bw.close();
        br.close();
    }
}
