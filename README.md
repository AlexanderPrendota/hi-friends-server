## HiFriends chat

## How To Get Started
- Copy docker-compose.yml to your server.
- Run:
```bash
 docker-compose up
```
Chat will be available by this link:

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

then 

```bash
 mvn clean install
 java -jar demo-0.0.1-SNAPSHOT.
```
Chat will be available by this link:

* `http://localhost:3131`

![2017-08-30 23 51 48](https://user-images.githubusercontent.com/10503748/29896533-0adf836c-8de6-11e7-891c-c402b385ce2a.png)

