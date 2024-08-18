#include <iostream>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int A, B, V;
    cin >> A >> B >> V;
    int day = 0;

    if ((V - A) % (A - B) == 0)
        day = (V - A) / (A - B);
    else
        day = (V - A) / (A - B) + 1;

    printf("%d\n", day + 1);
}