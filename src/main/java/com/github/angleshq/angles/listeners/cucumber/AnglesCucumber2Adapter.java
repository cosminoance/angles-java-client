package com.github.angleshq.angles.listeners.cucumber;

import com.github.angleshq.angles.basetest.cucumber.AnglesCucumberHooks;
import cucumber.api.Result;
import cucumber.api.TestStep;
import cucumber.api.event.EventHandler;
import cucumber.api.event.TestCaseFinished;
import cucumber.api.event.TestStepStarted;
import cucumber.api.formatter.Formatter;
import cucumber.runner.PickleTestStep;
import gherkin.pickles.PickleCell;
import gherkin.pickles.PickleRow;
import gherkin.pickles.PickleTable;


public class AnglesCucumber2Adapter extends AnglesCucumberHooks implements Formatter {

    protected EventHandler<TestStepStarted> handleTestStepFinished = new EventHandler<TestStepStarted>() {
        @Override
        public void receive(TestStepStarted event) {
            TestStep testStep = event.testStep;
            if (testStep.getClass() == PickleTestStep.class) {
                String text = event.testStep.getStepText();
                String argumentTable = "\n";
                try {
                    PickleTable argument = (PickleTable) testStep.getStepArgument().get(0);
                    for (PickleRow row : argument.getRows()) {
                        argumentTable += "\n| ";
                        for (PickleCell cell : row.getCells()) {
                            argumentTable += cell.getValue() + " | ";
                        }
                    }
                    anglesReporter.info("Step: " + text + " " + argumentTable);
                } catch (Exception e) {
                    anglesReporter.info("Step: " + text);
                }
            }
        }
    };

    protected EventHandler<TestCaseFinished> handleTestCaseFinished =
            new EventHandler<TestCaseFinished>() {
                @Override
                public void receive(cucumber.api.event.TestCaseFinished event) {
                    cucumber.api.TestCase testCase = event.testCase;
                    cucumber.api.Result result = event.result;
                    Throwable error = result.getError();
                    String scenarioName = testCase.getName();
                    TestStep testStep = testCase.getTestSteps().get(0);
                    String text = testStep.getHookType().toString();
                    //anglesReporter.info("Finished test step" + text);

                    String id = "" + testCase.getUri() + testCase.getLine();
                    System.out.println("Testcase " + id + " - " + result.getStatus());
                    if (result.getStatus().equals(Result.Type.PASSED)) {
                        anglesReporter.pass(scenarioName + " passed!", "", "", "");
                    }
                    if (result.getStatus().equals(Result.Type.FAILED)) {
                        anglesReporter.fail(scenarioName + " passed!", "", "", "");
                    }
                    if (result.getStatus().equals(Result.Type.SKIPPED)) {
                        anglesReporter.fail(scenarioName + " passed!", "", "", "");
                    }

                    anglesReporter.saveTest();

                }
            };

    @Override
    public void setEventPublisher(cucumber.api.event.EventPublisher publisher) {
        publisher.registerHandlerFor(cucumber.api.event.TestCaseFinished.class, handleTestCaseFinished);
        publisher.registerHandlerFor(cucumber.api.event.TestStepStarted.class, handleTestStepFinished);
    }
}
