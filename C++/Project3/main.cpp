// Aaron Zhang
// CS120
// This is a program that can read students information from a file
// and store them as objects into a vector. User can review, update
// or remove any data by operating the vector and the information
// inside the vector will be written into a file as user's desire.
// This program contains six methods besides from main method.
// Two void methods display two menus which have different options.
// Two void methods would execute specific order as user chooses.
// One boolean method searches if the name from user input exists.
// One method to take care of special choices.

#include <iostream>
#include <fstream>
#include <sstream>
#include "College.h"

using namespace std;

int choice,initChoice;  // choices for initial and second menu
string search_name,updateInfo,updateInfo2;  // name to search and update information from user

//Create college object
College c;

//Gain StudentInfo objects as vector from College object
vector <StudentInfo> Information = c.GetVector();

// This is the second menu
// It will be activated while user wants to
// modify or review the information of single student
void displayMenu(){
    cout << "\nWhat do you want to do to this student: \n"
         << "1. Display student information \n"
         << "2. Update major information \n"
         << "3. Update standing information \n"
         << "4. Update GPA \n"
         << "5. Update status \n"
         << "6. Remove this student \n"
         << "7. Finish and save \n"
         << endl;
};

// This is the initial menu
// Indicates what this program can do
void displayInitMenu(){
    cout << "\nWhat can I do for you: \n"
         << "1. Review/Update information of a student \n"
         << "2. Add a new student \n"
         << "3. Review the list of students \n"
         << "4. Quit \n"
         << endl;
};

// This is the function to search
// if the student is on the list
bool foundName(){
    bool found = false;
    string findIt = search_name;
    for (int i = 0; i <= Information.size(); ++i){
        if (Information[i].getName() == findIt){
            found = true;
        }
    }
    return found;
};

// These are the actions of second menu
// Keep working on single student until
// user chooses #7 to save and quit
void actions(){
    int index = 0;
    for (int i = 0; i <= Information.size(); ++i){
        if (Information[i].getName() == search_name){
            index = i;
        }
    }
    switch (choice) {
        case 1: {
            // Display all information of chosen student
            cout << Information[index].getName() << endl;
            cout << Information[index].getMajor() << endl;
            cout << Information[index].getStanding() << endl;
            cout << Information[index].getGPA() << endl;
            cout << Information[index].getStatus() << endl;
            break;
        }

        case 2: {
            // Set new major to chosen student
            cout << "Enter the new major: ";
            cin >> updateInfo >> updateInfo2;
            Information[index].setMajor(updateInfo.append(" ").append(updateInfo2));
            break;
        }

        case 3: {
            // Set new standing to chosen student
            cout << "Enter the new standing: ";
            cin >> updateInfo;
            Information[index].setStanding(updateInfo);
            break;
        }

        case 4: {
            // Set new GPA to chosen student
            cout << "Enter the new GPA: ";
            cin >> updateInfo;
            Information[index].setGPA(updateInfo);
            break;
        }

        case 5: {
            // Update status of chosen student
            cout << "Enter the updated status: ";
            cin >> updateInfo;
            Information[index].setStatus(updateInfo);
            break;
        }

        case 6: {
            // remove a student
            Information.erase(Information.begin() + index);
            // Looks like there will be empty element
            // This is the only way that I found can take care of it
            Information.erase(Information.begin() + (Information.size()-1));
            cout << "Removed" << endl;
            break;
        }

        case 7: {
            // Data will be saved to file when user chooses to quit the system
            break;
        }
    }
};

// This is the function that would be called
// since user wants to work on single student's information.
// It controls the user's choice to second menu and call
// actions to operate as user's choice.
// Consider the reality, #6 and #7 should be executed once
// and the program should return to previous menu. This is
// the reason why this method exists
void adjustInfo(){

    displayMenu();
    cout << "Enter your choice: ";
    while ((!(cin >> choice)) || (choice > 7 || choice < 1)) {
        cout << "Please enter a valid integer between 1 and 7: ";
        cin.clear();
        cin.ignore(100, '\n');
    }
    while(choice < 6){
        actions();
        displayMenu();
        cout << "\nWhat's next: ";
        while ((!(cin >> choice)) || (choice > 7 || choice < 1)) {
            cout << "Please enter a valid integer between 1 and 7: ";
            cin.clear();
            cin.ignore(100, '\n');
        }
    }
    if (choice == 7 || choice == 6){
        actions();
    }
};

// These are the actions of second menu
// While user wants to work on single student,
// it would call adjustInfo function to operate
void initActions(){
    switch(initChoice){
        case 1:{
            // Turns program to work on single student
            cout << "Whose information you want to review/update (enter Q to quit): ";
            cin >> search_name;
            // Validation
            if(search_name != "Q" && search_name != "q") {
                while(!foundName()){    // Valid if the name exists on the list
                    cout << "Can't find the name you entered, please check" << endl;
                    cout << "Whose information you want to review/update (enter Q to quit): ";
                    cin.clear();
                    cin.ignore(100,'\n');
                    cin >> search_name;
                }
                adjustInfo();
            }
            break;
        }

        case 2:{
            // Add new students
            string name, standing, major, gpa, status;
            cout << "Enter the name: ";
            cin >> name;
            cout << "Enter the major: ";
            cin >> updateInfo >> updateInfo2;
            major = updateInfo.append(" ").append(updateInfo2); // Take care of input space
            cout << "Enter the GPA: ";
            cin >> gpa;
            cout << "Enter the standing: ";
            cin >> standing;
            cout << "Enter the status: ";
            cin >> status;
            Information.emplace_back(StudentInfo(name, standing, major, gpa, status));
            cout << "Information added" << endl;
            break;
        }

        case 3:{
            // Display the list of students
            cout << "Here is the list of students: " << endl;
            for (int i = 0; i <= Information.size(); ++i){
                cout << Information[i] << endl;
            }
            break;
        }
    }
};


int main() {
    std::cout << "This is a program to review/update the information of students. \n" << std::endl;

    // Activate the initial menu
    displayInitMenu();

    // Input validation for initial choice
    cout << "Enter your choice: ";
    while ((!(cin >> initChoice)) || (initChoice > 4 || initChoice < 1)) {
        cout << "Please enter a valid integer between 1 and 4: ";
        cin.clear();
        cin.ignore(100, '\n');
    }

    // Activate the initial actions
    while(initChoice < 4){
        initActions();
        displayInitMenu();
        cout << "What do you want to do next: ";
        cin.clear();
        cin.ignore(100,'\n');
        cin >> initChoice;
    }

    // Quit the whole program and save all data
    if(initChoice == 4){
        cout << "Enter the name of file to save data (enter Q to quit without saving): ";
        string filenameOut;
        cin >> filenameOut;
        if(filenameOut != "Q" && filenameOut != "q") {
            ofstream outFile(filenameOut);
            for (int i = 0; i <= Information.size(); ++i) {
                outFile << Information[i].getName() << '\n'
                        << Information[i].getMajor() << '\n'
                        << Information[i].getStanding() << '\n'
                        << Information[i].getGPA() << '\n'
                        << Information[i].getStatus() << endl;
            }
            outFile.close();
        }
    }
    cout << "\nThanks for using this system." << endl;

    return 0;
}