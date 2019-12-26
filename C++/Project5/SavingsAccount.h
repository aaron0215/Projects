//
// Created by zhiyi on 3/14/2018.
//

#ifndef PROJECT5_SAVINGSACCOUNT_H
#define PROJECT5_SAVINGSACCOUNT_H

#include <iostream>

using namespace std;

class SavingsAccount{
protected:
    double balance, interestRate;
    virtual void withdraw() = 0;
    virtual void deposit() = 0;
public:
    virtual void CalBalance() = 0;

    /**
     * Default Constructor
     * Requires: nothing
     * Modifies: person, balance, interestRate
     * Effects: give variables default values
     */
    SavingsAccount();

    /**
     * Non-Default Constructor
     * Requires: nothing
     * Modifies: person, balance, interestRate
     * Effects: set variable given values
     */
    SavingsAccount(double b, double ir);

    /**
     * Balance setter
     * Requires: Nothing
     * Modifies: balance
     * Effects: set balance a given value
     */
    void setBalance(double b);

    /**
     * InterestRate setter
     * Requires: nothing
     * Modifies: interestRate
     * Effects: set interestRate a given value
     */
    void setInterestRate(double ir);

    /**
     * Balance getter
     * Requires: nothing
     * Modifies: nothing
     * Effects: return balance
     */
    double getBalance() const;

    /**
     * InterestRate getter
     * Requires: nothing
     * Modifies: nothing
     * Effects: return interestRate
     */
    double getInterestRate() const;

    /**
     * Overloaded Operator (Unary)
     * Member of the class
     * Requires: nothing
     * Modifies: nothing
     * Effects: add balances of different objects
     */
    SavingsAccount& operator += (const SavingsAccount &s);

    /**
     * Overloaded Operator (Binary)
     * friend of the class
     * Requires: nothing
     * Modifies: nothing
     * Effects: print the SavingsAccount to the ostream
     */
    friend ostream& operator << (ostream& outs, const SavingsAccount &s);
};

#endif //PROJECT5_SAVINGSACCOUNT_H
