package com.github.angleshq.angles.abstracttest;

import com.github.angleshq.angles.AnglesReporter;
import com.github.angleshq.angles.exceptions.AnglesPropertyNotGivenException;

import static com.github.angleshq.angles.util.AnglesUtils.getAnglesPropertyFromSystem;

public abstract class AbstractAnglesTestCase {

    protected AnglesReporter anglesReporter;
    protected String runName;
    protected String team;
    protected String component;
    protected String environment;

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

    protected String getUniqueTestName(String suiteName, String methodName) {
        return suiteName + "_" + methodName;
    }
}