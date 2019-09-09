# Jobsity Challenge

This project is a challenge for jobsity that reads a file with bowling scores and prints the board with the player scores.

## Getting Started

This instruction will guide you to install the application and run it.

### Prerequisites

You need to have maven and java 8 installed.

### Installing

mvn clean install

At the root directory there is a file named 'jobsity-bowling-moves.txt' that you can use as an example.

You can try this application by executing the following line with the path of the file that you want to process.

```
java -cp target/challenge-1.0.1-SNAPSHOT.jar com.jobsity.challenge.Application jobsity-bowling-moves.txt
```

## Releases

- 1.0.0: 

        - Initial release.

- 1.0.1:

        - Bug fixing: Fixing missing tab when a frame was a strike.
        
        - Adding missing unit test.

## Authors

* **Fernando Beltrachini**
