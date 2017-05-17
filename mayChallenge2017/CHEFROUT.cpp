//
//  main.cpp
//  Testcpp
//
//  Created by Gaurav Bhola on 16/05/17.
//
//

#include <iostream>
using namespace std;

int priority(char t) {
	if (t == 'C') {
		return 3;
	}
	if (t == 'E') {
		return 2;
	}
	if (t == 'S') {
		return 1;
	}
	return 0;
}

int main(int argc, const char * argv[]) {
	// insert code here...
	long t;
	cin>>t;
	cin.ignore();
	while (t > 0) {
		string hel;
		getline(cin, hel);

		int i=0;
		int l = hel.length();
		char prev = 'C';
		do {
			if (priority(prev) < priority(hel[i])) {
				cout << "no";
				break;
			}
			prev = hel[i];
			i++;
		} while(i < l);

		if (i == l) {
			cout << "yes";
		}

		cout << "\n";
		t--;
	}
	return 0;
}
