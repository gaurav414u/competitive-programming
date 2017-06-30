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
	 int t;
    cin>>t;
    cin.ignore();
    while (t > 0) {
        long long int n;
        long long int sum = 0;
        long long int thisSum = 0;
        long long int count = 0;
        
        cin>>n;
        for (long long int i = 0; i < n ; i++) {
            long long int a;
            cin>>a;
            
            if (a > 0) {
                //cout<<"positive"<<a<<endl;
                count++;
                thisSum += a;
                //cout<<"sum="<<sum<<endl;
            } else {
                //cout<<"negative"<<a<<endl;
                
                if ((count + 1) * (thisSum + a) >= count * thisSum + a) {
                    count++;
                    thisSum += a;
                } else {
                    sum += count * thisSum + a;
                    count = 0;
                    thisSum = 0;
                }
                //cout<<"count="<<count<<endl;
                //cout<<"thisSum="<<thisSum<<endl;
                //cout<<"sum="<<sum<<endl;
            }
            
        }
        if (count != 0) {
            sum += thisSum * count;
        }
        cout<<sum;
        
        
        cout<<endl;
        t--;
	}
	return 0;
}
