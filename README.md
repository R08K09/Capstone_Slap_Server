
# The Slap (Server-side)

## **TABLE OF CONTENTS** 📖

> 1. Project Overview
> 2. Diagrams
> 3. Tech Stack
> 4. Set-Up Instructions
> 5. RESTful Route Endpoints
> 6. Future Developments
> 6. Collaborators


### **_1. Project Overview_**
As part of our capstone project, our team created an API and the UI for the app in 10 days. Our group decided to build on the nostalgic social media platform 'TheSlap.com' as seen on the show Victorious. The Slap is a modern social media platform reminiscent of MySpace, designed with the user in mind to share their thoughts and feelings with the world.

### **_1. Diagrams_**

#### UML diagram

#### ERD diagram

### **_1. Tech Stack**

Tech stack for the back-end:

* Java `version: 17`
* Spring Boot `version: 3.0.6`
* PostgreSQL 
* Maven

Dependencies: `Lombok`, `Spring Data JPA`, `Spring Web`, `Spring Boot DevTools`, `PostgreSQL Driver`

## Set-Up Instructions:

Ensure the following is installed on your device:

* Intellij IDEA (running with JDK 17)
* PostgreSQL
* Postman
* Postico (optional - to visualise the data)

1. Clone the repository from GitHub. Scroll to the top of this page and click on the green 'Code' button. Ensure SSH is selected and copy the link provided. In your terminal, `git clone` the SSH key.

2. Create a local postgresql database called `the_slap_db`. In the terminal run: `createdb the_slap_db`

3. Via Intellij IDEA, run the `Capstone_Slap_Server` and make sure the API is running on port 8080.

To ensure the endpoints are working as expected, you can test them using Postman. All endpoints are available below in section 5 'RESTful Route Endpoints'.





