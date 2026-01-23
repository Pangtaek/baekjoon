using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace Baekjoon
{
    internal class Baekjoon1021
    {

        public void Solve()
        {
            StreamReader reader = new StreamReader(new BufferedStream(Console.OpenStandardInput()));
            StreamWriter writer = new StreamWriter(new BufferedStream(Console.OpenStandardOutput()));

            int[] tokens = Array.ConvertAll(reader.ReadLine().Split(' '), element => int.Parse(element));
            int N = tokens[0];
            int M = tokens[1];
            int[] positions = Array.ConvertAll(reader.ReadLine().Split(' '), element => int.Parse(element));
            int count = 0;

            LinkedList<int> deque = new LinkedList<int>();

            for (int i = 1; i <= N; i++)
            {
                deque.AddLast(i);
            }

            for (int i = 0; i < positions.Length; i++)
            {
                int target = positions[i];
                int index = IndexOf(deque, target);

                if (index == 0)
                {
                    Method1(deque);
                    continue;
                }

                bool isLocatedFront = index <= deque.Count / 2;
                if (isLocatedFront)
                {
                    for (int j = 0; j < index; j++)
                    {
                        count++;
                        Method2(deque);
                    }
                    Method1(deque);
                }
                else
                {
                    for (int j = deque.Count; j > index; j--)
                    {
                        count++;
                        Method3(deque);
                    }
                    Method1(deque);
                }
            }

            writer.WriteLine(count);
            writer.Close();
        }

        private void Method1<T>(LinkedList<T> deque)
        {
            deque.RemoveFirst();
        }

        private void Method2<T>(LinkedList<T> deque)
        {
            var element = deque.First;
            deque.RemoveFirst();
            deque.AddLast(element);
        }

        private void Method3<T>(LinkedList<T> deque)
        {
            var element = deque.Last;
            deque.RemoveLast();
            deque.AddFirst(element);
        }

        /// <summary>
        /// Deque의 원소들 중 대상에 해당하는 값의 인덱스를 반환하는 메서드입니다. 
        /// </summary>
        /// <typeparam name="T">제네릭</typeparam>
        /// <param name="deque">찾으려는 값을 가지고 있는 덱</param>
        /// <param name="target">찾으려는 값</param>
        /// <returns>target의 인덱스, 찾지 못하면 -1</returns>
        private int IndexOf<T>(LinkedList<T> deque, T target)
        {
            var array = deque.ToArray();

            for (int i = 0; i < array.Length; i++)
            {
                if (array[i].Equals(target))
                {
                    return i;
                }
            }

            return -1;
        }
    }
}
