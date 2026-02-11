using System;
using System.Collections.Generic;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon34694
    {
        public void Solve()
        {
            using (var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
            using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
            {
                string[] firstLine = reader.ReadLine().Split();
                int a = int.Parse(firstLine[0]);
                int b = int.Parse(firstLine[1]);
                int w = int.Parse(firstLine[2]);
                int M = int.Parse(firstLine[3]);

                // 유연한 수업
                List<int> flexibleClasses = new List<int>();
                for (int i = 0; i < a; i++)
                {
                    flexibleClasses.Add(int.Parse(reader.ReadLine()));
                }

                // 각 요일의 고정 수업 중 최대 층만 저장
                List<int> maxHeights = new List<int>();
                for (int i = 0; i < w; i++)
                {
                    string[] line = reader.ReadLine().Split();
                    int maxHeight = 0;
                    for (int j = 0; j < b; j++)
                    {
                        int floor = int.Parse(line[j]);
                        maxHeight = Math.Max(maxHeight, floor);
                    }
                    maxHeights.Add(maxHeight);
                }

                // 유연한 수업을 내림차순 정렬
                flexibleClasses.Sort((x, y) => y.CompareTo(x));

                // 각 요일의 최대 층을 내림차순 정렬
                maxHeights.Sort((x, y) => y.CompareTo(x));

                long result = 0;
                for (int i = 0; i < w; i++)
                {
                    int maxHeight = maxHeights[i];

                    // i번째 요일에 배정될 유연한 수업의 인덱스
                    int flexIndex = (M - b) * i;
                    if (flexIndex < a)
                    {
                        maxHeight = Math.Max(maxHeight, flexibleClasses[flexIndex]);
                    }

                    result += 2L * maxHeight - 2;
                }

                writer.WriteLine(result);
            }
        }
    }
}
