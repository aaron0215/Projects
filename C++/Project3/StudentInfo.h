//
// Created by Aaron Zhang on 2/11/2018.
//

#ifndef PROJECT3_STUDENTINFO_H
#define PROJECT3_STUDENTINFO_H

#include<iomanip>
#include<iostream>
#include<vector>
#include <string>
using namespace std;

class StudentInfo{
private:
    string name,standing,major,status,grade;

public:
    /**
     * Default Constructor
     * Requires: nothing
     * Modifies: nothing
     * Effects: nothing
     */
    StudentInfo();

    /**
     * Default Constructor
     * Requires: nothing
     * Modifies: name, standing, major, status, gpa
     * Effects: set given values to each variable
     */
    StudentInfo(string n, string sd, string m, string g, string su);

    /**
     * Destructor
     * Requires: nothing
     * Modifies: nothing
     * Effects: does nothing
     */
    ~StudentInfo();

    /**
     * name setter
     * Requires: nothing
     * Modifies: name
     * Effects: sets the name to the given value
     * @param h
     */
    void setName(string n);

    /**
     * standing setter
     * Requires: nothing
     * Modifies: standing
     * Effects: sets the standing to the given value
     * @param sd
     */
    void setStanding(string sd);

    /**
     * major setter
     * Requires: nothing
     * Modifies: major
     * Effects: sets the major to the given value
     * @param m
     */
    void setMajor(string m);

    /**
     * status setter
     * Requires: nothing
     * Modifies: status
     * Effects: sets the status to the given value
     * @param su
     */
    void setStatus(string su);

    /**
    * gpa setter
    * Requires: nothing
    * Modifies: gpa
    * Effects: sets the gpa to the given value
    * @param g
    */
    void setGPA(string g);

    /**
     * GPA getter
     * Requires: nothing
     * Modifies: nothing
     * Effects: return the GPA
     */
    string getGPA() const;

    /**
     * name getter
     * Requires: nothing
     * Modifies: nothing
     * Effects: return the name
     */
    string getName() const;

    /**
    * standing getter
    * Requires: nothing
    * Modifies: nothing
    * Effects: return the standing
    */
    string getStanding() const;

    /**
    * major getter
    * Requires: nothing
    * Modifies: nothing
    * Effects: return the major
    */
    string getMajor() const;

    /**
    * status getter
    * Requires: nothing
    * Modifies: nothing
    * Effects: return the status
    */
    string getStatus() const;

    /**
     * Overloadede Operator (Binary)
     * Friend of class
     * Requires: nothing
     * Modifies: nothing
     * Effects: prints the StudentInfo to the ostream
     */
    friend ostream& operator << (ostream& outs, const StudentInfo &s);
};

#endif //PROJECT3_STUDENTINFO_H
