#Aaron Zhang
#CS021 Green group
#This is a function that shows population
#and electoral votes numbers in all states.
#Call the myfns function to input the dictonary.
#Use try and except function to except the wrong
#file name. Use while and for loop to make program
#repeat as user requires.
def main():
    import myfns
    try:
        states = myfns.make_elector_dictionary('electoralvotes.txt')
    except IOError:
        print('IOError occured')
    else:
        total_votes = 0
        try:
            for key in states:
                popn,votes = states[key]
                total_votes += int(votes)
            print('The total number of electoral votes is',total_votes)
            print('The total number of electoral votes needed to win is 270'+'\n')
            int_state = input('Choose a state or just hit Enter when done: ')
            while int_state != '':
                if int_state in states:
                    popn,votes = states[int_state]
                    print('Population:',popn+'\t'+'Electoral votes:',votes+'\n')
                    int_state = input('Choose a state or just hit Enter when done: ')
                else:
                    print('Try again. This is not a valid state name'+'\n')
                    int_state = input('Choose a state or just hit Enter when done: ')
            print()
            print('Thanks for using my program')
        except:
            print('An error occured')
main()
