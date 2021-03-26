package com.github.angleshq.angles.listeners.junit;

import com.github.angleshq.angles.exceptions.AnglesPropertyNotGivenException;
import com.github.angleshq.angles.listeners.AbstractAnglesListener;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class AnglesJUnit5TestExtension extends AbstractAnglesListener implements BeforeAllCallback, BeforeEachCallback,
         AfterEachCallback, AfterAllCallback {

    public AnglesJUnit5TestExtension() throws AnglesPropertyNotGivenException {
        initialiseAnglesTestParameters();
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        startAnglesBuild();
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {

    }

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        if(extensionContext.getExecutionException().isPresent()) {
            anglesTestFailure("Test failed due to: [" +
                    extensionContext.getExecutionException().get().getClass().getSimpleName() + "]");
        } else {
            anglesTestSuccess();
        }
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        startAnglesTest(extensionContext.getTestMethod().get().getDeclaringClass().getSimpleName(),
                extensionContext.getTestMethod().get().getName());
    }
}