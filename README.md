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



### Testng Listener
Add the following to your base/test class:
```
@Listeners({AnglesTestngTestListener.class})
```
Please also ensure you set the following system variables:
```
<systemPropertyVariables>
    <angles.url>http://127.0.0.1:3000</angles.url>
    <angles.runName>SampleRunName</angles.runName>
    <angles.team>SampleTeamName</angles.team>
    <angles.component>SampleComponent</angles.component>
    <angles.environment>SampleEnvironmentName</angles.environment>
</systemPropertyVariables>
```

### JUnit5 Listener
Add the following to your base/test class:
```
@ExtendWith(AnglesJUnit5TestListener.class)
```
Please also ensure you set the following system variables:
```
<systemPropertyVariables>
    <angles.url>http://127.0.0.1:3000</angles.url>
    <angles.runName>SampleRunName</angles.runName>
    <angles.team>SampleTeamName</angles.team>
    <angles.component>SampleComponent</angles.component>
    <angles.environment>SampleEnvironmentName</angles.environment>
</systemPropertyVariables>
```