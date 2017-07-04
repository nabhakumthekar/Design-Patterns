This assignment takes input from file (in XML or similar format) deserializes it and using java reflection again serializes it. 

For example:
input:
<fqn:genericSerDeser.util.First>
<type=boolean, var=BooleanValue, value=true>
<type=byte, var=ByteValue, value=-17>
<type=char, var=CharValue, value=m>
<type=double, var=DoubleValue, value=9052486128430512128.000000>
<type=float, var=FloatValue, value=0.3268135289021513>
<type=int, var=IntValue, value=761788968>
<type=long, var=LongValue, value=-4568207186537638084>
<type=short, var=ShortValue, value=29291>
<type=String, var=StringValue, value=SmjOV3X7hXv9xz0zRv>
</fqn:genericSerDeser.util.First>

output:
<fqn:genericSerDeser.util.First>
<type=boolean, var=BooleanValue, value=true>
<type=byte, var=ByteValue, value=-17>
<type=char, var=CharValue, value=m>
<type=double, var=DoubleValue, value=9.0524861284305121E18>
<type=float, var=FloatValue, value=0.32681352>
<type=int, var=IntValue, value=761788968>
<type=long, var=LongValue, value=-4568207186537638084>
<type=short, var=ShortValue, value=29291>
<type=String, var=StringValue, value=SmjOV3X7hXv9xz0zRv>
</fqn:genericSerDeser.util.First>


Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code

ant -buildfile src/build.xml run -Darg0=inputFile.txt -Darg1=outputFile.txt -Darg2=DEBUG_VALUE (0,1,2 etc)
