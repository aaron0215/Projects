def main():
    in_string = input('Enter a string: ')
    test_string = in_string.upper()
    letter_list = []
    freq_list = []
    for ch in test_string:
        if ch not in letter_list:
            letter_list.append(ch)
            freq_list.append(1)
        else:
            index = letter_list.index(ch)
            freq_list[index] = freq_list[index] + 1
    print('Letter','Freq')
    index1 = 0
    while index1 < len(letter_list):
        print(' ',letter_list[index1],format(freq_list[index1],'6.0f'))
        index1 += 1
main()
