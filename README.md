# Gift Ideas

A simple command line application to save and retrieve gift ideas.

## Quickstart

You will need [Maven](http://maven.apache.org/) and the Java development kit ([JDK](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html#javasejdk)) installed on your system to build this project.

Run the start up script

```
./start.sh
```

to launch the command line, or launch the runnable jar directly with

```
java -jar target/gift-ideas-1.1.0-SNAPSHOT-jar-with-dependencies.jar
```

Type `?help` at the shell to get help, or `?list` to get a list of commands.

## Saving Gift Ideas

Save a gift idea for someone like this

```
gift-ideas> add Mom 'flower vase'
```

and retrieve gift ideas with

```
gift-ideas> get Mom
```

By default, gift ideas will be saved as json in a file called gift-ideas.json at the root of this project. You can use a different file by passing it on the command line like

```
java -jar target/gift-ideas-1.0.0-SNAPSHOT-jar-with-dependencies.jar /Users/me/my-personal-gifts.json
```
