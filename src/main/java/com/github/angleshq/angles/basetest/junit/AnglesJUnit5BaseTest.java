package com.github.angleshq.angles.basetest.junit;

import com.github.angleshq.angles.basetest.AbstractAnglesTestCase;
import com.github.angleshq.angles.listeners.junit.AnglesJUnit5Extension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AnglesJUnit5Extension.class)
public class AnglesJUnit5BaseTest extends AbstractAnglesTestCase {

    /**
     * Starts a test run within Angles. Override to customise test name.
     * @param info
     */
    @BeforeEach
    public void anglesBeforeEach(TestInfo info) {
        String suiteName = info.getTestClass().get().getSimpleName();
        String methodName = info.getTestMethod().get().getName();
        anglesReporter.startTest(suiteName, methodName);
    }
}