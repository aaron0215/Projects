#Aaron Zhang
#CS021 Green Group
#Lead users to input the value of worked days
#Use two while functions to evaluate input value
#The value will be calculated if it is more than or equal to 1
#The user will be noticed if value is less than 1
num=int(input('Number of days worked: '))
d=1
p=True
while num<d and p:
    num=int(input('Enter number of days >= 1: '))
while num>=d and p:
    s0=0.01
    t=0
    a=0
    print()
    print('Salary Earned Each Day')
    print()
    print('','Day',' ','Amount($)')
    print('','---',' ','----------')
    for i in range (1,num+1):
        s=s0*(2**(i-1))
        t+=s
        a=t/i
        print(format(i,'3.0f'),format(s,'10.2f'))
    print()
    print('Your total pay = $',format(t,'.2f'),sep='')
    print('Your average daily wage = $',format(a,'.2f'),sep='')
    p=False

