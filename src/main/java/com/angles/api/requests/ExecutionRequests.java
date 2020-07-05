package com.angles.api.requests;

import com.angles.StepStatus;
import com.angles.api.models.Environment;
import com.angles.api.models.build.Artifact;
import com.angles.api.models.build.Build;
import com.angles.api.models.execution.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import sun.awt.PlatformFont;

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

    public Execution[] get() throws IOException {
        CloseableHttpResponse response = sendJSONGet(basePath);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Execution[].class);
        }
        return null;
    }

    public Execution get(String executionId) throws IOException {
        CloseableHttpResponse response = sendJSONGet(basePath + "/" + executionId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Execution.class);
        }
        return null;
    }

    public Boolean delete(String executionId) throws IOException {
        CloseableHttpResponse response = sendDelete(basePath + "/" + executionId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return true;
        }
        return false;
    }

    public Execution update(Execution execution) throws IOException {
        CloseableHttpResponse response = sendJSONPut(basePath + "/" + execution.getId(), execution);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Execution.class);
        }
        return null;
    }

    public ExecutionResponse platforms(String executionId, Platform platform) throws IOException {
        Platform[] platforms = { platform };
        return platforms(executionId, platforms);
    }

    public ExecutionResponse platforms(String executionId, Platform[] platforms) throws IOException {
        AddPlatforms addPlatformsRequest = new AddPlatforms();
        for (Platform platform: platforms) {  addPlatformsRequest.addPlatform(platform); }
        CloseableHttpResponse response = sendJSONPut(basePath + "/" + executionId + "/platforms", addPlatformsRequest);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, ExecutionResponse.class);
        }
        return null;
    }
}
