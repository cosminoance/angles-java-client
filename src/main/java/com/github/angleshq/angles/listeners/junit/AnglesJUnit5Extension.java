package com.github.angleshq.angles.listeners.junit;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.github.angleshq.angles.util.AnglesReporterUtils.getAnglesReporter;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public class AnglesJUnit5Extension implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        if(extensionContext.getExecutionException().isPresent()) {
            getAnglesReporter().fail("Test failed", EMPTY, EMPTY,
                    extensionContext.getExecutionException().get().getMessage());
        } else {
            getAnglesReporter().pass("Test passed", EMPTY, EMPTY, EMPTY);
        }
        getAnglesReporter().saveTest();
    }
}