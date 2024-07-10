// 2743번: 단어 길이 재기

import java.util.Scanner;

public class Baekjoon2743 {
    public static void main(String[] args) {
        Baekjoon2743 baekjoon2743 = new Baekjoon2743();
        System.out.println(baekjoon2743.answer());
    }

    public int answer() {
        int answer = 0;

        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        answer = str.length();

        sc.close();

        return answer;
    }
}
