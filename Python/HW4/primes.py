#Aaron Zhang
#CS021 Green group
#This is a program to check whether input number is prime
#Lead user to input a number is more than 1
#Use modulus symbol to defind whether a number is prime
#When finding the number is not prime, the calculation will be terminated
#If there is no number can be divided, output prime result
print('Welcome to my prime number detector.')
print('Provide an integer and I will determine if it is prime.')
print()
keep_going='y'
while keep_going=='y'or keep_going=='Y':
    d=2
    p = True
    num=int(input('Enter an integer > 1: '))
    while num<d and p:
        num=int(input('Input must be > 1, try again: '))
    if num==d:
        print(num,'is prime')
        p=False
    while num>d and p:
        if num%d == 0:
            print(num,'is not prime')
            p=False
        d+=1
    if p:
        print(num,'is prime')
        p=False
    keep_going=input('Do you want to try another number? (Y/N) : ')
