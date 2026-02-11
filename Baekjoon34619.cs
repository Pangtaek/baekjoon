using System;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon34619
    {
        public void Solve()
        {

            using (var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
            using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
            {
                int[] tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
                int a = tokens[0];  // 중대 수
                int b = tokens[1];  // 소대당 인원
                int n = tokens[2];  // 중대당 소대 수
                int k = tokens[3];  // k번째 병사 (1-based)

                // k를 0-based로 변환
                k--;

                // 1개 중대의 총 인원
                int peoplePerCompany = b * n;

                // 중대 번호 (1-based)
                int company = k / peoplePerCompany + 1;

                // 중대 내에서의 위치 (0-based)
                int positionInCompany = k % peoplePerCompany;

                // 소대 번호 (1-based)
                int platoon = positionInCompany / b + 1;

                writer.WriteLine(company + " " + platoon);
            }
        }
    }
}
