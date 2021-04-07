# angles-java-client


### Maven dependency
The angles java client has been published to the maven repository. 

Simply add the following dependency to your POM:
``` xml
<dependency>
  <groupId>com.github.angleshq</groupId>
  <artifactId>angles-java-client</artifactId>
  <version>1.0.0-BETA4</version>
</dependency>
```

Please also ensure you set the following system variables (e.g. as part of the maven-surefire-plugin):
```
<systemPropertyVariables>
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
Extend your base/test class with:
```
AnglesJUnit5BaseTest
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
Example assertion usage:
```
doAssert.assertEquals("expected", "actual", "This is a message");
```

If you are using Testng or JUnit5 and have already extended the relevant BaseTest classes as detailed above, you will have access to the "doAssert" object. This object would have access to the respective Assert (Testng) or Assertions (JUnit5) methods.