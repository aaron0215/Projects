//
// Created by Aaron Zhang on 2/5/2018.
//

#include "FeeCalculator.h"

FeeCalculator::FeeCalculator() {
    height = 5.0;
    length = 20.0;
    width = 15.0;
    weight = 1.0;
}

FeeCalculator::FeeCalculator(double w){
    height = 5.0;
    length = 20.0;
    width = 15.0;
    weight = w;
}

FeeCalculator::FeeCalculator(double l, double wd){
    height = 5.0;
    length = l;
    width = wd;
    weight = 1.0;
}

FeeCalculator::FeeCalculator(double l,double h, double wd){
    height = h;
    length = l;
    width = wd;
    weight = 1.0;
}

FeeCalculator::FeeCalculator(double l,double h, double wd, double w){
    height = h;
    length = l;
    width = wd;
    weight = w;
}

FeeCalculator::~FeeCalculator() {
    //do nothing
}

void FeeCalculator::setHeight(double h){
    height = h;
}

void FeeCalculator::setWeight(double w){
    weight = w;
}

void FeeCalculator::setLength(double l){
    length = l;
}

void FeeCalculator::setWidth(double wd){
    width = wd;
}

double FeeCalculator::getHeight() const{
    return height;
};

double FeeCalculator::getLength() const{
    return length;
};

double FeeCalculator::getWidth() const{
    return width;
};

double FeeCalculator::getWeight() const{
    return weight;
};

double FeeCalculator::VolCalculator() {
    volume = length*width*height;
    return volume;
}

//The price of box that item uses differs
void FeeCalculator::CalCost(){
    volume = VolCalculator();
    if (volume <= 2000){
        boxPrice = 2;
    }
    else if (volume > 2000 && volume <= 5000){
        boxPrice = 5;
    }
    else{
        boxPrice = 10;
    }
    cost = InitPrice + (weight-1)*PriceRate + boxPrice;
}

double FeeCalculator::ShowEstimation(){
    CalCost();
    return cost;
}

//add costs of different items
FeeCalculator& FeeCalculator::operator += (const FeeCalculator &f){
    cost += f.cost;
    return *this;
}

//only display cost
ostream& operator << (ostream& outs,
                      const FeeCalculator &f) {
    outs << f.cost;
    return outs;
}