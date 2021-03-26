package com.github.angleshq.angles.listeners;

import com.github.angleshq.angles.AnglesReporter;
import com.github.angleshq.angles.exceptions.AnglesPropertyNotGivenException;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class AbstractAnglesListener {

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

    protected void startAnglesBuild() {
        anglesReporter.startBuild(runName, environment, team, component);
    }

    protected void startAnglesTest(String SuiteName, String testName) {
        anglesReporter.startTest(SuiteName, testName);
    }

    protected void anglesTestSuccess() {
        anglesReporter.pass("Test Passed", "true", "true", EMPTY);
        anglesReporter.saveTest();
    }

    protected void anglesTestFailure(String message) {
        anglesReporter.fail("Test Failed", "true", "false", message);
        anglesReporter.saveTest();
    }

    protected void anglesTestFailure() {
        anglesTestFailure(EMPTY);
    }

    protected void anglesTestSkipped() {
        anglesReporter.info("Test has been skipped");
        anglesReporter.saveTest();
    }

    protected String getAnglesPropertyFromSystem(String property) throws AnglesPropertyNotGivenException {
        String propertyValue = System.getProperty(property);
        if(isBlank(propertyValue)) {
            throw new AnglesPropertyNotGivenException("Detected that property [" + property + "] was not given. Please add this mandatory property as a system property");
        }
        return propertyValue;
    }

}
