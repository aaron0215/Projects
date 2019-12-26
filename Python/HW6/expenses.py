#Aaron Zhang
#CS021 Green group
#This is a program to compute the total
#cost of gasoline in a trip
#Give a few of standard value
#Lead user to input the value
#Use while loop to achieve the repeat
mpg_hwy = 38
mpg_city = 28
price_gal = 2.29
pt = 0.01
maximum = 100
minimum = 0
def main():
    print('Computing your gasonline expenses...')
    repeat = 'y' or 'Y'
    while repeat == 'y' or repeat == 'Y':
        print()
        total_mile = float(input('Total miles driven: '))
        while total_mile<=0:
            total_mile = float(input('Enter a value > 0: '))
        highway_percent = float(input('Percentage of total miles driven on a high way: '))
        while highway_percent>maximum or highway_percent<minimum:
            highway_percent = float(input('Enter a value between 0 and 100: '))
        total_gal = total_gallons(total_mile,highway_percent)
        total_cost = gas_expense(total_gal)
        print()
        print('Here is the information for your trip:')
        print()
        print('Total miles:',format(total_mile,'.1f'))
        print('Gas consumption:',format(total_gal,'.1f'),'gal')
        print('Total cost: $',format(total_cost,'.2f'))
        print()
        repeat = input('Compute gas expense for another trip (y or n)? ')
def total_gallons(total_m,highway_p):
    total_gas_consumption = total_m*highway_p*pt/mpg_hwy+total_m*(maximum-highway_p)*pt/mpg_city
    return total_gas_consumption
def gas_expense (total_g):
    total_spent = total_g*price_gal
    return total_spent
main()
    
