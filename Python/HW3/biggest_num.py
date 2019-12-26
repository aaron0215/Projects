#Aaron Zhang
#CS021 Green group
#Lead user to input three integer values
#Use if statement to compare and display values in descending order
x=int(input('First number? '))
y=int(input('Second number? '))
z=int(input('Third number? '))
if x>=y>z or x>y>=z:
    print('Descending order: ',x,y,z)
elif x>=z>y or x>z>=y:
    print('Descending order: ',x,z,y)
elif y>=x>z or y>x>=z:
    print('Descending order: ',y,x,z)
elif y>=z>x or y>z>=x:
    print('Descending order: ',y,z,x)
elif z>=x>y or z>x>=y:
    print('Descending order: ',z,x,y)
elif z>=y>x or z>y>=x:
    print('Descending order: ',z,y,x)
else:
    print('Descending order: ',x,y,z)
