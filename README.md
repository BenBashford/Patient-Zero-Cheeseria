# Patient Zero Cheeseria
POC of front&back end sections for Cheeseria application, users can view, add, update, and delete up to 5 cheeses, and use the calculator to determine the overall cost of a certain weight of any selected cheese.

# Prerequisites:
1. Project uses Java 17 & Maven 3.9.6, coded and compiled in Visual Studio Code 1.88.1. Spring-boot 3.2.5 was used to create skeleton project.
2. Ensure that localhost:3000 and localhost:8080 are not currently in use, as the project runs the frontend on the first port and the backend on the second. 
3. Ensure that Docker is installed and running to handle container building and deployment, Docker Desktop 4.29.0 was used for this project

# Installation Instructions:
1. Open project folder in Visual Studio Code or similar IDE
2. Open Docker Dekstop
2. Open Terminal
3. run mvn clean install
4. run docker-compose build
5. run docker-compose up

# Usage
With the server running, you can access the frontend of the application through http://localhost:3000/
The backend is accessible through http://localhost:8080/cheeses, here you can see all the cheese information currently stored in a session.
http://localhost:8080/swagger-ui/index.html can be used to access the OpenAPI documentation for the project

The program starts with no preset cheeses in memory. To create a new one, you can enter a name, price per kilo, color, and optionally an image url.
The url is not needed as a default image link is provided, but image url's can be gotten from https://www.cheese.com/
Up to 5 cheeses can be created at any one time, and they can be updated using the delete and update buttons on each cheese container and typing in the new parameters into the textbox.
For calculating price, use the dropdown menu to select a cheese from the available list, enter a weight in the box below and press calculate and a price will be provided.


# Testing
The testing file CheeseriaControllerTest is used to make sure the CRUD functionality of the project is working
This file is automatically run during mvn clean install, but if you wish to run it manually then you can run mvn test in the console.




