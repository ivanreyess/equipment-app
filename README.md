![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white) ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white) ![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black) ![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E) ![React](https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB) ![React Router](https://img.shields.io/badge/React_Router-CA4245?style=for-the-badge&logo=react-router&logoColor=white) ![NodeJS](https://img.shields.io/badge/node.js-6DA55F?style=for-the-badge&logo=node.js&logoColor=white)


# Equipment web app

Equipment based on *Spring boot*

## Directory Structure
```bash
├── src
├── front-end
├── Dockerfile
├── build.sh
├── docker-compose.yml
```

#### src :satellite:
This directory the source code for the api.

#### front-end :satellite:
This directory the source code for the api.

### Dockerfile :whale:
Dockerfile to build a docker image.

### docker-compose.yml :whale:
docker-compose file to run all services.

### build.sh :building_construction:
Script for building the projects and run them on containers.

### Requirements :white_check_mark:

* jdk 17
* docker
* docker-compose
* node js

### Running  the application :star:

To run the application  you can manually build all the projects and run them individually or executing the scripts provided inside this project there is a build.sh file, do not forget to make them executable in order to run it.


#### Manually

To run each service separately, starting with the backend, run the following command.

```
./mvnw spring-boot:run
```

To run the front end app, you need to install all its dependencies, make sur you are on the front-end directory.

```
cd /front-end
npm install
```
Then to run the web app, execute the following

```
npm start
```

#### Using scripts

Make the scripts executable

```
chmod +x build.sh
chmod +x start.sh
chmod +x stop.sh
```

* build.sh: Builds all the projects and then deploys them using docker-compose.
* stop.sh: Stop all docker containers with one script.
* start.sh: Start all containers previously built.

To execute the desired script you can copy and paste on your terminal.

```
./build.sh
./start.sh
./stop.sh
```

### Consuming APIs :star:

* Swagger doc: http://localhost:8081/swagger-ui/index.html
* Job entrypoint: http://localhost:8081/api/jobs/
* Equipment entrypoint: http://localhost:8081/api/equipments/


### Accessing web app :star:

*   http://localhost:3000/equipments/