//
// Created by zhiyi on 3/15/2018.
//

#include "AccountActivity.h"

AccountActivity::AccountActivity() {
    amount = 0;
}

AccountActivity::AccountActivity(double a){
    amount = a;
}

void AccountActivity::withdraw(){
    balance -= amount;
}

void AccountActivity::deposit() {
    balance += amount;
}

void AccountActivity::setType(){
    type.setType();
}

void AccountActivity::CalBalance(){
    if(type.getType() == "d"){
        deposit();
    }
    else{
        withdraw();
    }
}

void AccountActivity::setAmount(double a){
    amount = a;
}

double AccountActivity::getAmount() const{
    return amount;
}

ostream& operator << (ostream& outs, const AccountActivity &aa){
    outs << aa.balance << " " << aa.interestRate;
    return outs;
}