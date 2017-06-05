Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code

ant -buildfile src/build.xml run -Darg0=inputFile1.txt -Darg1=inputFile2.txt
-Darg2=output.txt -Darg3=NUM_THREADS -Darg4=DEBUG_VALUE