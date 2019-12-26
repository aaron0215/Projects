#Aaron Zhang
#CS021 Green group
#Lead user to input random number
#Use accumulation to store the total number
#In main function, divide number of user into two sections
#Compare the numbers in each section and print result
import random
def main():
    standard_value = 21
    total_num_user=0
    total_num_comp=0
    start = get_response()
    p=True
    while p and start=='y' or start == 'Y':
        num_user,num_comp = roll_dice()
        total_num_user += num_user
        total_num_comp += num_comp
        print('Points:',total_num_user)
        if total_num_user >= standard_value:
            print("User's points:",total_num_user)
            print("Computer's points:",total_num_comp)
            if total_num_user == standard_value and total_num_comp != standard_value:
                print("User wins")
            elif total_num_user > standard_value and total_num_comp > standard_value:
                print("Tie Game!")
            elif total_num_user > standard_value and total_num_comp <= standard_value:
                print("Computer wins!")
            p=False
        else:
            start = get_response()
    if p and start == 'n' or start == 'N':
        print("User's points:",total_num_user)
        print("Computer's points:",total_num_comp)
        if total_num_user < standard_value and total_num_comp<= standard_value:
            if total_num_user > total_num_comp:
                print("User wins")
            elif total_num_user < total_num_comp:
                print("Computer wins")
            else:
                print("Tie Game!")
        elif total_num_user <= standard_value and total_num_comp > standard_value:
            print("User wins")
def get_response():
    start_game = input('Do you want to roll? ')
    return start_game
def roll_dice():
    minimun = 2
    maximum = 12
    num_user_input = random.randint(minimun,maximum)
    num_comp_input = random.randint(minimun,maximum)
    return num_user_input,num_comp_input
main()
