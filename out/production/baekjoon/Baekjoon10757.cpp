
#include <iostream>
#include <algorithm>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    string a, b;
    cin >> a >> b;
    // a가 무조건 큰 수로 설정
    if (a.length() < b.length())
        swap(a, b);

    // 자리가 다르다면 0을 추가
    string tmp = "";
    if (a.length() != b.length())
        for (int i = 0; i < (a.length() - b.length()); i++)
            tmp += "0";
    b = (tmp + b);

    string ans;
    bool up = false;
    for (int i = a.length() - 1; i >= 0; i--)
    {
        int x = a[i] - '0';
        int y = b[i] - '0';
        int sum = x + y;
        if (up)
        {
            sum++;
            up = false;
        }
        if (sum >= 10)
        {
            up = true;
            sum = sum % 10;
        }
        ans += sum + '0';
    }

    if (up)
        ans += "1";
    for (int i = ans.length() - 1; i >= 0; i--)
        cout << ans[i];
}
