//
// Created by zhiyi on 3/15/2018.
//

#include "ActivityType.h"
#include <iostream>

ActivityType::ActivityType(){
    t = deposit;
}

void ActivityType::setType(){
    cout << "Types of activity: \n"
         << "1. Withdraw\n"
         << "2. Deposit\n"
         << "3. Consume\n"
         << "4. Refund\n"
         << "5. Quit\n" << endl;
    cout << "Choose one type: ";
    while ((!(cin >> index))||(index > 5 || index < 1)){
        cout << "Please enter a valid integer between 1 and 5: ";
        cin.clear();
        cin.ignore(100,'\n');
    }
    if(index <= 4 && index >= 1){
        t = Type(index - 1);
    }

}

string ActivityType::getType() const {
    switch(t){
        case deposit: return "d";
        case refund: return "d";
        case withdraw: return "w";
        case consume: return "w";
    }
}
