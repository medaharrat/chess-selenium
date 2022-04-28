# Selenium tests for Chess.com
Selenium automation tests for chess.com, using Google Chrome and WebDriver.
This serves as a basic implementation of a automation testing for the website.
Tests covered are the following:
* A user should be able to log in with valid credentials
* A user should not be able to log in with invalid credentials
* A logged in user should be able to log out
* A logged in user should be able to edit his profile information
* A logged in user should be able to start a game with a computer
### Requirements
* [Java](https://www.oracle.com/java/technologies/downloads/)
* [Gradle](https://gradle.org/install/)
* [Google Chrome](https://www.google.com/chrome)
### Run tests
`gradle test -info`
