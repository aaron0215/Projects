#Aaron Zhang
#CS021 Green group
#This is a function to calculate MPG
#by using GUI
#Import GUI function
import tkinter
#Divide content into four frames
#Each frame has own function
class MPGConverterGUI:
    def __init__(self):
        self.main_window = tkinter.Tk()
        self.gallons_frame = tkinter.Frame(self.main_window)
        self.miles_frame = tkinter.Frame(self.main_window)
        self.display_frame = tkinter.Frame(self.main_window)
        self.bottom_frame = tkinter.Frame(self.main_window)
        self.prompt1_label = tkinter.Label(self.gallons_frame,\
                                          text = 'Enter the number of gallons:')
        self.gallons_entry = tkinter.Entry(self.gallons_frame,\
                                          width = 10)
        self.prompt2_label = tkinter.Label(self.miles_frame,\
                                           text = 'Enter the number of miles:')
        self.miles_entry = tkinter.Entry(self.miles_frame,\
                                         width = 10)
        self.prompt1_label.pack(side = 'left')
        self.gallons_entry.pack(side = 'left')
        self.prompt2_label.pack(side = 'left')
        self.miles_entry.pack(side = 'left')
        self.display_label = tkinter.Label(self.display_frame,\
                                           text = 'Miles Per Gallon = ')
        self.value = tkinter.StringVar()
        self.mpg_label = tkinter.Label(self.display_frame,\
                                       textvariable = self.value)
        self.display_label.pack(side = 'left')
        self.mpg_label.pack(side = 'left')
        self.calc_button = tkinter.Button(self.bottom_frame,\
                                          text = 'Calculate MPG',\
                                          command = self.convert)
        self.quit_button = tkinter.Button(self.bottom_frame,\
                                          text = 'Quit',\
                                          command = self.main_window.destroy)
        self.calc_button.pack(side = 'left')
        self.quit_button.pack(side = 'left')
        self.gallons_frame.pack()
        self.miles_frame.pack()
        self.display_frame.pack()
        self.bottom_frame.pack()
        tkinter.mainloop()
    #Convert input into MPG
    #Use try and except to except all
    #invalid value
    #Format value and return it
    def convert(self):
        try:
            gallons = float(self.gallons_entry.get())
            miles = float(self.miles_entry.get())
            mpg = miles / gallons
            result = format(mpg,'.2f')
        except:
            self.value.set('Invalid input')
        else:
            self.value.set(result)
MPG_conv = MPGConverterGUI()
