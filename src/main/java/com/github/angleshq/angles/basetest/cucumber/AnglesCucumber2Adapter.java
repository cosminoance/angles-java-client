package com.github.angleshq.angles.basetest.cucumber;

import cucumber.api.Result;
import cucumber.api.TestStep;
import cucumber.api.event.EventHandler;
import cucumber.api.event.TestCaseFinished;
import cucumber.api.formatter.Formatter;
import gherkin.pickles.PickleStep;


public class AnglesCucumber2Adapter extends AnglesCucumberHooks implements Formatter {


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
                    if(result.getStatus().equals(Result.Type.PASSED)){
                        anglesReporter.pass(scenarioName +" passeed!","","","");
                    }
                    if (testStep.isHook()) {
                        anglesReporter.info("Finished  scenario" + scenarioName);
                        anglesReporter.saveTest();
                    }
                }

            };

    @Override
    public void setEventPublisher(cucumber.api.event.EventPublisher publisher) {
        publisher.registerHandlerFor(cucumber.api.event.TestCaseFinished.class, handleTestCaseFinished);
    }
}

