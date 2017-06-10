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
    int limit = 100;
    int a[500 + 1] = {0};
    int ans[100+1] = {0};
    
    for (int i = 1, k = 0 ; k < limit ; i++) {
        if (a[i] != 1) {
            ans[k++] = i;
            
            for (int l = 0 ; l < k-1 ; l++) {
                if (ans[l] + i < 500) {
                    a[ans[l] + i] = 1;
                }
            }
        }
    }
	long t;
    cin>>t;
    cin.ignore();
    while (t > 0) {
        int n;
        cin>>n;
        if (n == 1) {
            cout<<3<<" ";
        } else if (n == 2) {
            cout<<4<<" "<<5<<" ";
        } else {
            for (int i = 0; i < n ; i++) {
                cout<<ans[i]<<" ";
            }       
        }
        cout<<"\n";
		t--;
	}
	return 0;
}
