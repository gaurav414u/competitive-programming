//
//  main.cpp
//  Testcpp
//
//  Created by Gaurav Bhola on 16/05/17.
//
//
#include <iostream>

using namespace std;

int main(int argc, const char * argv[]) {
    long t;
    cin>>t;
    
    while (t--) {
        long n, m;
        cin>>n>>m;
        if (m%2 == 0 || n%2 == 0) {
            cout<<"Bob"<<endl;
        } else {
            if (m == 1 && n >= 3) {
                cout<<"Bob"<<endl;
            } else {
                cout<<"Alice"<<endl;
            }
        }
    }
    return 0;
}
