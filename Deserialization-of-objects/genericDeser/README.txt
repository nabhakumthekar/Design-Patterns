Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code

ant -buildfile src/build.xml run -Darg0=inputFile.txt -Darg1=0


Example:
To read from src give
ant -buildfile src/build.xml run -Darg0=src/input-1.txt -Darg1=0

To read from one folder above 
ant -buildfile src/build.xml run -Darg0=input-1.txt -Darg1=0


The files are read from here:
	kumthekar_nabha_assignment_4/genericDeser/src/input-1.txt

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense.â€

[Date: ] -- 04/30/2017

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)


1.HashMap.
- To store boxed primitives
- Insert:: O(1).
- Contains:: O(1).

2. HashSet
- To add unique instances of First & Second objects.
- Sets allow only unique entries.
- Insert:: O(n).
- Contains:: O(1).

3. ArrayList.
- To add created objects of First & Second type.
- ArrayList allows duplicate objects.
- Add:: O(1).
- Contains::O(n)
- Get:: O(1)
- Remove:: O(n)

4. ArrayList
- To store Result.
- Insert:: O(1).
- Contains:: O(1).

-----------------------------------------------------------------------
