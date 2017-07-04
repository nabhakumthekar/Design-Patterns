
This assignment is a demonstration of detecting a cycle in spreadsheet.

Given a sequence like below

input:
a3=12+56
a1=a3+45
b1=a1+77
b2=a1+b1
a1=44+10
b3=b2+a1
c3=a1+b3
a2=b2+c3
c2=11+12
c1=11+13
a2=c1+19
a1=b1+84
b1=a1+33

it calculates sum of entries and cycles detected

output:
Cycle detected for record a1=b1+84
The sum of all cell values is:884
-----------------------------------------------------------------------


Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code

ant -buildfile src/build.xml run -Darg0=inputFile1.txt -Darg1=outputFile.txt
-Darg2=DEBUG_VALUE