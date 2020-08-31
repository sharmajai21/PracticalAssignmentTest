# Westpac Kiwisaver Retirment Calculator Test Automation
This repository contians browser automation tests for the Westpac Bank KiwiSaver Retirment Calculator

## Tools and Frameworks:
1. Development IDE IntelliJ - V2109.3.2(Community Edition)
2. Test Automation tools - Selenium Webdriver with Cucumber and Junit
3. Framework Stye - POM (Page object modal)
4. Maven as build tool

## Pre-requisite:
1. Maven - Development done with Apache Maven V3.6.3
2. Java  - Development done with Java 1.8.0_261
3. Preferred platform - Windows (Provision has been added for mac os but it is work in progress)

## Setup for execution
1. Install maven locally and add it your windows environment variable (test your installation using `mvn -version` command)
2. Install Java and add JAVA_HOME and add it to your windows environment variable (test your installation using 'java -version' comman)
3. Clone the repositort to your local machine
4 Execute mavan command `mvn -Dcucumber.options="--tags <tagename>" clean test` (Use @KiwisaverCalculator in place of tagename)
5. On successful execution this should generate `/target` folder under your local project repository
6. Check `\target\site\cucumber-reports\cucumber-html-reports` folder for cucumber reports

### Notes:
1. Code is written and tested on Windows10 machine
2. Tested with chrome browser (Framework has provision for Edge and Firefox but drivers needs to be added and testing needs to be done)
