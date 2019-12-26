//
// Created by Aaron Zhang on 2/11/2018.
//

#include<iostream>
#include<string>
#include "StudentInfo.h"

StudentInfo::StudentInfo(){
    // does nothing
}


StudentInfo::StudentInfo(string n, string sd, string m, string g, string su){
    name = n;
    standing = sd;
    major = m;
    grade = g;
    status = su;
}

StudentInfo::~StudentInfo() {
    //does nothing
}

void StudentInfo::setName(string n){
    name = n;
}


void StudentInfo::setStanding(string sd){
    standing = sd;
}


void StudentInfo::setMajor(string m){
    major = m;
}


void StudentInfo::setStatus(string su){
    status = su;
}


void StudentInfo::setGPA(string g){
    grade = g;
}


string StudentInfo::getGPA() const{
    return grade;
}

string StudentInfo::getName() const{
    return name;
}

string StudentInfo::getStanding() const{
    return standing;
}

string StudentInfo::getMajor() const{
    return major;
}

string StudentInfo::getStatus() const{
    return status;
}

//returns name and allows user to do other things through the name
ostream& operator << (ostream& outs, const StudentInfo &s){
    outs << s.name;
    return outs;
}