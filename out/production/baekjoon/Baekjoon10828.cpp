
#include <iostream>
#include <stack>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N;
    cin >> N;
    stack<int> st;
    string str;

    for (int i = 0; i < N; i++)
    {
        cin >> str;

        if (str == "push")
        {
            int num;
            cin >> num;
            st.push(num);
        }
        else if (str == "pop")
        {
            if (!st.empty())
            {
                cout << st.top() << '\n';
                st.pop();
            }
            else
                cout << -1 << '\n';
        }
        else if (str == "size")
        {
            cout << st.size() << '\n';
        }
        else if (str == "empty")
        {
            if (st.empty())
                cout << 1 << '\n';
            else
                cout << 0 << '\n';
        }
        else if (str == "top")
        {
            if (!st.empty())
                cout << st.top() << endl;
            else
                cout << -1 << '\n';
        }
    }
}
