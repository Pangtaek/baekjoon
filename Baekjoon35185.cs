using System;
using System.IO;
using System.Collections.Generic;

namespace Baekjoon
{
    internal class Baekjoon35185
    {
        public void Solve()
        {
            using (var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
            using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
            {
                int[] tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
                int N = tokens[0];
                int L = tokens[1];
                string commands = reader.ReadLine();

                int currentPos = 0;
                int minPos = 0;
                int maxPos = 0;

                // 명령 실행 전후 모든 위치 (마지막 명령 직전까지)
                HashSet<int> intermediatePositions = new HashSet<int>();

                for (int i = 0; i < L; i++)
                {
                    // 명령 실행 전 위치 저장
                    intermediatePositions.Add(currentPos);

                    if (commands[i] == 'L') currentPos--;
                    else if (commands[i] == 'R') currentPos++;

                    minPos = Math.Min(minPos, currentPos);
                    maxPos = Math.Max(maxPos, currentPos);
                }

                int finalOffset = currentPos;

                // 유효한 시작 위치 B 범위 계산
                int minStart = Math.Max(0, -minPos);
                int maxStart = Math.Min(N - 1, N - 1 - maxPos);

                if (minStart > maxStart)
                {
                    writer.WriteLine("DEFEAT");
                    return;
                }
                else
                {
                    // 유효한 B에서 조건 만족하는 (B, F) 찾기
                    for (int B = minStart; B <= maxStart; B++)
                    {
                        int F = B + finalOffset;

                        if (F >= 0 && F < N && B != F)
                        {
                            int relativeF = F - B;

                            // 중간 경로에 F가 없으면 성공
                            if (!intermediatePositions.Contains(relativeF))
                            {
                                writer.WriteLine("WIN");
                                writer.WriteLine($"{B + 1} {F + 1}");
                                return;
                            }
                        }
                    }
                }

                writer.WriteLine("DEFEAT");
            }
        }
    }
}
