package com.github.angles.basetest;

import com.github.angles.AnglesReporterInterface;
import com.github.angles.exceptions.AnglesPropertyNotGivenException;

import static com.github.angles.util.AnglesReporterUtils.*;

public abstract class AbstractAnglesTestCase {

    protected AnglesReporterInterface anglesReporter;

    protected AbstractAnglesTestCase() {
        try {
            anglesReporter = initialiseAnglesTestParameters();
            anglesReporter.startBuild(getRunName(), getEnvironment(), getTeam(), getComponent());
        } catch (AnglesPropertyNotGivenException e) {
            System.out.println("Please ensure you provide all required system properties for Angles Dashboard.");
        }
    }
}