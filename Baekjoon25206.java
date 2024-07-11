import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Baekjoon25206 {
    public static void main(String[] args) throws IOException {
        Baekjoon25206 baekjoon25206 = new Baekjoon25206();
        System.out.println("평균학점: " + baekjoon25206.answer());
    }

    public float answer() throws IOException {
        float totalPoints = 0.0f;
        float totalCredits = 0.0f;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Float> credit = new HashMap<>();
        credit.put("A+", 4.5f);
        credit.put("A0", 4.0f);
        credit.put("B+", 3.5f);
        credit.put("B0", 3.0f);
        credit.put("C+", 2.5f);
        credit.put("C0", 2.0f);
        credit.put("D+", 1.5f);
        credit.put("D0", 1.0f);
        credit.put("F", 0.0f);

        for (int i = 0; i < 20; i++) {
            String[] str = br.readLine().split(" ");

            if (!str[2].equals("P")) { // "P" 성적은 제외
                float creditValue = Float.parseFloat(str[1]);
                totalCredits += creditValue;
                totalPoints += creditValue * credit.get(str[2]);
            }
        }

        br.close();

        float gpa = totalPoints / totalCredits;

        return gpa;
    }
}
