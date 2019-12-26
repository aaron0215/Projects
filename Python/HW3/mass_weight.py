#Aaron Zhang
#CS021 Green Group
#Lead user to input the mass in kilograms
#Switch kilograms into newtons than compare with standard value
#Display result
m=int(input("Enter the object's mass in kilograms: "))
g=9.8
w=m*g
if m>=0:
    if w<100:
        print('Object Weight:',format(w,'.2f'),'newtons')
        print('The object is too light. It weighs less than 100 newtons.')
    elif w>500:
        print('Object Weight:',format(w,'.2f'),'newtons')
        print('The object is too heavy. It weighs more than 500 newtons.')
    else:
        print('Object Weight:',format(w,'.2f'))
else:
    print('Mass must be >= 0')
