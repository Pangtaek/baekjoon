import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Baekjoon32980 {
    public static void main(String[] args) throws IOException {
        final char[] recyclableTypes = { 'P', 'C', 'V', 'S', 'G', 'F' };

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long totalPrice = 0;
        int N = Integer.parseInt(br.readLine());
        String[] trashList = new String[N];

        // 쓰레기의 문자열이 N개 주어진다.
        for (int i = 0; i < N; i++) {
            trashList[i] = br.readLine();
        }

        // 일반쓰레기를 제외한 쓰레기의 단위 크기당 비용이 공백으로 구분되어 순서대로 주어진다.
        long[] recyclableCosts = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        // 다음줄에 일반쓰레기의 단위 크기당 비용이 주어진다.
        long generalWasteCostPerUnit = Long.parseLong(br.readLine());

        // 쓰레기의 이니셜별 단위 크기당 비용을 Map형태로 저장한다.
        Map<Character, Long> costPerType = new TreeMap<>();
        for (int i = 0; i < recyclableTypes.length; i++) {
            costPerType.put(recyclableTypes[i], recyclableCosts[i]);
        }
        costPerType.put('O', generalWasteCostPerUnit);

        for (String trash : trashList) {
            char[] chars = trash.toCharArray();
            int size = chars.length;
            char type = chars[0];

            boolean isRecyclable = true;

            if (type == 'O' || !costPerType.containsKey(type)) {
                // 일반쓰레기면 일반배출한다.
                isRecyclable = false;
            } else {
                for (int i = 1; i < size; i++) {
                    if (chars[i] != type) {
                        isRecyclable = false;
                        break;
                    }
                }
            }

            long generalCost = calculateGeneralWaste(size, costPerType.get('O'));
            long recycleCost = isRecyclable // 분비배출 가능 여부
                    ? calculateSeparateTheTrash(size, costPerType.get(type)) // 참
                    : Integer.MAX_VALUE; // 거짓

            totalPrice += Math.min(generalCost, recycleCost);
        }

        bw.write(Long.toString(totalPrice));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }

    static long calculateSeparateTheTrash(int size, long costPerUnit) {
        return size * costPerUnit;
    }

    static long calculateGeneralWaste(int size, long costPerUnit) {
        return size * costPerUnit;
    }
}
