import java.util.Scanner;

/**
 * Baekjoon10807
 */
public class Baekjoon10807 {

    public static void main(String[] args) {
        Baekjoon10807 baekjoon10807 = new Baekjoon10807();
        System.out.println(baekjoon10807.answer());
    }

    public int answer() {
        int answer = 0;
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int arr[] = new int[num];

        for (int i = 0; i < num; i++) {
            arr[i] = sc.nextInt();
        }

        int N = sc.nextInt();
        for (int i = 0; i < num; i++) {
            if (N == arr[i])
                answer++;
        }

        sc.close();

        return answer;
    }
}