package com.github.angleshq.angles.abstracttest.junit;

import com.github.angleshq.angles.abstracttest.AbstractAnglesTestCase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public class AnglesJUnit5BaseTest extends AbstractAnglesTestCase {

    @BeforeEach
    public void anglesBeforeEach(TestInfo info) {
        String suiteName = info.getTestClass().get().getSimpleName();
        String methodName = info.getTestMethod().get().getName();
        startAnglesTest(suiteName, methodName);
    }

    @AfterEach
    public void anglesAfterEach(TestInfo info) {
        anglesReporter.saveTest();
    }
}