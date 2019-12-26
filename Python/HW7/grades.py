#Aaron Zhang
#CS O21 Green group
#This is a program to convert grades
#from numbers into letter and count
#the number of different levels
#Use function to open a file contains grade
#Create the file to receive output results
#Use limit values to judge and convert the grades
#Add count statements after convert statements
#Convert numbers into asterisks finally
#Use try and except function to except potential errors
def main():
    minimum = 0
    f_limit = 60
    c_limit = 70
    d_limit = 80
    b_limit = 90
    a_limit = 100
    count_a = 0
    count_b = 0
    count_c = 0
    count_d = 0
    count_f = 0
    try:
        infile = open('grades.txt','r') #IOError
        outfile = open('grades_out.txt','w')
        for line in infile:
            try:
                num = int(line) #ValueError
                if num < f_limit and num > minimum:
                    outfile.write('F'+'\n')
                    count_f += 1
                if num >= f_limit and num < c_limit:
                    outfile.write('D'+'\n')
                    count_d += 1
                if num >= c_limit and num < d_limit:
                    outfile.write('C'+'\n')
                    count_c += 1
                if num >= d_limit and num < b_limit:
                    outfile.write('B'+'\n')
                    count_b += 1
                if num >= b_limit and num <= a_limit:
                    outfile.write('A'+'\n')
                    count_a += 1
            except ValueError:
                print(line+'is an invalid value')
        outfile.write('\n')
        outfile.write('A: '+'*'*count_a+'\n')
        outfile.write('B: '+'*'*count_b+'\n')
        outfile.write('C: '+'*'*count_a+'\n')
        outfile.write('D: '+'*'*count_d+'\n')
        outfile.write('F: '+'*'*count_f+'\n')
        infile.close()
        outfile.close()
    except IOError:
        print('The file cannot be found, please check the name')
main()
    
