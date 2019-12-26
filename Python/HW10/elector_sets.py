#Aaron Zhang
#CS021 Green group
#This is function that show the situation of
#votes in different states.
#Call the myfns function and input the sets.
#Lead user to input valid number and state
#number to search information that they want.
#Use while, for and if in loop to locate data.
def main():
    import myfns
    try:
        states = myfns.make_elector_dictionary('electoralvotes.txt')
        s1,s2,s3,s4,s5,s6 = myfns.make_category_sets(states)
        lt250k = ''
        lt350k = ''
        lt450k = ''
        lt550k = ''
        lt650k = ''
        gt650k = ''
        for ch1 in s1:
            lt250k += ch1+'\n'
        for ch2 in s2:
            lt350k += ch2+'\n'
        for ch3 in s3:
            lt450k += ch3+'\n'
        for ch4 in s4:
            lt550k += ch4+'\n'
        for ch5 in s5:
            lt650k += ch5+'\n'
        for ch6 in s6:
            gt650k += ch6+'\n'
        ratio_list = ['LT250K','LT350K','LT450K','LT550K','LT650K','GT650K']
        count = 1
        for cate in ratio_list:
            print(count,': ',cate, sep='')
            count += 1
    except:
        print(states)
    else:
        print()
        try:
            category = input('Choose category 1-6, or just hit Enter when done: ')
            while category != '':
                print()
                if category == '1':
                    print(lt250k)
                elif category == '2':
                    print(lt350k)
                elif category == '3':
                    print(lt450k)
                elif category == '4':
                    print(lt550k)
                elif category == '5':
                    print(lt650k)
                elif category == '6':
                    print(gt650k)
                else:
                    print(category,'is not a valid category number')
                category = input('Choose category 1-6, or just hit Enter when done: ')
        except:
            print('An error happened')
        print()
        print('Now check your state(s)'+'\n')
        try:
            name = input('Name a state or just hit Enter to quit: ')
            while name != '':
                if name in lt250k:
                    print(name,'has one vote per 250,000 people'+'\n')
                elif name in lt350k:
                    print(name,'has one vote per 350,000 people'+'\n')
                elif name in lt450k:
                    print(name,'has one vote per 450,000 people'+'\n')
                elif name in lt550k:
                    print(name,'has one vote per 550,000 people'+'\n')
                elif name in lt650k:
                    print(name,'has one vote per 650,000 people'+'\n')
                elif name in gt650k:
                    print(name,'has one vote per 650,000 people'+'\n')
                else:
                    print('Try again. This is not a valid state.'+'\n')
                name = input('Name a state or just hit Enter to quit: ')
        except:
            print('An error occured')
main()
    
