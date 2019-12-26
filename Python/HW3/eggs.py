#Aaron Zhang
#CS021 Green Group
#Lead user to input the number of eggs
#Calculate and display how many of them can be packed and how many eggs left
print('This program will determine the required number of 1-dozen egg cartons.')
x=int(input('How many eggs did you collect today? '))
y=(x//12)
z=x-y*12
if x>=0:
    print('We will pack your',x,'eggs in',y,'cartons')
    print('There will be',z,'eggs left over.')
else:
    print('Your value cannot be negative.')
    
