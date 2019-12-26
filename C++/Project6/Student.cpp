//
// Created by zhiyi on 3/25/2018.
//

#include "Student.h"

/********************Global function************************/

void Calculator(){

}

/**********************Student class************************/

Student::Student(){
    firstName = "null";
    lastName = "null";
    yearOfClass = 2022;
};

Student::Student(string f, string l, int y){
    firstName = f;
    lastName = l;
    yearOfClass = y+4;
}

string Student::get_firstName() const {
    return firstName;
}

string Student::get_lastName() const {
    return lastName;
}

int Student::get_year() const{
    return yearOfClass;
}

void Student::set_firstName(string f){
    firstName = f;
};

void Student::set_lastName(string l){
    lastName = l;
}

void Student::set_year(int y){
    yearOfClass = y+4;
}

ostream& operator << (ostream& outs, const Student &s){
    outs << s.firstName << " " << s.lastName << ": class of " << s.yearOfClass << endl;
    return outs;
}

/*******************CEMSStudent class************************/

CEMSStudent::CEMSStudent(){
    math_credits = 0;
    major_credits = 0;
    elective_credits = 0;
}

CEMSStudent::CEMSStudent(int math, int major, int elect){
    math_credits = math;
    major_credits = major;
    elective_credits = elect;
}

int CEMSStudent::get_math() const{
    return math_credits;
};

int CEMSStudent::get_major() const{
    return major_credits;
}

int CEMSStudent::get_elect() const{
    return elective_credits;
}

void CEMSStudent::set_math(int math){
    math_credits = math;
}

void CEMSStudent::set_major(int major){
    major_credits = major;
}

void CEMSStudent::set_elect(int elect){
    elective_credits = elect;
}

void CEMSStudent::GradCalculator() {
    int credits = (12 - math_credits) + (38 - elective_credits) + (70 - major_credits);
    cout << "You need " << credits << " more credits to graduate" << endl;
}

/*********************CASStudent class************************/

CASStudent::CASStudent(){
    math_credits = 0;
    major_credits = 0;
    elective_credits = 0;
    minor_credits = 0;
}

CASStudent::CASStudent(int math, int major, int elect, int minor){
    math_credits = math;
    major_credits = major;
    elective_credits = elect;
    minor_credits = minor;
}

int CASStudent::get_math() const{
    return math_credits;
};

int CASStudent::get_major() const{
    return major_credits;
}

int CASStudent::get_elect() const{
    return elective_credits;
}

int CASStudent::get_minor() const{
    return minor_credits;
}

void CASStudent::set_math(int math){
    math_credits = math;
}

void CASStudent::set_major(int major){
    major_credits = major;
}

void CASStudent::set_elect(int elect){
    elective_credits = elect;
}

void CASStudent::set_minor(int minor){
    minor_credits = minor;
}

void CASStudent::GradCalculator() {
    int credits = (6 - math_credits) + (16 - elective_credits) + (60 - major_credits) + (38 - minor_credits);
    cout << "You need " << credits << " more credits to graduate" << endl;
}

/*********************Calculator class***********************/

ObjectVector::ObjectVector(){}

void ObjectVector::addToVector(CEMSStudent cems) {
    cemsVector.erase(cemsVector.begin());
    cemsVector.push_back(cems);
}

void ObjectVector::addToVector(CASStudent cas) {
    casVector.erase(casVector.begin());
    casVector.push_back(cas);
}