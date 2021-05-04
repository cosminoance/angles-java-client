package com.github.angleshq.angles.basetest.cucumber;

import com.github.angleshq.angles.assertion.junit.AnglesJUnit5Assert;
import com.github.angleshq.angles.basetest.AbstractAnglesTestCase;

public class AnglesCucumberHooks extends AbstractAnglesTestCase {

    protected static String suiteName = "";

    protected AnglesJUnit5Assert doAssert = new AnglesJUnit5Assert();
    public AnglesCucumberHooks(){
        super();

    }

//        @BeforeClass
//    public static void anglesCucumberSetup() {
//        try {
//            anglesReporter = initialiseAnglesTestParameters();
//            anglesReporter.startBuild(getRunName(), getEnvironment(), getTeam(), getComponent());
//        } catch (AnglesPropertyNotGivenException e) {
//            System.out.println("Please ensure you provide all required system properties for Angles Dashboard.");
//        }
//    }

}