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

        if(methodCount.containsKey(getUniqueTestName(suiteName, methodName))) {
            Integer count = methodCount.get(getUniqueTestName(suiteName, methodName)) + 1;
            anglesReporter.startTest(suiteName, methodName + " [" + count + "]");
            methodCount.put(getUniqueTestName(suiteName, methodName), count);
        } else {
            anglesReporter.startTest(suiteName, methodName);
            methodCount.put(getUniqueTestName(suiteName, methodName), 1);
        }
    }

    @AfterEach
    public void anglesAfterEach(TestInfo info) {
        System.out.println(info.getDisplayName());
        String suiteName = info.getTestClass().get().getSimpleName();
        String methodName = info.getTestMethod().get().getName();
        System.out.println("afterEach: [" + getUniqueTestName(suiteName, methodName) + "]");
        anglesReporter.saveTest();
    }
}
