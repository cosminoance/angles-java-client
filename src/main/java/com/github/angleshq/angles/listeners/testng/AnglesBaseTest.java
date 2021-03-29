package com.github.angleshq.angles.listeners.testng;

import com.github.angleshq.angles.AnglesReporter;
import com.github.angleshq.angles.exceptions.AnglesPropertyNotGivenException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class AnglesBaseTest  {

    protected AnglesReporter anglesReporter;
    protected String runName;
    protected String team;
    protected String component;
    protected String environment;

    protected void initialiseAnglesTestParameters() throws AnglesPropertyNotGivenException {
        anglesReporter = AnglesReporter.getInstance(getAnglesPropertyFromSystem("angles.url") + "/rest/api/v1.0/");
        runName = getAnglesPropertyFromSystem("angles.runName");
        team = getAnglesPropertyFromSystem("angles.team");
        component = getAnglesPropertyFromSystem("angles.component");
        environment = getAnglesPropertyFromSystem("angles.environment");
    }

    public AnglesBaseTest() {
        try {
            initialiseAnglesTestParameters();
            anglesReporter.startBuild(runName, environment, team, component);
        } catch (AnglesPropertyNotGivenException e) {
            System.out.println("Please ensure you provide all required system properties for Angles Dashboard.");
        }
    }

    @BeforeMethod
    public void anglesBeforeMethod(Method method) {
        anglesReporter.startTest(method.getClass().getSimpleName(), method.getName());
    }

    @AfterMethod
    public void anglesAfterMethod(Method method) {
        anglesReporter.saveTest();
    }

    public static String getAnglesPropertyFromSystem(String property) throws AnglesPropertyNotGivenException {
        String propertyValue = System.getProperty(property);
        if(isBlank(propertyValue)) {
            throw new AnglesPropertyNotGivenException("Detected that property [" + property + "] was not given. Please add this mandatory property as a system property");
        }
        return propertyValue;
    }

}
