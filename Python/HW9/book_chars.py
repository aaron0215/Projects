#Aaron Zhang
#CS021 Green group
#This is a function to count uppercase,
#lowercase, digits and spaces in known
#document.
#Lead user to input the file name and read
#all content inside the file, and use for
#loop to define each element and count.
#Use except function to except IOError
#which is the only error may happen here.
def main():
    try:
        file_name = input('Enter the filename: ')   #IOError may occur
        infile = open(file_name,'r')
        count_up = 0
        count_low = 0
        count_dig = 0
        count_sp = 0
        content = ''
        for ch in infile:
            content += ch
        for char in content:
            if char.isupper():
                count_up += 1
            elif char.islower():
                count_low += 1
            elif char.isdigit():
                count_dig += 1
            elif char.isspace():
                count_sp += 1
        infile.close()
    except IOError:
        print('The file cannot be found by this name')
    else:
        print('Uppercase letters:',count_up)
        print('Lowercase letters:',count_low)
        print('Digits:',count_dig)
        print('Spaces:',count_sp)
main()
