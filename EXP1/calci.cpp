#include <bits/stdc++.h>
using namespace std;
int main() {
    
    char op;
    double first, second;
    while (true)
        { cout<< "\nEnter an operator (+, -, *, /): ";
        cin>> op;
        switch (op) {
            case '+':
                cout<<"Enter two operands: ";
                cin>>first>>second;
                cout<<first<<"+"<<second<<" = "<<first + second;
                break;
            case '-':
                cout<<"Enter two operands: ";
                cin>>first>>second;
                cout<<first<<"-"<<second<<" = "<<first - second;
                break;
            case '*':
                cout<<"Enter two operands: ";
                cin>>first>>second;
                cout<<first<<"*"<<second<<" = "<<first * second;
                break;
            case '/':
                cout<<"Enter two operands: ";
                cin>>first>>second;
                cout<<first<<"/"<<second<<" = "<<first / second;
                break;
            default:
                cout<<"Error! operator is not correct";
        }}
  return 0;
}
