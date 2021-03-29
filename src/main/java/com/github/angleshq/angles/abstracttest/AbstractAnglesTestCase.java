package com.github.angleshq.angles.abstracttest;

import com.github.angleshq.angles.AnglesReporter;
import com.github.angleshq.angles.exceptions.AnglesPropertyNotGivenException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.apache.commons.lang3.StringUtils.isBlank;

public abstract class AbstractAnglesTestCase {

    protected AnglesReporter anglesReporter;
    protected String runName;
    protected String team;
    protected String component;
    protected String environment;
    protected static Map<String, Integer> methodCount = new ConcurrentHashMap<>();

    protected AbstractAnglesTestCase() {
        try {
            initialiseAnglesTestParameters();
            anglesReporter.startBuild(runName, environment, team, component);
        } catch (AnglesPropertyNotGivenException e) {
            System.out.println("Please ensure you provide all required system properties for Angles Dashboard.");
        }
    }

    protected void initialiseAnglesTestParameters() throws AnglesPropertyNotGivenException {
        anglesReporter = AnglesReporter.getInstance(getAnglesPropertyFromSystem("angles.url") + "/rest/api/v1.0/");
        runName = getAnglesPropertyFromSystem("angles.runName");
        team = getAnglesPropertyFromSystem("angles.team");
        component = getAnglesPropertyFromSystem("angles.component");
        environment = getAnglesPropertyFromSystem("angles.environment");
    }

    protected static String getAnglesPropertyFromSystem(String property) throws AnglesPropertyNotGivenException {
        String propertyValue = System.getProperty(property);
        if(isBlank(propertyValue)) {
            throw new AnglesPropertyNotGivenException("Detected that property [" + property + "] was not given. Please add this mandatory property as a system property");
        }
        return propertyValue;
    }

    protected void startAnglesTest(String suiteName, String methodName) {
        if(methodCount.containsKey(getUniqueTestName(suiteName, methodName))) {
            Integer count = methodCount.get(getUniqueTestName(suiteName, methodName)) + 1;
            anglesReporter.startTest(suiteName, methodName + " [" + count + "]");
            methodCount.put(getUniqueTestName(suiteName, methodName), count);
        } else {
            anglesReporter.startTest(suiteName, methodName);
            methodCount.put(getUniqueTestName(suiteName, methodName), 1);
        }
    }

    protected String getUniqueTestName(String suiteName, String methodName) {
        return suiteName + "_" + methodName;
    }
}
