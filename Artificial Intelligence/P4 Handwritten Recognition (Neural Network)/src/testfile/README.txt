This folder contains the following files:

------------------------------------------------------------------

testcases.txt > Every line in this file contains an example test case. The .txt file
specified at the end of a line is the output for that test case. For instance, 
the first line in testcases.txt is:

Sample test case #1: java DigitClassifier 5 0.01 100 train1.txt test1.txt : sample_output1.txt

This means that if you run "java DigitClassifier 5 0.01 100 train1.txt test1.txt", your
program's output should be the same as in sample_output1.txt

------------------------------------------------------------------

sample_output1.txt, sample_output2.txt, sample_output3.txt > These are the outputs
that your code should be producing when the corresponding test is run, as explained
above.

------------------------------------------------------------------

train1.txt and test1.txt > These are example training and test datasets that you want to
use when running your program and debugging your code. These two files must be
in the same folder as your .java files in order to run your code properly.

------------------------------------------------------------------

view.py > This file is to view what an image in the dataset looks like. You
don't have to use this at all as it is entirely optional. In other words,
it has nothing to do with your code and how you are going to be graded.

To visualize the image at index 10 in the train1.txt dataset, for example, you can
run "python view.py --file=train1.txt --index=10" in the command line (without the
double quotes). It should output something like this:

      ....      
     ....       
     ..         
   ....         
  ...           
 ....           
 ...            
...             
...             
... ..........  
... ..      ... 
...          ...
....         ...
 ....       ... 
  ............. 
    .........   

The digit is: 6
