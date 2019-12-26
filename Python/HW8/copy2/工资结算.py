def main():
    outfile = open('账单.txt','w')
    total_payroll = 0
    aver_paycheck = 0
    name_count = 0
    name_list = []
    hrs_work_list = []
    hrs_rate_list = []
    name_int = input('姓名: ')
    while name_int != '': 
        try:
            hrs_work = float(input('工作时长: '))
            hrs_rate = float(input('时薪: '))
            name_list.append(name_int)
            hrs_work_list.append(str(format(hrs_work,'.2f')))
            hrs_rate_list.append(str(format(hrs_rate,'.2f')))
            name_count += 1
            name_int = input('姓名: ')
        except ValueError:
            print('时长和时薪必须为数字')
            print("请重新输入")
            name_int = input('姓名')
    index = 0
    while index < len(name_list): 
        outfile.write(str(name_list[index]) + '\t'+'\t')
        outfile.write(str(hrs_work_list[index]) + '\t') 
        outfile.write(str(hrs_rate_list[index]) + '\t')
        total_pay = float(hrs_work_list[index])*float(hrs_rate_list[index])
        outfile.write(str(format(total_pay,'.2f')) + '\n')
        index += 1
        total_payroll += total_pay
    outfile.write('\n')
    aver_paycheck = total_payroll / name_count
    outfile.write('总收入 = $'+str(format(total_payroll,'.2f')) + '\n')
    outfile.write('平均收入 = $'+str(format(aver_paycheck,'.2f')) + '\n')
    outfile.close()
main()

#@本平台不持有版权，如有传播请联系平台
