# Promusic
Music store application using Angular and Spring technologies
## Promusic Frontend
Angular 11 + Bootstrap 4
## Promusic Backend
Spring Boot + OAuth + PostgreSQL + FlywayDB

## Start applications
* Install the docker tool.

* Duplicate the ".env.example" file and rename it to .env

* Now, it is needed to create a network between frontend and backend projects, but once it's done it won't be needed to do it again:
```
docker network create promusic
```
### Start Promusic Frontend
* After its launch, go to the "promusic-frontend" project directory and run the www container with this command:
```
docker-compose up --build -d
```
### Start Promusic Backend
* After running it, go to the "promusic-backend" project directory and run the backend containers and temporary database with this command:
```
docker-compose up --build -d
```
### Application
The application will be visible at these addresses:

Frontend:
```
http://localhost
```
Backend:
```
http://localhost:8080
```