package com.angles.api.requests;

import com.angles.api.models.Platform;
import com.angles.api.models.execution.*;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ExecutionRequests extends BaseRequests {

    private String basePath = "execution";
    public ExecutionRequests(String baseUrl) {
        super(baseUrl);
    }

    public Execution create(CreateExecution createExecution) throws IOException {
        CloseableHttpResponse response = sendJSONPost(basePath, createExecution);
        String responseBody = EntityUtils.toString(response.getEntity());
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
            return gson.fromJson(responseBody, Execution.class);
        } else {
            System.out.println(response.getStatusLine().getStatusCode() + ": " + responseBody);
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
