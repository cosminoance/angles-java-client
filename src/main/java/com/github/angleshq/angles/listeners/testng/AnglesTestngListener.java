package com.github.angleshq.angles.listeners.testng;

import org.testng.ITestListener;
import org.testng.ITestResult;

import static com.github.angleshq.angles.util.AnglesReporterUtils.getAnglesReporter;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class AnglesTestngListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        getAnglesReporter().fail("Test failed", EMPTY, EMPTY, result.getThrowable().getMessage());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        getAnglesReporter().pass("Test passed", EMPTY, EMPTY, EMPTY);
    }
}
