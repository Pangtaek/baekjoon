import java.util.Scanner;

public class Baekjoon11382 {

    public static void main(String[] args) {
        Baekjoon11382 baekjoon11382 = new Baekjoon11382();
        System.out.println(baekjoon11382.answer());

    }

    public long answer() {
        long answer = 0;

        Scanner sc = new Scanner(System.in);
        String question = sc.nextLine();
        String arr[] = question.split(" ");

        for (String str : arr) {
            answer += Long.parseLong(str);
        }
        sc.close();

        return answer;
    }
}