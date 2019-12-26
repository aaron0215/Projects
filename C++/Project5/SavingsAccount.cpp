//
// Created by zhiyi on 3/15/2018.
//

#include "SavingsAccount.h"

SavingsAccount::SavingsAccount(){
    balance = 0;
    interestRate = 0.25;
}

SavingsAccount::SavingsAccount(double b, double ir) {
    balance = b;
    interestRate = ir;
}

void SavingsAccount::setBalance(double b){
    balance = b;
}

void SavingsAccount::setInterestRate(double ir) {
    interestRate = ir;
}

double SavingsAccount::getBalance() const {
    return balance;
}

double SavingsAccount::getInterestRate() const {
    return interestRate;
}

SavingsAccount& SavingsAccount::operator += (const SavingsAccount &s){
    balance += s.balance;
    return *this;
}

ostream& operator << (ostream& outs, const SavingsAccount &s){
    outs << s.balance << s.interestRate;
    return outs;
}