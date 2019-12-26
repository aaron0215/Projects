#Aaron Zhang
#CS021 Green group
#This is a program to add line numbers
#and put contents into new file
#Lead users to input the names of two files
#Use a for loop to transfer all content and
#add line numbers before each line
#Except potential error
def main():
    try:
        file_name = input('Enter the name of file: ')
        infile = open(file_name, 'r') #IOError
        outfile = open('ln_'+file_name,'w')
        num = 1
        for line in infile:
            outfile.write(str(num) + ':' + ' ' + line)
            num += 1
        infile.close()
        outfile.close()
    except IOError:
        print('The file cannot be found, please chack the name')
main()
