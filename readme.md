# **Simple Rest Web Service**

### Building and Running application
Build application: `mvn package`

Running tests: 'mvn test'

Running with maven: `mvn spring-boot:run`

Running with java: `java -jar target/global-rest-0.0.1-SNAPSHOT.jar`
   
#### Manual service validation
`
curl -i -X POST -H "Content-Type:application/json" -d '{ "id": 2, "username": "test", "email" : "test@test.com" }' http://localhost:8080/api/users/
curl -i http://localhost:8080/api/users/2
`

### Problem Statement
The intent of the problem is to create a simple web service that allows all basic RESTful operations.

###
Implementation: basic rest service application based on spring boot

Application exposes rest endpoint for CRUD operations:

Method | url | body | description
--- | --- | --- | --- 
GET | /api/users/{id} | | retreive user
POST | /api/users/ | User | create user
PUT | /api/users/ | User | update existing user
DELETE | /api/users/{id} | | delete user

#### Limitations: 
 - no security of any kind
 - validation
 - no persistence
 
On the up side I've got this super nice banner

