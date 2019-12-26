//
// Created by zhiyi on 3/25/2018.
//

#ifndef PROJECT6_STUDENT_H
#define PROJECT6_STUDENT_H

#include <string>
#include <vector>
#include <iostream>
using namespace std;

void calculator();

class Student{
private:
    string firstName;
    string lastName;
    int yearOfClass;

protected:
    virtual void GradCalculator() = 0;

public:
    /**
    * Default Constructor
    * Requires: nothing
    * Modifies: firstName, lastName, yearOfAdmission
    * Effects: give variables null
    */
    Student();

    /**
    * Non-Default Constructor
    * Requires: nothing
    * Modifies: firstName, lastName, yearOfAdmission
    * Effects: set variables to be given ones
    */
    Student(string f, string l, int y);

    /**
    * Getters
    * Requires: nothing
    * Modifies: nothing
    * Effects: return the fields
    */
    string get_firstName() const;
    string get_lastName() const;
    int get_year() const;

    /**
    * Setters
    * Requires: nothing
    * Modifies: nothing
    * Effects: set the fields
    */
    void set_firstName(string f);
    void set_lastName(string l);
    void set_year(int y);

    /**
     * Overloaded Operator (Binary)
     * friend of the class
     * Requires: nothing
     * Modifies: nothing
     * Effects: print the Student to the ostream
     */
    friend ostream& operator << (ostream& outs, const Student &s);
};

class CEMSStudent : public Student{
private:
    int math_credits, major_credits, elective_credits;
    void GradCalculator() override;

public:
    /**
    * Default Constructor
    * Requires: nothing
    * Modifies: fields
    * Effects: give fields zero
    */
    CEMSStudent();

    /**
    * Non-Default Constructor
    * Requires: nothing
    * Modifies: fields
    * Effects: set fields given values
    */
    CEMSStudent(int math, int major, int elect);

    /**
    * Getters
    * Requires: nothing
    * Modifies: nothing
    * Effects: return the fields
    */
    int get_math() const;
    int get_major() const;
    int get_elect() const;

    /**
    * Setters
    * Requires: nothing
    * Modifies: nothing
    * Effects: set the fields
    */
    void set_math(int math);
    void set_major(int major);
    void set_elect(int elect);

};

class CASStudent : public Student{
private:
    int math_credits, major_credits, elective_credits, minor_credits;
    void GradCalculator() override;

public:
    /**
    * Default Constructor
    * Requires: nothing
    * Modifies: fields
    * Effects: give fields zero
    */
    CASStudent();

    /**
    * Non-Default Constructor
    * Requires: nothing
    * Modifies: fields
    * Effects: set fields given values
    */
    CASStudent(int math, int major, int elect, int minor);

    /**
    * Getters
    * Requires: nothing
    * Modifies: nothing
    * Effects: return the fields
    */
    int get_math() const;
    int get_major() const;
    int get_elect() const;
    int get_minor() const;

    /**
    * Setters
    * Requires: nothing
    * Modifies: nothing
    * Effects: set the fields
    */
    void set_math(int math);
    void set_major(int major);
    void set_elect(int elect);
    void set_minor(int minor);

};

class ObjectVector{
private:
    vector<CEMSStudent> cemsVector;
    vector<CASStudent> casVector;

public:
    /**
    * Default Constructor
    * Requires: nothing
    * Modifies: nothing
    * Effects: nothing
    */
    ObjectVector();

    void addToVector(CEMSStudent cems);
    void addToVector(CASStudent cas);

    void removeFromVector(CEMSStudent cems);
    void removeFromVector(CASStudent cas);

    void reorderVector();

};

#endif //PROJECT6_STUDENT_H
