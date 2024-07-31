import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon14215 {
    public static void main(String[] args) throws IOException {
        new Baekjoon14215().answer();
    }

    public void answer() throws IOException {
        int[] arr = new int[3];
        String[] token = new String[3];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        token = br.readLine().split(" ");

        for (int i = 0; i < 3; i++) {
            arr[i] = Integer.parseInt(token[i]);
        }

        Arrays.sort(arr);

        if (arr[0] + arr[1] > arr[2]) {
            System.out.println(arr[0] + arr[1] + arr[2]);
        } else {
            arr[2] = arr[0] + arr[1] - 1;
            System.out.println(arr[0] + arr[1] + arr[2]);
        }

        br.close();
    }
}
