//
// Created by Aaron Zhang on 2/5/2018.
//
// This testing program only tests the functionality
// Input validation will be set up in main.cpp

#include "FeeCalculator.h"
#include <iostream>
using namespace std;

void test_constructor();

int main(){
    test_constructor();
    return 0;
};

void test_constructor(){

    // I don't test invalid input here
    // Main.cpp has input validation

    // Tests all constructors
    FeeCalculator f1;
    cout << f1.getHeight() << endl;

    FeeCalculator f2(20);
    cout << f2.getWeight() << endl;

    FeeCalculator f3(30,20);
    cout << f3.getLength() << f3.getWidth() << endl;

    FeeCalculator f4(30,10,20);
    cout << f4.getLength() << f4.getHeight() << f4.getWidth() << endl;

    FeeCalculator f5(30,10,20,2);
    cout << f5.getLength() << f5.getHeight() << f5.getWidth() << f5.getWeight() << endl;

    // Tests setters
    f5.setLength(40);
    f5.setHeight(20);
    f5.setWidth(30);
    f5.setWeight(3);

    // Tests getters
    cout << f5.getLength() << f5.getHeight() << f5.getWidth() << f5.getWeight() << endl;

    // Tests VolCalculator
    cout << f5.VolCalculator() << endl;

    // Tests ShowEstimation
    cout << "The estimated cost is: $" << f5.ShowEstimation() << endl;

    // Tests overloaded operators : *= and <<
    FeeCalculator a(40,20,30,3); //same as f5, easier to check
    a.CalCost();
    cout << "The estimated cost of a is: $" << a << endl;
    a += f5;
    cout << "The total estimated cost is: $" << a << endl;

}
