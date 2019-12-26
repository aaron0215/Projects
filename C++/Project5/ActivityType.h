//
// Created by zhiyi on 3/14/2018.
//

#ifndef PROJECT5_ACTIVITY_H
#define PROJECT5_ACTIVITY_H

#include <string>
using namespace std;

class ActivityType{
private:
    enum Type{withdraw,deposit,consume,refund};
    int index;

public:
    Type t;

    /**
    * Default Constructor
    * Requires: nothing
    * Modifies: t
    * Effects: set t as deposit
    */
    ActivityType();

    /**
     * Type setter
     * Requires: nothing
     * Modifies: t
     * Effects: set t as user input
     */
    void setType();

    string getType() const;
};
#endif //PROJECT5_ACTIVITY_H
