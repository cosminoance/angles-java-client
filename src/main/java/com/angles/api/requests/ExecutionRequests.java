package com.angles.api.requests;

import com.angles.StepStatus;
import com.angles.api.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;

public class ExecutionRequests extends BaseRequests {

    private String basePath = "execution";
    public ExecutionRequests(String baseUrl) {
        super(baseUrl);
    }

    public Execution create(CreateExecution createExecution) throws IOException {
        CloseableHttpResponse response = sendJSONPost(basePath, createExecution);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println(responseBody);
            return gson.fromJson(responseBody, Execution.class);
        }
        return null;
    }


    public static void main(String[] args) throws IOException {


//        BuildRequests buildRequests = new BuildRequests("http://localhost:3000/rest/api/v1.0/");
        ExecutionRequests executionRequests = new ExecutionRequests("http://localhost:3000/rest/api/v1.0/");
//        CreateBuild createBuild = new CreateBuild();
//        createBuild.setTeam("angles");
//        createBuild.setEnvironment("preprod");
//        createBuild.setName("SmokeTest New Client");
//        createBuild.setComponent("anglesui");
//        Build newBuild = buildRequests.create(createBuild);
//        System.out.println(gson.toJson(newBuild.getComponent()));

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
        CreateExecution createExecution = new CreateExecution();
        createExecution.setBuild("5f00ee233c0ef49c237b1c4c");
        createExecution.setSuite("Suite123");
        createExecution.setTitle("TestExecutionTitle1");
        createExecution.setStart(new Date());

        Action action = new Action("Login");
        Step step = new Step("Navigate to Login Page", "Attempting To Login", StepStatus.INFO, new Date());
        Step step2 = new Step("Login", "true", "true", "Attempting To Login", StepStatus.PASS, new Date());
        action.addStep(step);
        action.addStep(step2);
        createExecution.addAction(action);
        createExecution.addTag("Selenium");

        Platform platform = new Platform();
        platform.setBrowserName("Chrome");
        platform.setBrowserVersion("83");
        platform.setPlatformName("Windows");
        platform.setPlatformVersion("10");

        createExecution.addPlatform(platform);
        System.out.println(gson.toJson(createExecution));
        Execution execution = executionRequests.create(createExecution);

        System.out.println(gson.toJson(execution));


    }


}
