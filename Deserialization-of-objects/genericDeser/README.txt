This assignment deserializes objects from given input file using java reflection. It also gives count for first & second unique as well as total objects input:

<fqn:genericDeser.util.Second>
<type=short, var=ShortValue, value=1>
<type=short, var=ShortValue2, value=22>
<type=long, var=LongValue, value=333>
<type=long, var=LongValue2, value=4444>
<type=double, var=DoubleValue, value=5555.5>
<type=double, var=DoubleValue2, value=6666.66>
<type=String, var=StringValue, value=hello>
</fqn:genericDeser.util.Second>
<fqn:genericDeser.util.First>
<type=short, var=ShortValue, value=18724>
<type=double, var=DoubleValue, value=5933.647962840596>
<type=boolean, var=BooleanValue, value=false>
<type=long, var=LongValue, value=5893089334009992408>
<type=char, var=CharValue, value=*>
<type=float, var=FloatValue, value=806.1457>
<type=String, var=StringValue, value=7x3w"3lv\%L@:E}?ut1>
<type=int, var=IntValue, value=-1496394512>
</fqn:genericDeser.util.First>


output:

Number of unique First objects: 1
Total Number of First objects: 1
Number of unique Second objects: 1
Total Number of Second objects: 1

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

ant -buildfile src/build.xml run -Darg0=inputFile.txt -Darg1=0