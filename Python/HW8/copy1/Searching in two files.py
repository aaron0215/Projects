#该程序可以两个文件中搜寻相同项
def main():
    目标 = input(" 用户提示语句（以q退出为例）")    #用户输入
    while target != 'q':     #while循环开始
        list1 = file2list('文件一') #在目标文件中查找
        index = 0
        if index < len(list1):
            if 目标 in list1:  #搜索结果
                变量1 = True
            else:
                变量1 = False
        list2 = file2list('文件二') #目标文件2
        if index < len(list2):
            if 目标 in list2:  #搜索结果
                变量2 = True
            else:
                变量2 = False
        if 变量1 and 变量2 == False: #显示匹配结果
            print(目标,'在文件一中')
        elif 变量2 and 变量1 == False:
            print(目标,'在文件二中')
        elif 变量2 and 变量1:
            print(目标,'存在于文件一和文件二中')
        else:
            print('文件一和文件二都没有该目标')
        目标 = input("用户提示语句（以q退出为例）")
def file2list(name):     #搜索函数
    try:
        文件 = open(name,'r')
        变量3 = infile.readlines()
        index = 0
        while index < len(cities):  #循环控制
            变量3[index] = 变量3[index].rstrip('\n')
            index += 1
        文件.close()
    except IOError:      #Except文件不存在的潜在错误
        print('文件',name,'没有找到')
        empty_list = []
        return empty_list
    else:
        return cities
main()
