import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Baekjoon5073
 */
public class Baekjoon5073 {

    public static void main(String[] args) throws IOException {
        new Baekjoon5073().answer();
    }

    /**
     * input
     * 7 7 7
     * 6 5 4
     * 3 2 5
     * 6 2 6
     * 0 0 0
     */

    public void answer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] token = new String[3];
        int[] arr = new int[3];

        while (true) {
            token = br.readLine().split(" ");

            for (int i = 0; i < 3; i++) {
                arr[i] = Integer.parseInt(token[i]);
            }

            if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0)
                break;

            Arrays.sort(arr);

            if (arr[2] >= arr[0] + arr[1]) {
                System.out.println("Invalid");
            } else {
                if (arr[0] == arr[1] && arr[1] == arr[2]) {
                    System.out.println("Equilateral");
                } else if (arr[0] == arr[1] || arr[1] == arr[2] || arr[0] == arr[2]) {
                    System.out.println("Isosceles");
                } else {
                    System.out.println("Scalene");
                }
            }

        }

        br.close();
    }
}