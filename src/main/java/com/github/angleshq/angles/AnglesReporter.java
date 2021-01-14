package com.github.angleshq.angles;

import com.github.angleshq.angles.api.models.build.Artifact;
import com.github.angleshq.angles.api.models.build.Build;
import com.github.angleshq.angles.api.models.build.CreateBuild;
import com.github.angleshq.angles.api.models.execution.Action;
import com.github.angleshq.angles.api.models.execution.CreateExecution;
import com.github.angleshq.angles.api.models.execution.Step;
import com.github.angleshq.angles.api.models.screenshot.*;
import com.github.angleshq.angles.api.requests.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AnglesReporter {

    public static final String DEFAULT_ACTION_NAME = "Test Details";
    private static Map<String, AnglesReporter> reporterMap = new HashMap<>();
    private String baseUrl;
    private BuildRequests buildRequests;
    private ExecutionRequests executionRequests;
    private EnvironmentRequests environmentRequests;
    private TeamRequests teamRequests;
    private ScreenshotRequests screenshotRequests;

    private InheritableThreadLocal<Build> currentBuild = new InheritableThreadLocal<>();
    private InheritableThreadLocal<CreateExecution> currentExecution = new InheritableThreadLocal<>();
    private InheritableThreadLocal<Action> currentAction = new InheritableThreadLocal<>();

    public static AnglesReporter getInstance(String url) {
        if (!reporterMap.containsKey(url)) {
            reporterMap.put(url, new AnglesReporter(url));
        }
        return reporterMap.get(url);
    }

    private AnglesReporter(String url) {
        this.baseUrl = url;
        buildRequests = new BuildRequests(baseUrl);
        executionRequests = new ExecutionRequests(baseUrl);
        environmentRequests = new EnvironmentRequests(baseUrl);
        teamRequests = new TeamRequests(baseUrl);
        screenshotRequests = new ScreenshotRequests(baseUrl);
    }

    public synchronized void startBuild(String name, String environmentName, String teamName, String componentName) {
        if (currentBuild.get() != null) {
            return;
        }
        CreateBuild createBuild = new CreateBuild();
        createBuild.setName(name);
        createBuild.setEnvironment(environmentName);
        createBuild.setTeam(teamName);
        createBuild.setComponent(componentName);
        try {
            currentBuild.set(buildRequests.create(createBuild));
        } catch (IOException exception) {
            throw new Error("Unable to create build due to [" + exception.getMessage() + "]");
        }
    }

    public synchronized void storeArtifacts(Artifact[] artifacts) {
        try {
            currentBuild.set(buildRequests.artifacts(currentBuild.get().getId(), artifacts));
        } catch (IOException exception) {
            throw new Error("Unable to create build due to [" + exception.getMessage() + "]");
        }
    }

    public void startTest(String suiteName, String testName) {
        CreateExecution createExecution = new CreateExecution();
        createExecution.setStart(new Date());
        createExecution.setBuild(currentBuild.get().getId());
        createExecution.setTitle(testName);
        createExecution.setSuite(suiteName);
        currentExecution.set(createExecution);
        currentAction.set(null);
    }

    public void saveTest() {
        try {
            executionRequests.create(currentExecution.get());
        }  catch (IOException exception) {
            throw new Error("Unable to save/update test execution due to [" + exception.getMessage() + "]");
        }
    }

    public void startAction(String description) {
        if (this.currentExecution.get() == null) {
            //if no test has been created assume it's the setup
            this.startTest("Set-up", "Set-up");
        }
        this.currentAction.set(new Action(description));
        this.currentExecution.get().getActions().add(this.currentAction.get());
    }

    public void debug(String debug) {
        addStep("debug", null, null, debug, StepStatus.DEBUG, null);
    }

    public void debug(String debug, String screenshotId) {
        addStep("debug", null, null, debug, StepStatus.DEBUG, screenshotId);
    }

    public void error(String error) {
        addStep("error", null, null, error, StepStatus.ERROR, null);
    }

    public void error(String error, String screenshotId) {
        addStep("error", null, null, error, StepStatus.ERROR, screenshotId);
    }

    public void info(String info) {
        addStep("info", null, null, info, StepStatus.INFO, null);
    }

    public void info(String info, String screenshotId) {
        addStep("info", null, null, info, StepStatus.INFO, screenshotId);
    }

    public void pass(String name, String expected, String actual, String info) {
        addStep(name, expected, actual, info, StepStatus.PASS, null);
    }

    public void pass(String name, String expected, String actual, String info, String screenshotId) {
        addStep(name, expected, actual, info, StepStatus.PASS, screenshotId);
    }

    public void fail(String name, String expected, String actual, String info) {
        addStep(name, expected, actual, info, StepStatus.FAIL);
    }

    public void fail(String name, String expected, String actual, String info, String screenshotId) {
        addStep(name, expected, actual, info, StepStatus.FAIL, screenshotId);
    }

    private void addStep(String name, String expected, String actual, String info, StepStatus status) {
        addStep(name, expected, actual, info, status, null);
    }

    private void addStep(String name, String expected, String actual, String info, StepStatus status, String screenshotId) {
        if (currentAction.get() == null) {
            startAction(DEFAULT_ACTION_NAME);
        }
        Step step = new Step();
        step.setTimestamp(new Date());
        step.setStatus(status);
        step.setName(name);
        step.setExpected(expected);
        step.setActual(actual);
        step.setInfo(info);
        if (screenshotId !=null) {
            step.setScreenshot(screenshotId);
        }
        currentAction.get().addStep(step);
    }

    public CreateScreenshotResponse storeScreenshot(ScreenshotDetails details) {
        CreateScreenshot createScreenshot = new CreateScreenshot();
        createScreenshot.setBuildId(currentBuild.get().getId());
        createScreenshot.setTimestamp(new Date());
        createScreenshot.setView(details.getView());
        createScreenshot.setFilePath(details.getPath());
        try {
            CreateScreenshotResponse response = screenshotRequests.create(createScreenshot);
            // we need to store the platform details or tags
            storeScreenshotDetails(response.getId(), details);
            return response;
        } catch (IOException exception) {
            throw new Error("Unable store screenshot due to [" + exception.getMessage() + "]");
        }
    }

    public ImageCompareResponse compareScreenshotAgainstBaseline(String screenshotId) {
        try {
            return screenshotRequests.baselineCompare(screenshotId);
        } catch (IOException exception) {
            throw new Error("Unable compare screenshot with baseline due to [" + exception.getMessage() + "]");
        }
    }

    private void storeScreenshotDetails(String screenshotId, ScreenshotDetails details) {
        if (details.getPlatform() != null || details.getTags() != null) {
            UpdateScreenshot updateScreenshot = new UpdateScreenshot();
            if (details.getPlatform() != null) updateScreenshot.setPlatform(details.getPlatform());
            if (details.getTags() != null) updateScreenshot.setTags(details.getTags());
            try {
                screenshotRequests.update(screenshotId, updateScreenshot);
            } catch (IOException exception) {
                throw new Error("Unable update screenshot due to [" + exception.getMessage() + "]");
            }
        }
    }
}
