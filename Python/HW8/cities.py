#Aaron Zhang
#CS021 Green group
#This is a function to determine
#the belonging state of city that
#users input.
#Use if/else function to make sure
#the lists are not empty and to
#determint which list(state)
#the input(city) belongs to.
def main():
    city_name = input("City name (type 'q' to quit): ")    #Input section
    while city_name != 'q':     #Search in each file
        list1 = file2list('vt_municipalities.txt')
        index = 0
        if index < len(list1):
            if city_name in list1:
                city_in_vt = True
            else:
                city_in_vt = False
        list2 = file2list('nh_municipalities.txt')
        if index < len(list2):
            if city_name in list2:
                city_in_nh = True
            else:
                city_in_nh = False
        if city_in_vt and city_in_nh == False: #Show results
            print(city_name,'is in Vermont.')
        elif city_in_nh and city_in_vt == False:
            print(city_name,'is in New Hampshire.')
        elif city_in_nh and city_in_vt:
            print(city_name,'is in Vermont and New Hampshire.')
        else:
            print('Neither VT nor NH has a city by that name')
        city_name = input("City name (type 'q' to quit): ")
def file2list(name):     #Search section
    try:
        infile = open(name,'r')
        cities = infile.readlines()
        index = 0
        while index < len(cities):
            cities[index] = cities[index].rstrip('\n')
            index += 1
        infile.close()
    except IOError:      #Except the file is not existed
        print('The file',name,'cannot be founded.')
        empty_list = []
        return empty_list
    else:
        return cities
main()
