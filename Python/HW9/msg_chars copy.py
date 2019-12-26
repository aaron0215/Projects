def main():
    in_string = input('Enter a string: ')
    test_string = in_string.upper()
    letter_list = []
    freq_list = []
    for ch in test_string:
        if ch not in letter_list:
            letter_list.append(ch)
            freq_list.append(test_string.count(ch))
    index = 0
    while index < len(letter_list):
        print(letter_list[index],freq_list[index])
        index += 1
main()
