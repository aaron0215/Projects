#Aaron Zhang
#CS021 Green Group
#Use eval to make value of radius switch from string into number
#Give pi a constant value 3.14
#Use formula to calculate diameter, circumference, surface area and volume
#Print all results in different sentences
r=eval(input('Enter the radius of sphere: '))
pi=3.14
d=2*r
c=pi*r*2
s=4*pi*r**2
v=pi*r**3*4/3
print('Diameter: ',format(d,'.1f'))
print('Circumference: ',format(c,'.1f'))
print('Surface area: ',format(s,'.1f'))
print('Volume: ',format(v,'.1f'))
