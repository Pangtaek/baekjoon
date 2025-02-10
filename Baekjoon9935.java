import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String boom = br.readLine();

        System.out.println(solution(str, boom));
    }

    public static String solution(String str, String boom) {
        StringBuilder sb = new StringBuilder();
        int boomLen = boom.length();

        for (char c : str.toCharArray()) {
            sb.append(c); // 문자열에 문자 추가

            // 폭발 문자열이 나타났는지 확인
            if (sb.length() >= boomLen && sb.substring(sb.length() - boomLen).equals(boom)) {
                sb.setLength(sb.length() - boomLen); // 폭발 문자열 제거
            }
        }

        return sb.length() == 0 ? "FRULA" : sb.toString();
    }
}
