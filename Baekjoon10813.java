// 10810번: 공 바꾸기

import java.util.Scanner;

public class Baekjoon10813 {
    public static void main(String[] args) {
        Baekjoon10813 baekjoon10813 = new Baekjoon10813();

        for (int n : baekjoon10813.answer()) {
            System.out.print(n + " ");
        }
    }

    /*
     * 입력값
     * 5 4
     * 1 2
     * 3 4
     * 1 4
     * 2 2
     */

    public int[] answer() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int answer[] = new int[N];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = i + 1;
        }

        for (int i = 0; i < M; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            int tmp = answer[x - 1];
            answer[x - 1] = answer[y - 1];
            answer[y - 1] = tmp;
        }

        sc.close();

        return answer;
    }
}
