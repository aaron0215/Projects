//
// Created by Aaron Zhang on 2/5/2018.
//

#ifndef PROJECT2_FEECALCULATOR_H
#define PROJECT2_FEECALCULATOR_H

#include <iostream>
using namespace std;

class FeeCalculator{
private:
    double length, height, width, weight, volume, cost;
    int boxPrice;
    const double InitPrice = 9.43;
    const double PriceRate = 0.3;

public:
    /**
     * Default Constructor
     * Requires: nothing
     * Modifies: length, height, weight and width
     * Effects: set the data to be initial values
     */
    FeeCalculator();

    /**
     * Non-Default Constructor
     * Requires: nothing
     * Modifies: length, height, weight and width
     * Effects: set the weight to given value and others to be initial
     */
    FeeCalculator(double w);

    /**
     * Non-Default Constructor
     * Requires: nothing
     * Modifies: length, height, weight and width
     * Effects: set the length and width to given value and others to be initial
     */
    FeeCalculator(double l,double wd);

    /**
     * Non-Default Constructor
     * Requires: nothing
     * Modifies: length, height, weight and width
     * Effects: set the length, height and width to given values
     */
    FeeCalculator(double l,double h, double wd);

    /**
     * Non-Default Constructor
     * Requires: nothing
     * Modifies: length, height, weight and width
     * Effects: set the data to given values
     */
    FeeCalculator(double l,double h, double wd, double w);

    /**
     * method
     * Requires: nothing
     * Modifies: nothing
     * Effects: return the volume
     */
    double VolCalculator();

    /**
     * method
     * Requires: nothing
     * Modifies: nothing
     * Effects: display the estimated fees
     */
    double ShowEstimation();

    /**
     * method
     * Requires: nothing
     * Modifies: cost
     * Effects: calculate the cost and give value to variable
     */
    void CalCost();

    /**
     * Destructor
     * Requires: nothing
     * Modifies: nothing
     * Effects: does nothing
     */
    ~FeeCalculator();

    /**
     * height setter
     * Requires: nothing
     * Modifies: height
     * Effects: sets the height to the given value
     * @param height
     */
    void setHeight(double h);

    /**
     * length setter
     * Requires: nothing
     * Modifies: length
     * Effects: sets the length to the given value
     * @param length
     */
    void setLength(double l);

    /**
     * width setter
     * Requires: nothing
     * Modifies: width
     * Effects: sets the width to the given value
     * @param width
     */
    void setWidth(double wd);

    /**
     * weight setter
     * Requires: nothing
     * Modifies: weight
     * Effects: sets the weight to the given value
     * @param weight
     */
    void setWeight(double w);

    /**
     * length getter
     * Requires: nothing
     * Modifies: nothing
     * Effects: return the length
     */
    double getLength() const;

    /**
     * weight getter
     * Requires: nothing
     * Modifies: nothing
     * Effects: return the weight
     */
    double getWeight() const;

    /**
     * height getter
     * Requires: nothing
     * Modifies: nothing
     * Effects: return the height
     */
    double getHeight() const;

    /**
     * width getter
     * Requires: nothing
     * Modifies: nothing
     * Effects: return the width
     */
    double getWidth() const;

    /**
     * Overloaded Operator (Unary)
     * Member of the class (not a friend)
     * Requires: nothing
     * Modifies: fee
     * Effects: add costs of different objects
     */
    FeeCalculator& operator += (const FeeCalculator &f);

    /**
     * Overloaded Operator (Binary)
     * Friend of the class
     * Requires: nothing
     * Modifies: nothing
     * Effects: prints the FeeCalculator to the ostream (print cost)
    */
    friend ostream& operator << (ostream& outs,
                                 const FeeCalculator &f);
};

#endif //PROJECT2_FEECALCULATOR_H
