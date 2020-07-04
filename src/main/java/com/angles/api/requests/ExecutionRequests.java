package com.angles.api.requests;

import com.angles.api.models.Build;
import com.angles.api.models.CreateBuild;
import com.angles.api.models.CreateExecution;
import com.angles.api.models.Execution;
import com.google.gson.Gson;
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
        String responseBody = EntityUtils.toString(response.getEntity());
        System.out.println(responseBody);
        return null;
    }


    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        BuildRequests buildRequests = new BuildRequests("http://localhost:3000/rest/api/v1.0/");
        ExecutionRequests executionRequests = new ExecutionRequests("http://localhost:3000/rest/api/v1.0/");
        CreateBuild createBuild = new CreateBuild();
        createBuild.setTeam("angles");
        createBuild.setEnvironment("preprod");
        createBuild.setName("SmokeTest New Client");
        createBuild.setComponent("anglesui");
        Build newBuild = buildRequests.create(createBuild);
        System.out.println(gson.toJson(newBuild.getComponent()));


        CreateExecution createExecution = new CreateExecution();
        createExecution.setBuild(newBuild.getId());
        createExecution.setSuite("Suite123");
        createExecution.setTitle("TestExecutionTitle1");
        createExecution.setStart(new Date());
//        createExecution.addTag("Selenium");
        Execution execution = executionRequests.create(createExecution);

        System.out.println(gson.toJson(execution));





    }


}
