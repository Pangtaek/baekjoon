using System;
using System.Collections.Generic;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon16920
    {
        private int N, M, P;
        private int[] S;
        private char[,] matrix;
        private int[] scores;

        private static readonly int[] dx = { 0, 0, -1, 1 };
        private static readonly int[] dy = { -1, 1, 0, 0 };

        public void Solve()
        {
            StreamReader reader = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            StreamWriter writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int[] tokens = Array.ConvertAll(reader.ReadLine().Split(' '), int.Parse);
            N = tokens[0];
            M = tokens[1];
            P = tokens[2];

            S = Array.ConvertAll(reader.ReadLine().Split(' '), int.Parse);
            matrix = new char[N, M];
            scores = new int[P + 1];

            List<int[]>[] startPoint = new List<int[]>[P + 1];
            for (int p = 1; p <= P; p++)
            {
                startPoint[p] = new List<int[]>();
            }

            for (int row = 0; row < N; row++)
            {
                string data = reader.ReadLine();
                for (int column = 0; column < M; column++)
                {
                    char value = data[column];
                    matrix[row, column] = value;

                    int playerId = value - '0';
                    if (1 <= playerId && playerId <= 9)
                    {
                        startPoint[playerId].Add(new int[] { column, row }); // (x, y)
                        scores[playerId]++; // 성 개수만큼 점수 증가
                    }
                }
            }

            Bfs(startPoint);

            // 점수 출력
            for (int p = 1; p <= P; p++)
            {
                writer.Write(scores[p] + " ");
            }
            writer.WriteLine();

            writer.Close();
        }

        private void Bfs(List<int[]>[] startPoint)
        {
            Queue<(int x, int y)>[] queues = new Queue<(int x, int y)>[P + 1];
            for (int p = 1; p <= P; p++)
                queues[p] = new Queue<(int x, int y)>();

            // 초기 성들 큐에 넣기
            for (int p = 1; p <= P; p++)
            {
                foreach (var castle in startPoint[p])
                {
                    queues[p].Enqueue((castle[0], castle[1]));
                }
            }

            while (true)
            {
                bool anyExpand = false;

                // 각 플레이어 순서대로 턴 진행
                for (int p = 1; p <= P; p++)
                {
                    int speed = S[p - 1];
                    if (queues[p].Count == 0)
                        continue;

                    // S[p] 레벨까지만 확장 (턴당 최대 이동 횟수)
                    for (int step = 0; step < speed; step++)
                    {
                        int qSize = queues[p].Count;
                        if (qSize == 0)
                        {
                            break;
                        }

                            // 현재 레벨 큐 크기만큼만 처리
                            for (int i = 0; i < qSize; i++)
                        {
                            var current = queues[p].Dequeue();
                            int x = current.x;
                            int y = current.y;

                            // 4방향으로 확장
                            for (int d = 0; d < 4; d++)
                            {
                                int nx = x + dx[d];
                                int ny = y + dy[d];

                                if (!IsInBound(nx, ny))
                                {
                                    continue;
                                }

                                if (matrix[ny, nx] != '.')
                                {
                                    continue;
                                }
                                // 점령
                                matrix[ny, nx] = (char)(p + '0');
                                scores[p]++;
                                queues[p].Enqueue((nx, ny));
                                anyExpand = true;
                            }
                        }
                    }
                }

                if (!anyExpand)
                {
                    break; // 더 이상 확장할 플레이어가 없음
                }
            }
        }

        private bool IsInBound(int x, int y)
        {
            return 0 <= x && x < M && 0 <= y && y < N;
        }
    }
}
