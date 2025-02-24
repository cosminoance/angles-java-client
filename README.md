# angles-java-client


### Maven dependency
The angles java client has been published to the maven repository. 

Simply add the following dependency to your POM:
``` xml
<dependency>
  <groupId>com.github.angleshq</groupId>
  <artifactId>angles-java-client</artifactId>
  <version>1.0.0-BETA6</version>
</dependency>
```

Please also ensure you set the following system variables (e.g. as part of the maven-surefire-plugin):
```
<systemPropertyVariables>
    <angles.enabled>true</angles.enabled>
    <angles.url>http://127.0.0.1:3000</angles.url>
    <angles.runName>SampleRunName</angles.runName>
    <angles.team>SampleTeamName</angles.team>
    <angles.component>SampleComponent</angles.component>
    <angles.environment>SampleEnvironmentName</angles.environment>
</systemPropertyVariables>
```

### Testng
Extend your base/test class with:
```
AnglesTestngBaseTest
```

### JUnit5
Extend with the `AnglesJunit5Extension` extension class in order to have the Junit test result logged into Angles. 
Optional `Angles` handled assertions are available at test level by instantiating `AnglesJunit5Assert`.

```java
@ExtendWith(AnglesJUnit5Extension.class)
```

### Cucumber 2
- Extend the step definition file with: 
    ```java
    public class StepDef extends AnglesCucumber2Adapter
    ```
    - Include `AnglesCucumber2Adapter` as a plugin in the *run with* class if you run through `Maven`
        ```java
        @CucumberOptions(plugin = { 
              "com.github.angleshq.angles.listeners.cucumber.AnglesCucumber2Adapter"})   
        ```
- Inside the `step definition` class, you need to include an empty `@Before` method to allow instantiation of the `Angles` reporter.
    ```java
  @Before
    public static void initilizeAnglesAdapter() {
    }
    ```
  
### Log4j2 Appender
Add the appender to your log4j2 configuration file:
```
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="INFO" packages="com.github.angleshq.angles.listeners.log4j2.AnglesLog4j2LogAppender">
    <Appenders>
        <AnglesLog4j2LogAppender name="AnglesLog4j2LogAppender"/>
    </Appenders>
    <Loggers>
        <Root level="debug">
            <AppenderRef ref="AnglesLog4j2LogAppender"/>
        </Root>
    </Loggers>
</Configuration>
```
Please bear in mind that this appender standalone will not be able to push logs to your Angles Dashboard and requires this to be coupled with a test execution framework like JUnit5 or Testng. i.e. This plugin will not create new runs/builds.


### Using Assertions
In order to do assertions that are captured by the Angles dashboard, you can instantiate the `AnglesJUnit5Assert` at the test script level.

```java
AnglesJUnit5Assert doAssert = new AnglesJUnit5Assert();
```

>Example assertion usage:
```
doAssert.assertEquals(expected, actual, "info about assertion");
```

If you are using Testng and have already extended the relevant `AnglesTestngBaseTest` class, you will have access to the `doAssert` object. 

The Junit5 and TestNG assert objects wrap the respective Assert (Testng) or Assertions (JUnit5) methods.
