package com.github.angleshq.angles.listeners.testng;

import com.github.angleshq.angles.exceptions.AnglesPropertyNotGivenException;
import com.github.angleshq.angles.listeners.AbstractAnglesListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AnglesTestngTestListener extends AbstractAnglesListener implements ITestListener {

    public AnglesTestngTestListener() throws AnglesPropertyNotGivenException {
        initialiseAnglesTestParameters();
    }

    public void onStart(ITestContext context) {
        startAnglesBuild();
    }

    public void onFinish(ITestContext context) {

    }

    public void onTestStart(ITestResult result) {
        startAnglesTest(result.getMethod().getRealClass().getSimpleName(), result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        anglesTestSuccess();
    }

    public void onTestFailure(ITestResult result) {
        anglesTestFailure();
    }

    public void onTestSkipped(ITestResult result) {
        anglesTestSkipped();
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}
