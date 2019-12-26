#include <iostream>
using namespace std;

int my_function(int n);

int main() {
    int number = 0;
    number += 8;
    number /= 3;
    number -= -28;
    number %= 7;
    number *= 33;
    number /= 4;
    number += 18;
    number *= 3;
    number -= 99;
    number *= 33;
    number %= 78;
    number += 444;
    number *= -2;
    number += 438;
    number = my_function(number);
    number += 36;
    number %= 80;
    number = (number < 40) ? 55 : 22;
    number *= -3;
    number -= -54;
    number += 17;
    number += my_function(my_function(222222));
    number += 2;
    number *= (number + 90);
    number /= 5;
    number -= 88;
    number += 3456;
    number = my_function(number);
    number -= 21;
    number += 14;
    number *= 5;
    number -= 90;
    number /= -8;
    number += 45;
    number -= my_function(-10293);
    number *= 15;
    number += 642;
    number *= 32;
    number = my_function(number);
    number -= 91;
    number /= 11;
    number = max(number-39, 12);
    return 0;
}

int my_function(int n) {
    for (int i = 0; i < 7; ++i) {
        n /= 2;
    }
    return n;
}