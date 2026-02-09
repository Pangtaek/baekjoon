using System;
using System.Collections.Generic;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon34691
    {
        public void Solve()
        {
            using (var reader = new StreamReader(new BufferedStream(Console.OpenStandardInput())))
            using (var writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput())))
            {
                var djshsDictionary = new Dictionary<string, string>();
                djshsDictionary.Add("animal", "Panthera tigris");
                djshsDictionary.Add("tree", "Pinus densiflora");
                djshsDictionary.Add("flower", "Forsythia koreana");

                while (true)
                {
                    string key = reader.ReadLine();

                    if (key == "end") break;

                    writer.WriteLine(djshsDictionary[key]);
                }
            }
        }
    }
}
