#Aaron Zhang
#CS021 Green Group
#Define function and initial value of speed and hours
#Use user-defined function to accept and calculation all values
def main():
    v=1
    h=1
    show_travel(v,h)
def show_travel(speed,hours):
    speed = int(input('Enter the speed of the vehicle in mph: '))
    while speed<=0:
        print('speed must be greater than zero')
        speed = int(input('Enter the speed of the vehicle in mph: '))
    hours = int(input('Enter the number of hours traveled: '))
    while hours<=0:
        print('time must be great than zero')
        hours = int(input('Enter the number of hours traveled: '))
    if speed>0 and hours>0:
        print('Hour  Distance Traveled')
        print('------------------------')
        for i in range (1,hours+1):
            distance = speed * i
            print(i,format(distance,'15.1f'))
main()
