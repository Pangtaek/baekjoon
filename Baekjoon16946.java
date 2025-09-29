import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 * 16946: 벽 부수고 이동하기 4
 * https://www.acmicpc.net/problem/16946
 * 
 * 풀이법: BFS
 * 1. 0인 영역들을 BFS로 그룹핑하여 각 그룹의 크기를
 *    groupSize 리스트에 저장하고, 각 위치의 그룹 ID를 groupId 배열에 저장한다.
 * 2. 각 위치별로 벽(1)인 경우, 인접한 서로 다른 그룹들의 크기를 합산하여
 *   벽을 부쉈을 때 이동 가능한 칸의 개수를 계산한다.
 * 3. 결과를 10으로 나눈 나머지를 출력한다.
 */
public class Baekjoon16946 {
    
    private static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
    private static int N, M;
    private static int[][] map;
    private static int[][] groupId; // 각 위치의 그룹 ID
    private static List<Integer> groupSize; // 각 그룹의 크기

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out))) {

            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            
            map = new int[N][M];
            groupId = new int[N][M];
            groupSize = new ArrayList<>();
            
            // 입력 받기
            for (int row = 0; row < N; row++) {
                String line = br.readLine();
                for (int column = 0; column < M; column++) {
                    map[row][column] = Character.getNumericValue(line.charAt(column));
                }
            }

            // 0인 영역들을 그룹핑
            groupAreasByBFS();
            
            StringBuilder answer = new StringBuilder();
            
            // 각 위치별로 답 계산
            for (int row = 0; row < N; row++) {
                for (int column = 0; column < M; column++) {
                    if (map[row][column] == 0) {
                        answer.append(0);
                    } else {
                        // 벽인 경우: 인접한 서로 다른 그룹들의 크기 합산
                        int count = calculateWallBreakCount(row, column);
                        answer.append(count % 10);
                    }
                }
                answer.append("\n");
            }
            
            bw.write(answer.toString());
            bw.flush();
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }
    
    // BFS로 0인 영역들을 그룹핑
    private static void groupAreasByBFS() {
        int currentGroupId = 1;
        groupSize.add(0); // index 0은 사용하지 않음
        
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < M; column++) {
                if (map[row][column] == 0 && groupId[row][column] == 0) {
                    int size = bfs(row, column, currentGroupId);
                    groupSize.add(size);
                    currentGroupId++;
                }
            }
        }
    }
    
    // BFS로 연결된 0 영역의 크기 계산 및 그룹 ID 할당
    private static int bfs(int startRow, int startCol, int groupIdValue) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol});
        groupId[startRow][startCol] = groupIdValue;
        
        int count = 1;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            
            for (int[] direction : DIRECTIONS) {
                int nextX = x + direction[0];
                int nextY = y + direction[1];
                
                if (isInRange(nextX, nextY) && 
                    map[nextX][nextY] == 0 && 
                    groupId[nextX][nextY] == 0) {
                    
                    groupId[nextX][nextY] = groupIdValue;
                    queue.offer(new int[]{nextX, nextY});
                    count++;
                }
            }
        }
        
        return count;
    }
    
    // 벽을 부쉈을 때 이동 가능한 칸의 개수 계산
    private static int calculateWallBreakCount(int row, int column) {
        Set<Integer> adjacentGroups = new HashSet<>();
        
        // 4방향의 인접한 그룹들 찾기
        for (int[] direction : DIRECTIONS) {
            int nextX = row + direction[0];
            int nextY = column + direction[1];
            
            if (isInRange(nextX, nextY) && map[nextX][nextY] == 0) {
                adjacentGroups.add(groupId[nextX][nextY]);
            }
        }
        
        // 인접한 그룹들의 크기 합산 + 부순 벽(1)
        int totalCount = 1;
        for (int group : adjacentGroups) {
            totalCount += groupSize.get(group);
        }
        
        return totalCount;
    }
    
    private static boolean isInRange(int row, int column) {
        return row >= 0 && row < N && column >= 0 && column < M;
    }
}
