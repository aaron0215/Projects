//
// Created by zhiyi on 3/14/2018.
//

#ifndef PROJECT5_PERSON_H
#define PROJECT5_PERSON_H

#include <string>
#include <iostream>
#include "AccountActivity.h"

using namespace std;

class Person{
private:
    string firstName, lastName;
    double balance, interestRate;
    AccountActivity account;
public:
    /**
    * Default Constructor
    * Requires: nothing
    * Modifies: firstName, lastName
    * Effects: set variables null
    */
    Person();

    /**
    * Non-Default Constructor
    * Requires: nothing
    * Modifies: firstName, lastName
    * Effects: set variables given values
    */
    Person(string f, string l);

    /**
     * FirstName setter
     * Requires: nothing
     * Modifies: firstName
     * Effects: set firstName a given value
     */
    void setFirst(string f);

    /**
     * LastName setter
     * Requires: nothing
     * Modifies: lastName
     * Effects: set lastName a given value
     */
    void setLast(string l);

    /**
     * Account setter
     * Requires: nothing
     * Modifies: account
     * Effects: set account an object
     */
    void setAccount(AccountActivity &aa);

    /**
     * FirstName getter
     * Requires: nothing
     * Modifies: nothing
     * Effects: return firstName
     */
    string getFirst() const;

    /**
     * LastName getter
     * Requires: nothing
     * Modifies: nothing
     * Effects: return lastName
     */
    string getLast() const;

    /**
     * Overloaded Operator (Binary)
     * friend of the class
     * Requires: nothing
     * Modifies: nothing
     * Effects: print the Person to the ostream
     */
    friend ostream& operator << (ostream& outs, const Person &p);
};

#endif //PROJECT5_PERSON_H
