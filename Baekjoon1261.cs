using System;
using System.Collections.Generic;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon1261
    {
        public void Solve()
        {
            StreamReader reader = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            StreamWriter writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int[] tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
            int M = tokens[0]; // width
            int N = tokens[1]; // height
            int[,] maze = new int[N, M];

            for (int height = 0; height < N; height++)
            {
                string data = reader.ReadLine();
                for (int width = 0; width < M; width++)
                {
                    maze[height, width] = data[width] - '0'; // char -> int
                }
            }

            int result = Bfs(maze, 0, 0);
            writer.Write(result);

            reader.Close();
            writer.Close();
        }

        private int Bfs(int[,] maze, int startX, int startY)
        {
            int[,] directions = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
            bool[,] visited = new bool[maze.GetLength(0), maze.GetLength(1)];
            var deque = new LinkedList<(int x, int y, int cost)>();

            visited[startY, startX] = true;
            deque.AddLast((startX, startY, 0));

            while (deque.Count > 0)
            {
                var current = deque.First.Value;
                deque.RemoveFirst();
                int x = current.x;
                int y = current.y;

                // 출구 도착
                if (x == maze.GetLength(1) - 1 && y == maze.GetLength(0) - 1)
                {
                    return current.cost;
                }

                for (int d = 0; d < directions.GetLength(0); d++)
                {
                    int nx = x + directions[d, 0];
                    int ny = y + directions[d, 1];

                    // 미로 범위 확인
                    if (nx < 0 || nx >= maze.GetLength(1) || ny < 0 || ny >= maze.GetLength(0))
                    {
                        continue;
                    }

                    // 방문 여부 확인
                    if (visited[ny, nx])
                    {
                        continue;
                    }

                    // 이동 우선순위 선정 및 이동
                    visited[ny, nx] = true;
                    if (maze[ny, nx] == 0) // 빈칸
                    {
                        deque.AddFirst((nx, ny, current.cost));
                    }
                    else // 벽돌
                    {
                        deque.AddLast((nx, ny, current.cost + 1));
                    }
                }
            }

            return -1; // 도착하지 못한 경우
        }
    }
}
