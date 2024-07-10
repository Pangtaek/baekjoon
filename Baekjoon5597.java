// 5597번: 과제 안내신분..?

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon5597 {
    public static void main(String[] args) {
        Baekjoon5597 baekjoon5597 = new Baekjoon5597();
        for (int n : baekjoon5597.answer(baekjoon5597.answer())) {
            System.out.println(n);
        }
    }

    /**
     * 입력값
     * 3
     * 1
     * 4
     * 5
     * 7
     * 9
     * 6
     * 10
     * 11
     * 12
     * 13
     * 14
     * 15
     * 16
     * 17
     * 18
     * 19
     * 20
     * 21
     * 22
     * 23
     * 24
     * 25
     * 26
     * 27
     * 28
     * 29
     * 30
     */

    public boolean[] answer() {
        boolean answer[] = new boolean[30];
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 28; i++) {
            int sno = sc.nextInt() - 1;
            answer[sno] = true;
        }

        sc.close();

        return answer;
    }

    public Queue<Integer> answer(boolean[] arr) {
        Queue<Integer> answer = new LinkedList<>();

        for (int i = 0; i < 30; i++) {
            if (arr[i] != true) {
                answer.add(i + 1);
            }
        }

        return answer;
    }
}
