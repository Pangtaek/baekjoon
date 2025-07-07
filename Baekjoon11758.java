import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Baekjoon11758 {

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Point[] points = new Point[3];

        int[] tokens;
        for (int i = 0; i < 3; i++) {
            tokens = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            points[i] = new Point(tokens[0], tokens[1]);
        }

        bw.write(Integer.toString(ccw(points)));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
    
    private static int ccw(Point[] points) {
        int line1 = points[0].x * points[1].y + points[1].x * points[2].y + points[2].x * points[0].y;
        int line2 = points[0].y * points[1].x + points[1].y * points[2].x + points[2].y * points[0].x;

        if (line1 - line2 > 0) // 반시계 방향
            return 1;
        else if (line1 == line2) // 일직선 방향
            return 0;
        else // 시계 방향
            return -1;
    }
}
