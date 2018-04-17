#!/bin/sh

# run a build if target directory doesn't exist
if [ ! -e 'target' ]
then
	mvn clean package
fi

# launch the runnable jar
java -jar target/gift-ideas-1.1.0-jar-with-dependencies.jar
