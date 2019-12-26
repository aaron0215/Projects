#Aaron Zhang
#CS021 Green Group
#Main function leads user to input values and calculate them
#Use-defined function accept values from calling function and show distance
def main():
    speed = int(input('Enter the speed of the vehicle in mph: '))
    while speed<=0:
        print('speed must be greater than zero')
        speed = int(input('Enter the speed of the vehicle in mph: '))
    hours = int(input('Enter the number of hours traveled: '))
    while hours<=0:
        print('time must be great than zero')
        hours = int(input('Enter the number of hours traveled: '))
    show_travel(speed,hours)
def show_travel(v,h):
    if v>0 and h>0:
        print('Hour  Distance Traveled')
        print('------------------------')
        for i in range (1,h+1):
            distance = v * i
            print(i,format(distance,'15.1f'))
main()
