1.Preparation

- Mysql Server 5.7.x.x
- JDK 1.8
- Maven 3.x

2.Setup<br>
- Open pom.xml file and change database server information that depend on your system.
  
    + database.host
    + database.user
    + database.password
    + scheme.name<br>
    
- Move to the project folder and run mvn command to generate database: <code>mvn flyway:migrate</code>
- Then, run mvn command to build project <code>mvn clean install </code>

3.Run standalone
- Move to <code>target folder </code> and run <code>demo.jar</code> by command: <code>java - jar demo.jar </code><br>
- Use <code>demo.postman_collection.json</code> for testing
- At first, run <code>oauth/token endpoint</code> to get token and then use the token to test other APIs.
- Account to login: <code>demo/123456</code><br>

4.Run debug mode
- Run debug mode by command: <code>java -Xdebug -Xrunjdwp:transport=dt_socket,address=8080,server=y,suspend=n -jar demo.jar </code>
- Next, use any IDE(Eclipse, STS, Intellij ) to remote debug.
