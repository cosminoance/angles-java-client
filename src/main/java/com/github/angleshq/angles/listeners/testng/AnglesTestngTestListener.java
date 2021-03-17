package com.github.angleshq.angles.listeners.testng;

import com.github.angleshq.angles.AnglesReporter;
import com.github.angleshq.angles.exceptions.AnglesPropertyNotGivenException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isBlank;

public class AnglesTestngTestListener implements ITestListener {

    private AnglesReporter anglesReporter;
    private String runName;
    private String team;
    private String component;
    private String environment;

    public AnglesTestngTestListener() throws AnglesPropertyNotGivenException {
        anglesReporter = AnglesReporter.getInstance(getAnglesPropertyFromSystem("angles.url") + "/rest/api/v1.0/");
        runName = getAnglesPropertyFromSystem("angles.runName");
        team = getAnglesPropertyFromSystem("angles.team");
        component = getAnglesPropertyFromSystem("angles.component");
        environment = getAnglesPropertyFromSystem("angles.environment");
    }

    public void onStart(ITestContext context) {
        anglesReporter.startBuild(runName, environment, team, component);
    }

    public void onFinish(ITestContext context) {

    }

    public void onTestStart(ITestResult result) {
        anglesReporter.startTest(result.getMethod().getRealClass().getSimpleName(), result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        anglesReporter.pass("Test Passed", "true", "true", EMPTY);
        anglesReporter.saveTest();
    }

    public void onTestFailure(ITestResult result) {
        anglesReporter.fail("Test Failed", "true", "false", EMPTY);
        anglesReporter.saveTest();
    }

    public void onTestSkipped(ITestResult result) {
        anglesReporter.info("Test has been skipped");
        anglesReporter.saveTest();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    private String getAnglesPropertyFromSystem(String property) throws AnglesPropertyNotGivenException {
        String propertyValue = System.getProperty(property);
        if(isBlank(propertyValue)) {
            throw new AnglesPropertyNotGivenException("Detected that property [" + property + "] was not given. Please add this mandatory property as a system property");
        }
        return propertyValue;
    }
}
