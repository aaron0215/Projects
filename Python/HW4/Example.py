print('Welcome to my prime number detector.')
print('Provide an integer and I will determine if it is prime.')
print()
keep_going='y'
while keep_going=='y'or keep_going=='Y':
    d=1
    x=1
    num=int(input('Enter an integer > 1: '))
    while num<=d:
        num=int(input('Input must be > 1, try again: '))
    while num>d:
        if num==2:
            print(num,'is prime')
        elif num>2:
            for i in range (2,num):
                if (num%i)==0:
                    print(num,'is not prime')
                    break
                else:
                    print(num,'is prime')
                    break
            break
    keep_going=input('Do you want to try another number? (Y/N) : ')
