// 27866번: 문자와 문자열

import java.util.Scanner;

public class Baekjoon27866 {
    public static void main(String[] args) {
        Baekjoon27866 baekjoon27866 = new Baekjoon27866();
        System.out.println(baekjoon27866.answer());
    }

    public char answer() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int n = sc.nextInt();

        sc.close();

        char answer = str.charAt(n - 1);

        return answer;
    }
}
