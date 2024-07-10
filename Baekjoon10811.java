// 10811번: 바구니 뒤집기

import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon10811 {
    public static void main(String[] args) {
        Baekjoon10811 baekjoon10811 = new Baekjoon10811();
        for (int n : baekjoon10811.answer()) {
            System.out.print(n + " ");
        }
    }

    /**
     * input
     * 5 4
     * 1 2
     * 3 4
     * 1 4
     * 2 2
     */

    /**
     * ouput
     * 3 4 1 2 5
     */

    public int[] answer() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int answer[] = new int[N];

        for (int i = 0; i < N; i++) {
            answer[i] = i + 1;
        }

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            reverse(answer, x - 1, y);
            // for (int n : answer) {
            // System.out.print(n + " ");
            // }
            // System.out.println();
        }

        sc.close();

        return answer;
    }

    static void reverse(int[] arr, int s, int e) {
        int[] copy = Arrays.copyOfRange(arr, s, e);

        int len = copy.length;

        for (int i = 0; i < len; i++) {
            arr[s + i] = copy[len - (i + 1)];
        }
    }
}
