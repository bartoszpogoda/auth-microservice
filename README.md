# auth-service (WORK IN PROGRESS)

Base implementation of microservice designed to handle user accounts, authentication and authorization.

## Implemented pattern  

Service was designed to fit in architectures, where each request goes through some edge service (see API Gateway Pattern). In that case the request (in base implementation path and method) can be authorized against user permissions with remote call to auth-service and then forwarded or rejected based on response.

## Authentication model & flow
Todo 
## Authorization model & flow
Todo
## Permission model
Todo
## Integration examples
Todo
#### Zuul 1.x
Todo (sync)

#### Zuul 2.x
Todo (async)