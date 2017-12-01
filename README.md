# Football Scoring Dashboard

## Generate the distribution

To generate the distributions, go under the dashboard/ directory
and run the command: "./gradlew distZip"

## Start the application

Go inside the ./build/distributions/ directory.

Take one of the two files, either dashboard.tar or dashboard.zip and uncompress it.

The decompressed folder should contain a bin/ and a lib/ directory. 

Go to the bin/ directory, and chose, depending on your platform, the shell script 
to execute.

Enjoy! 

## Known limitations:

1. The application has been tested with Latin languages in mind, it may not work properly with other kind of languages. 

2. Two teams can not have the same name, for obvious reasons. 

3. For some reason, using the command "./gradlew runApp" works but there is 
still Gradle console garbage present at the prompt. So my prefered method to 
start the application is through the distributions (see above).

Sébastien Hamel
April 13, 2016 
