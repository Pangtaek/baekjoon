
// 25591
#include <iostream>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int x, y;
    cin >> x >> y;
    int a = 100 - x;
    int b = 100 - y;
    int c = 100 - (a + b);
    int d = a * b;
    int q = d / 100;
    int 뭐지 = (d / 100) + c;
    int r = d % 100;

    cout << a << ' ' << b << ' ' << c << ' ' << d << ' ' << q << ' ' << r << '\n';
    cout << 뭐지 << ' ' << r;
}
