package com.github.angleshq.angles.basetest;

import com.github.angleshq.angles.AnglesReporter;
import com.github.angleshq.angles.exceptions.AnglesPropertyNotGivenException;

import java.util.Objects;

import static com.github.angleshq.angles.util.AnglesReporterUtils.getComponent;
import static com.github.angleshq.angles.util.AnglesReporterUtils.getEnvironment;
import static com.github.angleshq.angles.util.AnglesReporterUtils.getRunName;
import static com.github.angleshq.angles.util.AnglesReporterUtils.getTeam;
import static com.github.angleshq.angles.util.AnglesReporterUtils.initialiseAnglesTestParameters;

public abstract class AbstractAnglesTestCase {

    protected AnglesReporter anglesReporter;
    protected boolean anglesEnabled= !Objects.isNull(System.getProperty("angles.enabled"));;
    protected AbstractAnglesTestCase() {
        if(anglesEnabled) {
            try {
                anglesReporter = initialiseAnglesTestParameters();
                anglesReporter.startBuild(getRunName(), getEnvironment(), getTeam(), getComponent());
            } catch (AnglesPropertyNotGivenException e) {
                System.out.println("Please ensure you provide all required system properties for Angles Dashboard.");
            }
        }
    }
}