#Aaron Zhang
#CS021 Green Group
#Use main function lead users to input value
#Main function will calculate all numbers
#Display function will show formatted result
def main():
    val_cal_tax = 0.72
    percent = 0.6
    actual_val = int(input('Enter the actual value: '))
    while actual_val<0:
        print('Actual value must be >= 0')
        actual_val = int(input('Enter the actual value: '))
    assessed_value = actual_val * percent
    pro_tax = (assessed_value/100)*0.72
    show_property_tax(assessed_value,pro_tax)
def show_property_tax(value,tax):
    print('Assessed value: $','{:,.2f}'.format(value),sep='')
    print('Property tax: $','{:,.2f}'.format(tax),sep='')
main()
