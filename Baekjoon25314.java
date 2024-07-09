import java.util.Scanner;

public class Baekjoon25314 {
    public static void main(String[] args) {
        Baekjoon25314 baekjoon25314 = new Baekjoon25314();
        System.out.println(baekjoon25314.answer());
    }

    public String answer() {
        String answer = "";
        Scanner sc = new Scanner(System.in);

        int num = sc.nextInt();

        for (int i = 0; i < num / 4; i++) {
            answer += "long ";
        }

        answer += "int";

        sc.close();

        return answer;
    }
}
