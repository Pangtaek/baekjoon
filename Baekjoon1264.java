import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * 백준 1264번: 모음의 개수
 * https://www.acmicpc.net/problem/1264
 * 
 * - 문제 풀이:
 * 1. BufferedReader를 사용하여 여러 줄의 입력을 효율적으로 처리합니다
 * 2. 각 줄에 대해 모음(a, e, i, o, u)의 개수를 세고, 대소문자를 구분하지 않도록 처리합니다.
 * 3. 입력이 "#"인 경우 반복문을 종료합니다.
 * 4. BufferedWriter를 사용하여 결과를 출력합니다.
 * - 시간 복잡도: O(n), n은 입력된 전체 문자 수
 * - 공간 복잡도: O(1)
 */

public class Baekjoon1264 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            while (true) {
                // 1. 한 줄을 읽어옵니다.
                String line = br.readLine();

                // 2. 종료 조건 (입력된 줄이 "#"인지 확인)
                if (line.equals("#")) {
                    break; // while 루프를 종료합니다.
                }

                // 3. 모음 개수를 셉니다.
                int vowelCount = 0;
                for (int i = 0; i < line.length(); i++) {
                    char ch = line.charAt(i);

                    // 문자를 소문자로 변환하여 비교를 단순화합니다.
                    char lowerCh = Character.toLowerCase(ch);

                    if (lowerCh == 'a' || lowerCh == 'e' || lowerCh == 'i' || lowerCh == 'o' || lowerCh == 'u') {
                        vowelCount++;
                    }
                }

                // 4. 모음 개수를 출력합니다. (줄바꿈 포함)
                bw.write(vowelCount + "\n");
            }

            bw.flush(); // 버퍼에 남은 내용을 모두 출력

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}