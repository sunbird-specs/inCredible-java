# inCredible-java

Placeholder for POJOs and utility functions based on the inCredible specification

## Modules
pojos
- All the pojos related to the spec

utils
- Utility functions for signing, verification

sample-inCredible
- Example code to demonstrate usage of pojos and utils above

## Installation
To build the pojos and utilities
> mvn clean install

Optionally, to build the sample
>cd sample-inCredible
>javac -cp ../pojos/target/pojos-1.0-SNAPSHOT.jar src/Main.java
