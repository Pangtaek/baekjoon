using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace Baekjoon
{
    internal class Baekjoon1034
    {
        public void Solve()
        {
            using (var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
            using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
            {
                int[] tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
                int N = tokens[0];
                int M = tokens[1];
                int[,] matrix = new int[N, M];

                for (int row = 0; row < N; row++)
                {
                    string line = reader.ReadLine();
                    for (int column = 0; column < M; column++)
                    {
                        matrix[row, column] = line[column] - '0';
                    }
                }
                
                int K = int.Parse(reader.ReadLine());

                // 각 행의 패턴을 문자열로 변환하고 그룹화
                Dictionary<string, int> patternCount = new Dictionary<string, int>();

                for (int row = 0; row < N; row++)
                {
                    StringBuilder pattern = new StringBuilder();
                    for (int column = 0; column < M; column++)
                    {
                        pattern.Append(matrix[row, column]);
                    }
                    string patternStr = pattern.ToString();

                    if (!patternCount.ContainsKey(patternStr))
                    {
                        patternCount[patternStr] = 0;
                    }
                    patternCount[patternStr]++;
                }

                int maxRows = 0;

                // 각 패턴에 대해 검사
                foreach (var entry in patternCount)
                {
                    string pattern = entry.Key;
                    int count = entry.Value;

                    // 해당 패턴에서 0의 개수 세기
                    int zeroCount = pattern.Count(c => c == '0');

                    // 조건 검사: 0의 개수 <= K이고, 홀짝이 일치해야 함
                    if (zeroCount <= K && (zeroCount % 2 == K % 2))
                    {
                        maxRows = Math.Max(maxRows, count);
                    }
                }

                writer.WriteLine(maxRows);
            }
        }
    }
}
