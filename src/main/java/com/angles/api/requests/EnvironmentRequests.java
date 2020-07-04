package com.angles.api.requests;

import com.angles.api.models.Environment;
import com.google.gson.Gson;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class EnvironmentRequests extends BaseRequests {

    private String basePath = "environment";
    public EnvironmentRequests(String baseUrl) {
        super(baseUrl);
    }

    public Environment create(Environment environment) throws IOException {
        CloseableHttpResponse response = sendJSONPost(basePath, environment);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Environment.class);
        }
        return null;
    }

    public Environment[] get() throws IOException {
        CloseableHttpResponse response = sendJSONGet(basePath);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Environment[].class);
        }
        return null;
    }

    public Environment get(String environmentId) throws IOException {
        CloseableHttpResponse response = sendJSONGet(basePath + "/" + environmentId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Environment.class);
        }
        return null;
    }

    public Boolean delete(String environmentId) throws IOException {
        CloseableHttpResponse response = sendDelete(basePath + "/" + environmentId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return true;
        }
        return false;
    }

    public Environment update(Environment environment) throws IOException {
        CloseableHttpResponse response = sendJSONPut(basePath + "/" + environment.getId(), environment);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Environment.class);
        }
        return null;
    }
}
