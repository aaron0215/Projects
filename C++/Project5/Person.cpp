//
// Created by zhiyi on 3/15/2018.
//

#include "Person.h"

Person::Person(){
    firstName = "null";
    lastName = "null";
    balance = 0;
    interestRate = 0.25;
}

Person::Person(string f, string l){
    firstName = f;
    lastName = l;
    balance = 0;
    interestRate = 0.25;
}

void Person::setFirst(string f) {
    firstName = f;
}

void Person::setLast(string l) {
    lastName = l;
}

void Person::setAccount(AccountActivity &aa){
    account = aa;
}

string Person::getFirst() const {
    return firstName;
}

string Person::getLast() const {
    return lastName;
}

ostream& operator << (ostream& outs, const Person &p){
    outs << p.firstName << " "
         << p.lastName << " "
         << p.account << endl;
}