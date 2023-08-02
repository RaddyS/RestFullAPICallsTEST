Table of Contents:
1. Installation
2. Usage
3. License

##Installation:

To get started, follow these steps:

Copy the project to your desired directory.
Open the project using an IDE, text editor, or command prompt for interaction, as the project does not have a CLI or UI yet.

##Usage:

The main functionalities of the project include:

1. Adding Test Cases using DSL:

You can add test cases using the Domain-Specific Language (DSL).
These test cases are converted into automated tests to check if the API calls for GET, POST, PUT, and DELETE endpoints are functioning as expected.

2. Performing Tests with CSV Data:

The project supports testing with user data in the form of CSV files.
For POST operations, use the POST.CSV file, and for PUT operations, use the PUT.CSV file.
Place the CSV files in the ResourcesTest directory.

3. Generating Test Reports:

The project provides a test report for the executed test cases.
- testReport: Directory to view test report for the excuted test 

## Project Structure:

1. The project has been structured for better design with the introduction of the following packages:

- Pojo: Contains Plain Old Java Objects (POJO) to model API data.
- Util: Holds utility classes to assist with various functionalities.
- DAO: Contains Data Access Objects (DAO) to manage data interactions.
  
2. For specific tasks, refer to the directories mentioned below:

- Feature: Add DSL scenarios in this directory.
- StepDefinitions: Add test coverage code in this directory.
- ResourcesTest: Add CSV data files for testing here.
- configuration: Add API end point and access token
- runner: Select the feature files to run and and run what is needed

##License:
- You are free to use and copy the project for valid and beneficial purposes. The ownership of the work remains with the author.
