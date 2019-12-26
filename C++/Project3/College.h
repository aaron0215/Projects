//
// Created by Aaron Zhang on 2/11/2018.
//

#ifndef PROJECT3_COLLEGE_H
#define PROJECT3_COLLEGE_H

#include "StudentInfo.h"

class College{
private:
    vector <StudentInfo>  Students;

public:
    /**
     * Default Constructor
     * Requires: nothing
     * Modifies: nothing
     * Effects: Generate a vector of students information
     */
    College();

//    /**
//     * Constructor
//     * Requires: nothing
//     * Modifies: nothing
//     * Effects: add objects to Students
//     */
//    College(StudentInfo sf);

    /**
    * Students getter
    * Requires: nothing
    * Modifies: nothing
    * Effects: return the Students
    */
    vector<StudentInfo> GetVector() const;

};

#endif //PROJECT3_COLLEGE_H
