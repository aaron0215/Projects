import tkinter
class TempCoverterGUI:
    def __init__(self):
        self.main_window = tkinter.Tk()
        self.top_frame = tkinter.Frame(self.main_window)
        self.bottom_frame = tkinter.Frame(self.main_window)
        self.prompt1_label = tkinter.Label(self.top_frame,\
                                           text='Enter the Celsius temperature')
        self.prompt2_label = tkinter.Label(self.top_frame,\
                                           text='Fahrenheit temperature')
        self.calc_button = tkinter.Button(self.top_frame,\
                                          text='Convert to Fahrenheit',\
                                          command = self.convert)
        self.prompt1_label.pack(side='left')
        self.prompt2_label.pack(side='left')
        self.calc_button.pack(side='left')
        self.cels_entry = tkinter.Entry(self.bottom_frame,\
                                        width = 10)
        self.value = tkinter.StringVar()
        self.faher_label=tkinter.Label(self.bottom_frame,\
                                       textvariable = self.value)
        self.quit_button = tkinter.Button(self.bottom_frame,\
                                          text = 'Quit',\
                                          command = self.main_window.destroy)
        self.cels_entry.pack(side='left')
        self.faher_label.pack(side='left')
        self.quit_button.pack(side='left')
        self.top_frame.pack()
        self.bottom_frame.pack()
        tkinter.mainloop()
    def convert(self):
        num1 = 9/5
        num2 = 32
        try:
            celsius_temp = float(self.cels_entry.get())
            fahre_temp = num1*celsius_temp+num2
            result = format(fahre_temp,'.1f')
        except:
            self.value.set('Invalid input')
        else:
            self.value.set(result)
Temp_conv = TempCoverterGUI()
        
        
