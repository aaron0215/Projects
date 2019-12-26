//
// Created by zhiyi on 3/14/2018.
//

#ifndef PROJECT5_ACCOUNTACTIVITY_H
#define PROJECT5_ACCOUNTACTIVITY_H

#include "SavingsAccount.h"
#include "ActivityType.h"

class AccountActivity : public SavingsAccount{
private:
    ActivityType type;
    double amount;
    void withdraw() override;
    void deposit() override;

public:
    void CalBalance() override;

    /**
    * Default Constructor
    * Requires: nothing
    * Modifies: withdraw, deposit
    * Effects: give variables zero
    */
    AccountActivity();

    /**
    * Non-Default Constructor
    * Requires: nothing
    * Modifies: withdraw, deposit
    * Effects: set variables given values
    */
    AccountActivity(double a);

    /**
     * Withdraw/deposit amount setter
     * Requires: nothing
     * Modifies: withdraw
     * Effects: set withdraw a given value
     */
    void setAmount(double a);

    void setType();


    /**
     * Withdram/deposit getter
     * Requires: nothing
     * Modifies: nothing
     * Effects: return amount
     */
    double getAmount() const;

    /**
     * Overloaded Operator (Binary)
     * friend of the class
     * Requires: nothing
     * Modifies: nothing
     * Effects: print the AccountActivity to the ostream
     */
    friend ostream& operator << (ostream& outs, const AccountActivity &aa);

};

#endif //PROJECT5_ACCOUNTACTIVITY_H
