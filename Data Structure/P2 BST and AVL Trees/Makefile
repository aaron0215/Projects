.PHONY =  junit5 junit4

junit5: BST.java BSTTest.java AVL.java AVLTest.java
	javac -cp .:./classes/:junit-platform-console-standalone-1.3.2.jar *.java

bst: junit5
	java -jar junit-platform-console-standalone-1.3.2.jar --class-path .:./classes/ -c BSTTest

avl: junit5
	java -jar junit-platform-console-standalone-1.3.2.jar --class-path .:./classes/ -c AVLTest

all: junit5
	java -jar junit-platform-console-standalone-1.3.2.jar --class-path .:./classes/ -p ""

junit4:
	javac -cp .:./classes/:junit4-12.jar *.java
	java cp .:./classes/:junit4-12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore TestDS_My
