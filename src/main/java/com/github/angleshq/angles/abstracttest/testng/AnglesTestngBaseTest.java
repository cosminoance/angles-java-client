package com.github.angleshq.angles.abstracttest.testng;

import com.github.angleshq.angles.abstracttest.AbstractAnglesTestCase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class AnglesTestngBaseTest extends AbstractAnglesTestCase {

    @BeforeMethod(alwaysRun = true)
    public void anglesBeforeMethod(Method method) {
        String suiteName = method.getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        if(methodCount.containsKey(getUniqueTestName(suiteName, methodName))) {
            Integer count = methodCount.get(getUniqueTestName(suiteName, methodName)) + 1;
            anglesReporter.startTest(suiteName, methodName + " [" + count + "]");
            methodCount.put(getUniqueTestName(suiteName, methodName), count);
        } else {
            anglesReporter.startTest(suiteName, methodName);
            methodCount.put(getUniqueTestName(suiteName, methodName), 1);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void anglesAfterMethod(Method method) {
        anglesReporter.saveTest();
    }
}
