using System;
using System.Collections.Generic;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon18500
    {
        public void Solve()
        {
            using (var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
            using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
            {
                int[] tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
                int R = tokens[0];
                int C = tokens[1];
                char[,] cave = new char[R, C];

                for (int row = 0; row < R; row++)
                {
                    string line = reader.ReadLine();
                    for (int column = 0; column < C; column++)
                    {
                        cave[row, column] = line[column];
                    }
                }

                int count = int.Parse(reader.ReadLine());
                tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);

                for (int i = 0; i < count; i++)
                {
                    int height = tokens[i];
                    int y = R - height;

                    // 미네랄 파괴
                    bool isDestroyed = false;
                    if ((i & 1) == 0)    // 왼쪽에서 오른쪽
                    {
                        for (int x = 0; x < C; x++)
                        {
                            if (cave[y, x] == 'x')
                            {
                                cave[y, x] = '.';
                                isDestroyed = true;
                                break;
                            }
                        }
                    }
                    else    // 오른쪽에서 왼쪽
                    {
                        for (int x = C - 1; x >= 0; x--)
                        {
                            if (cave[y, x] == 'x')
                            {
                                cave[y, x] = '.';
                                isDestroyed = true;
                                break;
                            }
                        }
                    }

                    if (isDestroyed)
                    {
                        // 떠있는 클러스터 찾기 및 떨어뜨리기
                        DropFloatingClusters(cave, R, C);
                    }
                }

                for (int row = 0; row < R; row++)
                {
                    for (int column = 0; column < C; column++)
                    {
                        writer.Write(cave[row, column]);
                    }
                    writer.WriteLine();
                }
            }
        }

        private void DropFloatingClusters(char[,] cave, int R, int C)
        {
            bool[,] visited = new bool[R, C];

            // 바닥과 연결된 모든 클러스터 마킹
            for (int x = 0; x < C; x++)
            {
                if (cave[R - 1, x] == 'x' && !visited[R - 1, x])
                {
                    MarkCluster(cave, visited, R - 1, x, R, C);
                }
            }

            // 떠있는 클러스터 찾기
            List<(int y, int x)> floatingClusterList = new List<(int, int)>();
            for (int y = 0; y < R; y++)
            {
                for (int x = 0; x < C; x++)
                {
                    if (cave[y, x] == 'x' && !visited[y, x])
                    {
                        floatingClusterList.Add((y, x));
                    }
                }
            }

            if (floatingClusterList.Count == 0)
            {
                return;
            }

            // 떠있는 클러스터를 얼마나 떨어뜨려야 하는지 계산
            int dropDistance = CalculateDropDistance(cave, floatingClusterList, R, C);

            // 클러스터 떨어뜨리기
            if (dropDistance > 0)
            {
                // 먼저 지우기
                foreach (var floatingCluster in floatingClusterList)
                {
                    cave[floatingCluster.y, floatingCluster.x] = '.';
                }

                // 새 위치에 그리기
                foreach (var floatingCluster in floatingClusterList)
                {
                    cave[floatingCluster.y + dropDistance, floatingCluster.x] = 'x';
                }
            }
        }

        private void MarkCluster(char[,] cave, bool[,] visited, int startY, int startX, int R, int C)
        {
            Queue<(int y, int x)> queue = new Queue<(int, int)>();
            queue.Enqueue((startY, startX));
            visited[startY, startX] = true;

            int[,] directions = new int[,] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

            while (queue.Count > 0)
            {
                var current = queue.Dequeue();
                int y = current.y;
                int x = current.x;

                for (int d = 0; d < 4; d++)
                {
                    int ny = y + directions[d, 0];
                    int nx = x + directions[d, 1];

                    if (ny < 0 || ny >= R || nx < 0 || nx >= C)
                    {
                        continue;
                    }

                    if (visited[ny, nx] || cave[ny, nx] != 'x')
                    {
                        continue;
                    }

                    visited[ny, nx] = true;
                    queue.Enqueue((ny, nx));
                }
            }
        }

        private int CalculateDropDistance(char[,] cave, List<(int y, int x)> clusterList, int R, int C)
        {
            int minDrop = R;

            foreach (var cluster in clusterList)
            {
                int drop = 0;
                // 현재 위치에서 아래로 얼마나 떨어질 수 있는지 계산
                for (int ny = cluster.y + 1; ny < R; ny++)
                {
                    // 같은 클러스터의 미네랄이면 통과
                    if (clusterList.Contains((ny, cluster.x)))
                    {
                        continue;
                    }

                    // 다른 미네랄이나 바닥에 닿으면 멈춤
                    if (cave[ny, cluster.x] == 'x')
                    {
                        break;
                    }

                    drop++;
                }

                // 바닥까지의 거리 계산
                int distanceToBottom = R - 1 - cluster.y;
                int actualDrop = Math.Min(drop, distanceToBottom);

                minDrop = Math.Min(minDrop, actualDrop);
            }

            return minDrop;
        }
    }
}
