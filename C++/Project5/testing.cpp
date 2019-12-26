//
// Created by zhiyi on 3/15/2018.
//

#include <iostream>
#include "Person.h"
using namespace std;

int main() {
    std::cout << "Hello, World!" << std::endl;
    Person p;
    p.setFirst("Aaron");
    p.setLast("Zhang");
    AccountActivity a(100);
    a.setBalance(1000);
    p.setAccount(a);
    cout << p << endl;
    a.setType();
    a.CalBalance();
    p.setAccount(a);
    cout << p << endl;
    return 0;
}