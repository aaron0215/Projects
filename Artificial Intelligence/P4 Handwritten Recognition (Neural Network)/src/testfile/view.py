#!/usr/bin/python

# This tool can help you visualize the digit.
# Sample Usage: python view.py --file=train1.txt --index=10

import sys
from math import sqrt
from optparse import OptionParser
parser = OptionParser()
parser.add_option("-f", "--file", dest="file", help="the file name of the dataset")
parser.add_option("-i", "--index", dest="index", help="the index of the pic", type=int)
(options, args) = parser.parse_args()

f=open(options.file, 'r')
f.readline() # comment
num_label= len(f.readline()[2:].split())
num_attr = int(f.readline()[2:])
dim_size = int(sqrt(num_attr))
for i in range(0, options.index):
	f.readline() # skip the previous lines
pixels = f.readline().rstrip().split(' ')
for i in range(0, dim_size):
	for j in range(0, dim_size):
		if float(pixels[i*dim_size+j]) > 0.5:
			sys.stdout.write('.')
		else:
			sys.stdout.write(' ')
	sys.stdout.write('\n')
label = 0
for i in range(0, num_label):
	if float(pixels[num_attr+i]) > 0.5:
		label = i
index_digit_map = {0:6, 1:8, 2:9}
sys.stdout.write('The digit is: ' + str(index_digit_map[label]) + '\n')
