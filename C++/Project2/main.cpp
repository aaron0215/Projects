// Aaron Zhang
// CS120
// This is a program can estimate the cost of mailing items
// Main.cpp doesn't use all methods
// But all methods can be added in further development if needed

#include <iostream>
#include <string>
#include "FeeCalculator.h"
using namespace std;

void displayMenu() {

    //display the menu
    cout << "\nHere is the menu: \n"
         << "1. Calculate the cost of small stuff (less than 2L) with known weight \n"
         << "2. Calculate the cost of light stuff (less than 1lbs) with known length and width \n"
         << "3. Calculate the cost of light stuff (less than 1lbs) with known length, width and height \n"
         << "4. Calculate the cost of stuff with known length, width, height and weight \n"
         << "5. Just check the default settings and price\n"
         << "6. I have more than one items with known size \n"
         << "7. Quit \n"
         << "(The unit for length, width and length is centimeter, for weight is lbs. Use space to separate) \n"
         << endl;
};

void choiceAction(int choice) {

    double l,wd,h,w;     //length, width, height, weight
    int count = 0;       //count for #6 choice

    switch(choice){
        case 1: {
            cout << "Enter the weight: ";
            if (cin >> w && w >= 0) {      //all cases use similar statements to valid input
                FeeCalculator f1(w);
                f1.CalCost();
                cout << "The estimated cost is: $" << f1 << endl;
            }
            else{
                cout << "Invalid number" << endl;
            }
            break;
        }

        case 2: {
            cout << "Enter the length and width: ";
            if(cin >> l >> wd && l >= 0 && wd >= 0) {
                FeeCalculator f2(l, wd);
                f2.CalCost();
                cout << "The estimated cost is: $" << f2 << endl;
            }
            else{
                cout << "Invalid number" << endl;
            }
            break;
        }

        case 3: {
            cout << "Enter the length, width and height: ";
            if (cin >> l >> wd >> h && l >= 0 && wd >= 0 && h >=0) {
                FeeCalculator f3(l, h, wd);
                f3.CalCost();
                cout << "The estimated cost is: $" << f3 << endl;
            }
            else{
                cout << "Invalid number" << endl;
            }
            break;
        }

        case 4: {
            cout << "Enter the length, width, height and weight: ";
            if (cin >> l >> wd >> h >> w && l >= 0 && wd >= 0 && h >=0 && w >= 0) {
                FeeCalculator f4(l, h, wd, w);
                f4.CalCost();
                cout << "The estimated cost is: $" << f4 << endl;
            }
            else{
                cout << "Invalid number" << endl;
            }
            break;
        }

        case 5: {
            FeeCalculator f5;
            f5.CalCost();
            cout << "The default length is: " << f5.getLength() << "cm" << endl;
            cout << "The default width is: " << f5.getWidth() << "cm" << endl;
            cout << "The default height is: " << f5.getHeight() << "cm" << endl;
            cout << "The default weight is: " << f5.getWeight() << "lbs" << endl;
            cout << "The estimated cost is: $" << f5 << endl;
            break;
        }

        case 6: {
            cout << "How many items you have: ";
            if (cin >> count && count >= 1) {
                cout << "Enter the length, width, height and weight of first item: ";
                if (cin >> l >> wd >> h >> w && l >= 0 && wd >= 0 && h >= 0 && w >= 0) {
                    FeeCalculator totalF(l, h, wd, w);
                    totalF.CalCost();
                    count--;
                    while (count > 0) {
                        cout << "Enter the length, width, height and weight of next item: ";
                        if (cin >> l >> wd >> h >> w && l >= 0 && wd >= 0 && h >= 0 && w >= 0) {
                            FeeCalculator nextF(l, h, wd, w);
                            nextF.CalCost();
                            totalF += nextF;
                            count--;
                        }
                        else{     // Validation for length, width, height and weight of next item
                            cout << "Invalid number, calculation terminated" << endl;
                            break;
                        }
                    }
                    cout << "Total cost: $" << totalF << endl;
                }

                else{       // Validation for length, width, height and weight of first item
                    cout << "Invalid number" << endl;
                }

            }
            else{       // Validation for user's input
                cout << "Invalid number" << endl;
            }
            break;
        }

        case 7:
            break;

    } //end of switch
};

int main() {

    std::cout << "This is a program can give you an approximate cost when you want to mail something" << std::endl;

    int choice;          //user's choice
    string again = "Y";  //while loop controller

    while (again == "Y" || again == "y"){

        displayMenu();
        cout << "Enter your choice: ";

        //input validation of choice
        while((!(cin >> choice)) || (choice > 7 || choice < 1)) {
            cout << "Please enter a valid integer between 1 and 7: ";
            cin.clear();
            cin.ignore(100, '\n');
        }

        choiceAction(choice);

        cout << "\nDo you want to try another one? (Press Y to continue): ";
        cin.clear();
        cin.ignore(100, '\n');
        cin >> again;
    }

    return 0;
}
