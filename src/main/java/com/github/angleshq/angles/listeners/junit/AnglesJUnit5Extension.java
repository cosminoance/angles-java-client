package com.github.angleshq.angles.listeners.junit;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static com.github.angleshq.angles.util.AnglesReporterUtils.getAnglesReporter;

public class AnglesJUnit5Extension implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        getAnglesReporter().saveTest();
    }
}