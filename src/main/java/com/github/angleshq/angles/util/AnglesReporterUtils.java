package com.github.angleshq.angles.util;

import com.github.angleshq.angles.AnglesReporter;
import com.github.angleshq.angles.exceptions.AnglesPropertyNotGivenException;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class AnglesReporterUtils {

    private static AnglesReporter anglesReporter;
    private static String runName;
    private static String team;
    private static String component;
    private static String environment;

    public static AnglesReporter initialiseAnglesTestParameters() throws AnglesPropertyNotGivenException {
        anglesReporter = AnglesReporter.getInstance(getAnglesPropertyFromSystem("angles.url") + "/rest/api/v1.0/");
        runName = getAnglesPropertyFromSystem("angles.runName");
        team = getAnglesPropertyFromSystem("angles.team");
        component = getAnglesPropertyFromSystem("angles.component");
        environment = getAnglesPropertyFromSystem("angles.environment");
        return anglesReporter;
    }

    public static String getAnglesPropertyFromSystem(String property) throws AnglesPropertyNotGivenException {
        String propertyValue = System.getProperty(property);
        if(isBlank(propertyValue)) {
            throw new AnglesPropertyNotGivenException("Detected that property [" + property + "] was not given. Please add this mandatory property as a system property");
        }
        return propertyValue;
    }

    public static AnglesReporter getAnglesReporter() {
        return anglesReporter;
    }

    public static String getRunName() {
        return runName;
    }

    public static String getTeam() {
        return team;
    }

    public static String getComponent() {
        return component;
    }

    public static String getEnvironment() {
        return environment;
    }
}