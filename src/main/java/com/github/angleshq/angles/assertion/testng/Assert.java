package com.github.angleshq.angles.assertion.testng;

import org.testng.asserts.Assertion;
import org.testng.asserts.IAssert;

import static com.github.angleshq.angles.util.AnglesReporterUtils.getAnglesReporter;

public class Assert extends Assertion {

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


}