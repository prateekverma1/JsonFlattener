# JsonFlattenerMDB
Takes the json input as command line and returns the flatened json

Code is available here: MongoDBJsonParser/src/main/java/com/flatten/json/

Test files are available here: test_files/

Screen shot of the sample input and output is in the file screenshot_output.PNG

To run the program follow the instructions below:

Go to project git folder JsonFlattenerMDB 

Run the following git command:  
cat test_files/input.json | java -cp MongoDBJsonParser/target/MongoDBJsonParser-1.0-SNAPSHOT-jar-with-dependencies.jar com.flatten.json.JsonFlatten
