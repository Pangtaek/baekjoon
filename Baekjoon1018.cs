using System;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon1018
    {
        public void Solve()
        {
            using (var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
            using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
            {
                int[] tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
                int N = tokens[0];
                int M = tokens[1];
                char[,] board = new char[N, M];

                for (int y = 0; y < N; y++)
                {
                    string line = reader.ReadLine();
                    for (int x = 0; x < M; x++)
                    {
                        board[y, x] = line[x];
                    }
                }

                int minCount = 64; // 최악의 경우

                for (int y = 0; y <= N - 8; y++)
                {
                    for (int x = 0; x <= M - 8; x++)
                    {
                        // W로 시작하는 패턴
                        int whiteStart = CountRepaint(board, y, x, 'W');

                        // B로 시작하는 패턴
                        int blackStart = CountRepaint(board, y, x, 'B');

                        // 둘 중 최소값
                        minCount = Math.Min(minCount, Math.Min(whiteStart, blackStart));
                    }
                }

                writer.WriteLine(minCount);
            }
        }

        private int CountRepaint(char[,] board, int startY, int startX, char startColor)
        {
            int count = 0;

            for (int y = 0; y < 8; y++)
            {
                for (int x = 0; x < 8; x++)
                {
                    // 체스판 패턴: (y + x) % 2 == 0이면 시작 색과 동일
                    char expectedColor;
                    if (((y + x) & 1) != 0)
                    {
                        expectedColor = startColor;
                    }
                    else
                    {
                        expectedColor = startColor == 'W' ? 'B' : 'W';
                    }

                    // 현재 칸의 색상이 예상과 다르면 카운트 증가
                    if (board[startY + y, startX + x] != expectedColor)
                    {
                        count++;
                    }
                }
            }

            return count;
        }
    }
}
