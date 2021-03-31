package com.github.angleshq.angles.assertion.testng;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

import static com.github.angleshq.angles.assertion.AssertHelper.anglesAssertGreaterThan;
import static com.github.angleshq.angles.assertion.AssertHelper.anglesAssertLessThan;
import static com.github.angleshq.angles.util.AnglesReporterUtils.getAnglesReporter;

public class AnglesTestngAssert extends Assertion {

    @Override
    public void onAssertSuccess(IAssert<?> assertCommand) {
        getAnglesReporter().pass("Assert", assertCommand.getExpected().toString(),
                assertCommand.getActual().toString(), assertCommand.getMessage());
    }

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        getAnglesReporter().fail("Assert", assertCommand.getExpected().toString(),
                assertCommand.getActual().toString(), assertCommand.getMessage());
    }

    public void assertGreaterThan(Double value1, Double value2) {
        assertEquals(true, anglesAssertGreaterThan(value1, value2));
    }

    public void assertLessThan(Double value1, Double value2) {
        assertEquals(true, anglesAssertLessThan(value1, value2));
    }

    public void assertGreaterThan(Integer value1, Integer value2) {
        assertEquals(true, anglesAssertGreaterThan(value1, value2));
    }

    public void assertLessThan(Integer value1, Integer value2) {
        assertEquals(true, anglesAssertLessThan(value1, value2));
    }

    public void assertGreaterThan(Float value1, Float value2) {
        assertEquals(true, anglesAssertGreaterThan(value1, value2));
    }

    public void assertLessThan(Float value1, Float value2) {
        assertEquals(true, anglesAssertLessThan(value1, value2));
    }

    public void assertGreaterThan(String value1, String value2) {
        assertEquals(true, anglesAssertGreaterThan(value1, value2));
    }

    public void assertLessThan(String value1, String value2) {
        assertEquals(true, anglesAssertLessThan(value1, value2));
    }

    private void handleAssertEquals(String step, Object expected, Object actual, String details) {
        if(expected.equals(actual)) {
            getAnglesReporter().pass(step, expected.toString(), actual.toString(), details);
        } else {
            getAnglesReporter().fail(step, expected.toString(), actual.toString(), details);
        }
    }
}