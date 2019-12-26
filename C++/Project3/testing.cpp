//
// Created by Aaron Zhang on 2/12/18.
//

#include <iostream>
#include <fstream>
#include <sstream>
#include "College.h"

using namespace std;

int main(){
    College TestC;
    vector <StudentInfo> TestVector = TestC.GetVector();
    cout << "This the original data: \n" << TestVector[0] << endl;
};