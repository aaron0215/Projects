#Aaron Zhang
#CS021 Green group
#This is a root funtion that will be used later.
#This function includes a dictionary, six sets and
#a test function without return value.
#Use readline to read the file and put each first
#element (state name) as key and put its following
#two elements as values.
#Add keys and values into set by using for loop.
#Use print_set function to check the data.
def make_elector_dictionary(name):
    new_dictionary = {}
    try:
        infile = open(name,'r')
        for line in infile:
            text1 = line
            text2 = infile.readline()
            text3 = infile.readline()
            text1 = text1.rstrip('\n')
            text2 = text2.rstrip('\n')
            text3 = text3.rstrip('\n')
            new_dictionary[text1] = text2,text3
        infile.close()
    except IOError:
        return 'The file cannot be found'
    else:
        return new_dictionary
def make_category_sets(states):
    s1 = set()
    s2 = set()
    s3 = set()
    s4 = set()
    s5 = set()
    s6 = set()
    try:
        for city in states:
            popu,vote = states[city]
            vote_rate = int(popu)/int(vote)
            if vote_rate < 250000:
                s1.add(city)
            elif vote_rate < 350000:
                s2.add(city)
            elif vote_rate < 450000:
                s3.add(city)
            elif vote_rate < 550000:
                s4.add(city)
            elif vote_rate < 650000:
                s5.add(city)
            elif vote_rate >= 650000:
                s6.add(city)
        return s1,s2,s3,s4,s5,s6
    except:
        return 'An error occured'
def print_set(set1,set2,set3,set4,set5,set6):
    for ch1 in set1:
        print(ch1)
    for ch2 in set2:
        print(ch2)
    for ch3 in set3:
        print(ch3)
    for ch4 in set4:
        print(ch4)
    for ch5 in set5:
        print(ch5)
    for ch6 in set6:
        print(ch6)
