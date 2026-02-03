using System;
using System.Collections.Generic;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon2146
    {
        private static readonly int[,] DIRECTIONS = new int[,] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        public void Solve()
        {
            using (var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
            using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
            {
                int N = int.Parse(reader.ReadLine());
                int[,] map = new int[N, N];

                for (int y = 0; y < N; y++)
                {
                    int[] line = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
                    for (int x = 0; x < N; x++)
                    {
                        map[y, x] = line[x];
                    }
                }

                // 1. 섬 라벨링
                int islandNum = 2;
                for (int y = 0; y < N; y++)
                {
                    for (int x = 0; x < N; x++)
                    {
                        if (map[y, x] == 1)
                        {
                            LabelIsland(N, map, y, x, islandNum);
                            islandNum++;
                        }
                    }
                }

                // 2. 각 섬에서 최단 거리 찾기
                int minCost = int.MaxValue;
                for (int island = 2; island < islandNum; island++)
                {
                    int cost = FindShortestBridge(N, map, island);
                    minCost = Math.Min(minCost, cost);
                }

                writer.WriteLine(minCost);
            }
        }

        private void LabelIsland(int N, int[,] map, int startY, int startX, int label)
        {
            var queue = new Queue<(int y, int x)>();
            queue.Enqueue((startY, startX));
            map[startY, startX] = label;

            while (queue.Count > 0)
            {
                var current = queue.Dequeue();
                int y = current.y;
                int x = current.x;

                for (int d = 0; d < DIRECTIONS.GetLength(0); d++)
                {
                    int nextY = y + DIRECTIONS[d, 0];
                    int nextX = x + DIRECTIONS[d, 1];

                    if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N)
                        continue;

                    if (map[nextY, nextX] == 1)
                    {
                        map[nextY, nextX] = label;
                        queue.Enqueue((nextY, nextX));
                    }
                }
            }
        }

        private int FindShortestBridge(int N, int[,] map, int islandLabel)
        {
            var queue = new Queue<(int y, int x, int dist)>();
            bool[,] visited = new bool[N, N];

            // 섬의 테두리 찾기
            for (int y = 0; y < N; y++)
            {
                for (int x = 0; x < N; x++)
                {
                    if (map[y, x] == islandLabel)
                    {
                        queue.Enqueue((y, x, 0));
                        visited[y, x] = true;
                    }
                }
            }

            // BFS로 최단 거리 찾기
            while (queue.Count > 0)
            {
                var current = queue.Dequeue();
                int y = current.y;
                int x = current.x;
                int dist = current.dist;

                for (int d = 0; d < DIRECTIONS.GetLength(0); d++)
                {
                    int nextY = y + DIRECTIONS[d, 0];
                    int nextX = x + DIRECTIONS[d, 1];

                    if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N)
                        continue;

                    if (visited[nextY, nextX])
                        continue;

                    visited[nextY, nextX] = true;

                    if (map[nextY, nextX] == 0)
                    {
                        queue.Enqueue((nextY, nextX, dist + 1));
                    }
                    else if (map[nextY, nextX] != islandLabel)
                    {
                        return dist;
                    }
                }
            }

            return int.MaxValue;
        }
    }
}
