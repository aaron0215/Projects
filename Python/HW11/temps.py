#Aaron Zhang
#CS021 Green group
#This is a function to convert temperature
#by using GUI
#Import GUI function
import tkinter
#Divide content into three frames
#from left to right
#In each frame, there are two sections
#arranged from top to bottom
#Each section has own function
class TempCoverterGUI:
    def __init__(self):
        self.main_window = tkinter.Tk()
        self.num1_frame = tkinter.Frame(self.main_window)
        self.num2_frame = tkinter.Frame(self.main_window)
        self.num3_frame = tkinter.Frame(self.main_window)
        self.prompt1_label = tkinter.Label(self.num1_frame,\
                                           text='Enter the Celsius temperature:')
        self.prompt2_label = tkinter.Label(self.num2_frame,\
                                           text='Fahrenheit temperature:')
        self.calc_button = tkinter.Button(self.num3_frame,\
                                          text='Convert to Fahrenheit',\
                                          command = self.convert)
        self.prompt1_label.pack(side='top')
        self.prompt2_label.pack(side='top')
        self.calc_button.pack(side='top')
        self.cels_entry = tkinter.Entry(self.num1_frame,\
                                        width = 10)
        self.value = tkinter.StringVar()
        self.faher_label=tkinter.Label(self.num2_frame,\
                                       textvariable = self.value)
        self.quit_button = tkinter.Button(self.num3_frame,\
                                          text = 'Quit',\
                                          command = self.main_window.destroy)
        self.cels_entry.pack(side='top')
        self.faher_label.pack(side='top')
        self.quit_button.pack(side='top')
        self.num1_frame.pack(side='left')
        self.num2_frame.pack(side='left')
        self.num3_frame.pack(side='left')
        tkinter.mainloop()
    #Convert input into fahrenheit temp
    #Use try and except to except all
    #invalid value
    #Format value and return it
    def convert(self):
        num1 = 9/5
        num2 = 32
        try:
            celsius_temp = float(self.cels_entry.get())
            fahre_temp = num1*celsius_temp+num2
            result = format(fahre_temp,'.2f')
        except:
            self.value.set('Invalid input')
        else:
            self.value.set(result)
Temp_conv = TempCoverterGUI()
        
