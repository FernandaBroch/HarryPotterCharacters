# HarryPotterCharacters - Backend Challange

The challenge consistes in an API Rest CRUD to persist a Harry Potter character. There is a check if the code of the House informed exists. This check consumes an external API. 

## To run this API in your localhost:

### Create a Mysql database in a docker container

`docker run -p 3306:3306 -d --name DatabaseName -e MYSQL_ROOT_PASSWORD=DatabasePassword mysql/mysql-server`

Then log in the mysql database and create a new user, because you will not be able to use root user to access your database from the application.

```
mysql -u root -p DatabaseName;
CREATE USER 'username'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'username'@'%';
```

### Clone the reposiory and run

After cloning this [repository](https://github.com/FernandaBroch/HarryPotterCharacters.git), you have to create 2 files in the main/resources folder.

1. **application.properties**
  - This file contains the information about your database connection\
  Content:

```
spring.datasource.url = jdbc:mysql://localhost:3306/DatabaseName?allowPublicKeyRetrieval=true&useSSL=false

# Username and password
spring.datasource.username = username
spring.datasource.password = password
spring.jpa.hibernate.ddl-auto = update
server.port=9000`
```

2. **apikey.properties**
  - This file contains the apiKey to access the external API that validates the house's code.\
  Content:

`APP_KEY = xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx`

---

## API Documentation

The application was made using SWAGGER, therefore the documentation with the parameters, responses, endpoints was auto-generated from the API definition.
After running the application, you can find it in the link below:

http://localhost:9000/swagger-ui.html#/harry-potter-character-controller


