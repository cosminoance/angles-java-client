package com.github.angleshq.angles;

import com.github.angleshq.angles.api.models.Platform;
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

    private static Map<String, AnglesReporter> reporterMap = new HashMap<>();
    private String baseUrl;
    private BuildRequests buildRequests;
    private ExecutionRequests executionRequests;
    private EnvironmentRequests environmentRequests;
    private TeamRequests teamRequests;
    private ScreenshotRequests screenshotRequests;

    private Build currentBuild;
    private ThreadLocal<CreateExecution> currentExecution = new ThreadLocal<>();
    private ThreadLocal<Action> currentAction = new ThreadLocal<>();

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
        System.out.println("Creating Build [" + name + "]");
        if (currentBuild != null) {
            return;
        }
        CreateBuild createBuild = new CreateBuild();
        createBuild.setName(name);
        createBuild.setEnvironment(environmentName);
        createBuild.setTeam(teamName);
        createBuild.setComponent(componentName);
        try {
            currentBuild = buildRequests.create(createBuild);
        } catch (IOException exception) {
            throw new Error("Unable to create build due to [" + exception.getMessage() + "]");
        }
    }

    public void startTest(String suiteName, String testName) {
        System.out.println("Starting Test [" + testName + "]");
        CreateExecution createExecution = new CreateExecution();
        createExecution.setStart(new Date());
        createExecution.setBuild(currentBuild.getId());
        createExecution.setTitle(testName);
        createExecution.setSuite(suiteName);
        currentExecution.set(createExecution);
    }

    public void saveTest() {
        System.out.println(Thread.currentThread().getId() + " - Saving Test");
        try {
            executionRequests.create(currentExecution.get());
        }  catch (IOException exception) {
            throw new Error("Unable to save/update test execution due to [" + exception.getMessage() + "]");
        }

    }

    public void startAction(String description) {
        System.out.println("Adding action [" + description + "]");
        this.currentAction.set(new Action(description));
        this.currentExecution.get().getActions().add(this.currentAction.get());
    }

    public void info(String info) {
        if (currentAction.get() == null) {
            currentAction.set(new Action("Default"));
        }
        Step step = new Step();
        step.setName("info");
        step.setTimestamp(new Date());
        step.setStatus(StepStatus.INFO);
        step.setInfo(info);
        currentAction.get().addStep(step);
    }

    public void debug(String info) {
        if (currentAction.get() == null) {
            currentAction.set(new Action("Default"));
        }
        // add a flag to stop this if debugging is turned off.
        Step step = new Step();
        step.setName("debug");
        step.setTimestamp(new Date());
        step.setStatus(StepStatus.DEBUG);
        step.setInfo(info);
        currentAction.get().addStep(step);
    }

    public void error(String error) {
        if (currentAction.get() == null) {
            currentAction.set(new Action("Default"));
        }
        Step step = new Step();
        step.setName("error");
        step.setTimestamp(new Date());
        step.setStatus(StepStatus.ERROR);
        step.setInfo(error);
        currentAction.get().addStep(step);
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
        createScreenshot.setBuildId(currentBuild.getId());
        createScreenshot.setTimestamp(new Date());
        createScreenshot.setView(details.getView());
        createScreenshot.setFilePath(details.getPath());
        try {
            CreateScreenshotResponse response = screenshotRequests.create(createScreenshot);
            System.out.println(response.getId());
            if (details.getPlatform() != null) {
                // we need to store the platform details
                updateScreenshot(response.getId(), details.getPlatform());
            }
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

    private Screenshot updateScreenshot(String screenshotId, Platform platform) {
        UpdateScreenshot updateScreenshot = new UpdateScreenshot();
        updateScreenshot.setPlatform(platform);
        try {
            return screenshotRequests.update(screenshotId, updateScreenshot);
        } catch (IOException exception) {
            throw new Error("Unable update screenshot due to [" + exception.getMessage() + "]");
        }
    }
}
