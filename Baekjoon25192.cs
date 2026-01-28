using System;
using System.Collections.Generic;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon25192
    {
        public void Solve()
        {
            var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int N = int.Parse(reader.ReadLine());
            int result = 0;
            var nameSet = new HashSet<string>();

            for (int i = 0; i < N; i++)
            {
                var name = reader.ReadLine();

                if (name.Equals("ENTER"))
                {
                    result += nameSet.Count;
                    nameSet.Clear();
                }
                else if (nameSet.Add(name)) { }
            }

            result += nameSet.Count;
            writer.Write(result);

            writer.Close();
            reader.Close();
        }
    }
}
