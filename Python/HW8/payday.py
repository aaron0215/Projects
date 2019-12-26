#Aaron Zhang
#CS021 Green group
#This is function to receive worked hours and
#hourly rate to calculate and display payroll
#Use three list to store name, hours and rate
#Extract values from list to calculate at the end
def main():
    outfile = open('Payroll.txt','w')
    total_payroll = 0
    aver_paycheck = 0
    name_count = 0
    name_list = []
    hrs_work_list = []
    hrs_rate_list = []
    name_int = input('Name (just hit Enter when done): ')
    while name_int != '': #Input section
        try:
            hrs_work = float(input('Hours worked: '))
            hrs_rate = float(input('Hourly rate: '))
            name_list.append(name_int)
            hrs_work_list.append(str(format(hrs_work,'.2f')))
            hrs_rate_list.append(str(format(hrs_rate,'.2f')))
            name_count += 1
            name_int = input('Name (just hit Enter when done): ')
        except ValueError:
            print('Values must be numeric.')
            print("Please input employee's name and values again.")
            name_int = input('Name (just hit Enter when done): ')
    index = 0
    while index < len(name_list): #Display and calculation section
        outfile.write(str(name_list[index]) + '\t'+'\t')
        outfile.write(str(hrs_work_list[index]) + '\t') 
        outfile.write(str(hrs_rate_list[index]) + '\t')
        total_pay = float(hrs_work_list[index])*float(hrs_rate_list[index])
        outfile.write(str(format(total_pay,'.2f')) + '\n')
        index += 1
        total_payroll += total_pay
    outfile.write('\n')
    aver_paycheck = total_payroll / name_count
    outfile.write('Total Payroll = $'+str(format(total_payroll,'.2f')) + '\n')
    outfile.write('Average Paycheck = $'+str(format(aver_paycheck,'.2f')) + '\n')
    outfile.close()
main()
