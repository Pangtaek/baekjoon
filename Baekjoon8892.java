import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon8892 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int t = Integer.parseInt(br.readLine());

            for(int idx=0; idx<t; idx++) {
                int n = Integer.parseInt(br.readLine());
                String[] arr = new String[n];
                StringBuilder sb;
                int isTrue = 0;
                String answer = "";

                for (int i = 0; i < n; i++) {
                    arr[i] = br.readLine();
                }

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (i != j) {
                            sb = new StringBuilder();
                            sb.append(arr[i]).append(arr[j]);

                            if (isPalindrome(sb.toString())) {
                                answer = sb.toString();
                                isTrue++;
                            }

                            if (isTrue >= 2) {
                                System.out.println(answer);

                                return;
                            }
                        }
                    }
                }

                if(answer.equals("")){
                    System.out.println(0);

                    return;
                }

                System.out.println(answer);
            }


        } catch (IOException e) {
            System.out.println("백승윤");
        }
    }

    public static boolean isPalindrome(String str) {
        boolean answer = true;

        int start = 0;
        int end = str.length() - 1;

        while (start < end) {
            if (str.charAt(start) != str.charAt(end)) {
                break;
            }

            start++;
            end--;
        }

        if (start < end) {
            answer = false;
        }

        return answer;
    }
}