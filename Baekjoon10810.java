import java.util.Scanner;

/**
 * Baekjoon10810
 */
public class Baekjoon10810 {

    public static void main(String[] args) {
        Baekjoon10810 baekjoon10810 = new Baekjoon10810();
        System.out.println(baekjoon10810.answer());
    }

    /*
     * 입력값
     * 5 4
     * 1 2 3
     * 3 4 4
     * 1 4 1
     * 2 2 2
     */

    public String answer() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // N개의 바구니
        int M = sc.nextInt(); // M번 던짐

        int arr[] = new int[N]; // 바구니 생성
        for (int i = 0; i < M; i++) { // M번 반복
            int x = sc.nextInt(); // arr[x]부터
            int y = sc.nextInt(); // arr[y]까지
            int n = sc.nextInt(); // n으로 초기화
            for (int j = x - 1; j <= y - 1; j++) {
                arr[j] = n;
            }
            // System.out.println("\n" + (i + 1) + "회차");
            // System.out.println("-------------------");
            // for (int a : arr) {
            // System.out.print(a + " ");
            // }
            // System.out.println();
        }

        String answer = "";
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) {
                answer += arr[i] + " ";
            } else {
                answer += arr[i];
            }
        }

        sc.close();

        return answer;
    }
}