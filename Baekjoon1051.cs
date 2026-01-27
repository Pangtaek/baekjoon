using System;
using System.IO;

namespace Baekjoon
{
    internal class Baekjoon1051
    {
        internal void Solve()
        {
            StreamReader reader = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            StreamWriter writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int[] tokens = Array.ConvertAll(reader.ReadLine().Split(), int.Parse);
            int N = tokens[0];
            int M = tokens[1];
            int[,] matrix = new int[N, M];

            for (int row = 0; row < N; row++)
            {
                string data = reader.ReadLine();
                for (int column = 0; column < M; column++)
                {
                    matrix[row, column] = data[column] - '0'; // char -> int
                }
            }

            int result = 1;
            bool isFound = false;
            for (int length = Math.Min(N, M); length >= 2 && !isFound; length--)
            {
                for (int row = 0; row <= N - length && !isFound; row++)
                    for (int column = 0; column <= M - length && !isFound; column++)
                    {
                        int target = matrix[row, column];
                        if (matrix[row, column + length - 1] == target
                         && matrix[row + length - 1, column] == target
                         && matrix[row + length - 1, column + length - 1] == target)
                        {
                            result = length * length;
                            isFound = true;
                        }
                    }
            }

            writer.WriteLine(result);

            writer.Close();
            reader.Close();
        }
    }
}
