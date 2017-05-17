#include <iostream>
using namespace std;

int main() {
    int t;
    cin>>t;
    cin.ignore();
    while (t > 0) {
        long n;
        cin>>n;
        
        long max = 0;
        for (long i = 0 ; i < n ; i++) {
            int k;
            cin>>k;
            if (k > max) {
                max = k;
            }
        }
        
        cout<<(n-max);
        cout << "\n";
        t--;
    }
    return 0;
}
