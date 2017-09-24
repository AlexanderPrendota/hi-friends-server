## HiFriends chat

Server for Hi-Friends-client from (https://github.com/AlexandrPrendota/HiFriends-client).

## How To Get Started
- Copy docker-compose.yml to your server.
- Run:
```bash
 docker-compose up
```
If you use `hi-server-javascript version`: Chat will be available by this link:

* `http://localhost:3131`

## How to expose port

By default chat is working on 3131 port.
You can expose it to another port changing the config file 'docker-compose.yml'
```bash
    ports:
      - "your_port:3131"
```

## Also can run application 

uncomment data base configuration in DataSourceConfig.java
```java
@Bean
@Primary
@ConfigurationProperties(prefix = "spring.datasource")
public DataSource dataSource() {
  return DataSourceBuilder
          .create()
          .build(); 
}
```
also you must set database congiguration on `application.properties`

```
spring.datasource.url=jdbc:postgresql://host:port/db
spring.datasource.username=db_name
spring.datasource.password=pass
spring.jpa.hibernate.ddl-auto=update
server.port=your_server_port
```

then 

```bash
 mvn clean package
 java -jar demo-0.0.1-SNAPSHOT.
```
If you use `hi-server-javascript version`: Chat will be available by this link:

* `http://localhost:3131`

`hi-server-javascript version` GUI:

![2017-08-30 23 51 48](https://user-images.githubusercontent.com/10503748/29896533-0adf836c-8de6-11e7-891c-c402b385ce2a.png)

