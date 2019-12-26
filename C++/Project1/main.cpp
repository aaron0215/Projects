//Aaron Zhang
//CS 120
//This is the program to search the most popular songs in specific year

#include <iostream>
#include <fstream>
#include <sstream>
#include <iomanip>
using namespace std;

int main() {
    std::cout << "This program can help you search the most popular songs in a certain year." << std::endl;

    //all variables
    int ChosenYear, year;
    string info, junk;
    string again = "Y";

    ofstream outFile("DataSet.txt", ios::app);

    //while loop to generate
    while (again == "Y" || again == "y") {

        //open the file
        ifstream inFile("Songs.txt");
        cout << "Enter a year (2008-2016): "; //user prompt

        //input validation
        while((!(cin >> ChosenYear)) || (ChosenYear > 2016 || ChosenYear < 2008)) {
            cout << "Please enter a valid integer between 2008 and 2016: ";
            cin.clear();
            cin.ignore(100, '\n');
        }

        //convert integer to string to be quoted
        stringstream ss;
        string ChosenYearString = to_string(ChosenYear);

        //first type of output manipulator: quoted(string)
        ss << quoted(ChosenYearString);
        cout << "The year you entered: " << ss.str() << "\n" << endl;


        //second and third type of output manipulators: setw() and setfill()
        cout << setw(36) << setfill('*');

        //fourth type of output manipulator: setbase(). Just for fun :D
        cout << " The most popular songs in " << setbase(10) << ChosenYear <<" ";
        cout << setw(10) << setfill('*') << "*" << endl;
        cout << setw(36) << setfill('*');
        cout << " The most popular songs in " << setbase(16) << ChosenYear <<" (This is a hexadecimal form, just for fun :D) ";
        cout << setw(10) << setfill('*') << "*" << endl;

        //read the file and display the songs as the year user chose
        while (inFile >> year) {
            if (year == ChosenYear) {
                getline(inFile, junk);
                getline(inFile, info);
                cout << info << endl;
            }
            else {
                getline(inFile, junk);
                getline(inFile, info);
            }
        }
        inFile.close();

        cout << "\n";

        //record the years that user searched
        outFile << ChosenYear << endl;

        //repeat
        cout << "Do you want to search another year? (Press Y to continue): ";
        cin.clear();
        cin.ignore(100,'\n');
        cin >> again;
    }

    outFile.close();

    return 0;
}