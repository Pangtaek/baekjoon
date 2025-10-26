import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/*
 * 백준 2475번: 검증수
 * https://www.acmicpc.net/problem/2475
 * 
 * - 입력 받은 다섯 개의 수 각각을 제곱한 뒤 모두 더한 값을 10으로 나눈 나머지를 출력
 */

public class Baekjoon2475 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int[] arr = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int pow = 0;
            for (int n : arr) {
                pow += n * n;
            }

            bw.write(Integer.toString(pow % 10));
            bw.newLine();

            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
