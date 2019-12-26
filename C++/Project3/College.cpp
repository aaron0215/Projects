//
// Created by Aaron Zhang on 2/11/2018.
//

#include "College.h"
#include <fstream>
//College contains lots of students information, including name, major, standing, and overall GPA
//It can search if a student is active, add new student, change information and deactivate any student

//Can't find easier way to check if file exists
bool checkFile(string fn){
    ifstream inFile(fn);
    if(inFile){
        return true;
    }
    else{
        return false;
    }
}

College::College(){
    string filename,name,standing,major,status,junk,grade;
    cout << "Enter the name of file to import data: ";
    cin >> filename;
    while (!checkFile(filename)) {
        cout << "File: " << filename << " does not exist, please check the name" << endl;
        cout << "Enter the name of file to import data: ";
        cin.clear();
        cin.ignore(100,'\n');
        cin >> filename;
    }

    ifstream inFile(filename);

    while (getline(inFile, name)) {
        getline(inFile, standing);
        getline(inFile, major);
        getline(inFile, grade);
        getline(inFile, status);
        Students.emplace_back(StudentInfo(name, standing, major, grade, status));
    }
    inFile.close();
}

vector<StudentInfo> College::GetVector() const{
    return Students;
}

