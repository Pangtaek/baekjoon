import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Baekjoon2754 {
    
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String grade = br.readLine();

            String score;
            switch (grade) {
                case "A+":
                    score = "4.3";
                    break;
                case "A0":
                    score = "4.0";
                    break;
                case "A-":
                    score = "3.7";
                    break;
                case "B+":
                    score = "3.3";
                    break;
                case "B0":
                    score = "3.0";
                    break;
                case "B-":
                    score = "2.7";
                    break;
                case "C+":
                    score = "2.3";
                    break;
                case "C0":
                    score = "2.0";
                    break;
                case "C-":
                    score = "1.7";
                    break;
                case "D+":
                    score = "1.3";
                    break;
                case "D0":
                    score = "1.0";
                    break;
                case "D-":
                    score = "0.7";
                    break;
                default: // "F"
                    score = "0.0";
            }

            bw.write(score);
            bw.write("\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
