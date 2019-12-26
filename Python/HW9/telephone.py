#Aaron Zhang
#CS021 Green group
#This is a function that can transfer
#telephone number into pure digits
#Lead the user to enter the string(number)
#Use lower case function to simplify the
#translation process.
#Use for loop and if function to grab and
#translate each element in the number
def main():
    in_string = input('Enter the telephone number in the format XXX-XXX-XXXX: ')
    new_string = in_string.lower()
    num_string = ''
    for ch2 in new_string:
        if ch2.isalpha():
            if ch2 == 'a' or ch2 == 'b' or ch2 == 'c':
                ch2 = '2'
            elif ch2 == 'd' or ch2 == 'e' or ch2 == 'f':
                ch2 = '3'
            elif ch2 == 'g' or ch2 == 'h' or ch2 == 'i':
                ch2 = '4'
            elif ch2 == 'j' or ch2 == 'k' or ch2 == 'l':
                ch2 = '5'
            elif ch2 == 'm' or ch2 == 'n' or ch2 == 'o':
                ch2 = '6'
            elif ch2 == 'p' or ch2 == 'q' or ch2 == 'r' or ch2 == 's':
                ch2 = '7'
            elif ch2 == 't' or ch2 == 'u' or ch2 == 'v':
                ch2 = '8'
            elif ch2 == 'w' or ch2 == 'x' or ch2 == 'y' or ch2 == 'z':
                ch2 = '9'
            num_string += ch2
        else:
            num_string += ch2
    print('The phone number is',num_string)
main()
