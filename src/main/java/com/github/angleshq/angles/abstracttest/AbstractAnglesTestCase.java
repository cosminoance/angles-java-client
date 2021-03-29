package com.github.angleshq.angles.abstracttest;

import com.github.angleshq.angles.AnglesReporter;
import com.github.angleshq.angles.exceptions.AnglesPropertyNotGivenException;

import static com.github.angleshq.angles.util.AnglesUtils.getComponent;
import static com.github.angleshq.angles.util.AnglesUtils.getEnvironment;
import static com.github.angleshq.angles.util.AnglesUtils.getRunName;
import static com.github.angleshq.angles.util.AnglesUtils.getTeam;
import static com.github.angleshq.angles.util.AnglesUtils.initialiseAnglesTestParameters;

public abstract class AbstractAnglesTestCase {

    protected AnglesReporter anglesReporter;

    protected AbstractAnglesTestCase() {
        try {
            anglesReporter = initialiseAnglesTestParameters();
            anglesReporter.startBuild(getRunName(), getEnvironment(), getTeam(), getComponent());
        } catch (AnglesPropertyNotGivenException e) {
            System.out.println("Please ensure you provide all required system properties for Angles Dashboard.");
        }
    }
}