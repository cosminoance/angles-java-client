package com.github.angleshq.angles.api.requests;

import com.github.angleshq.angles.api.exceptions.AnglesServerException;
import com.github.angleshq.angles.api.models.Environment;
import com.github.angleshq.angles.api.models.build.Build;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class EnvironmentRequests extends BaseRequests {

    private String basePath = "environment";
    public EnvironmentRequests(String baseUrl) {
        super(baseUrl);
    }

    public Environment create(Environment environment) throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendJSONPost(basePath, environment);
        return processResponse(response, Environment.class);
    }

    public Environment[] get() throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendJSONGet(basePath);
        return processResponse(response, Environment[].class);
    }

    public Environment get(String environmentId) throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendJSONGet(basePath + "/" + environmentId);
        return processResponse(response, Environment.class);
    }

    public Boolean delete(String environmentId) throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendDelete(basePath + "/" + environmentId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return true;
        } else  {
            processErrorResponse(response);
        }
        return false;
    }

    public Environment update(Environment environment) throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendJSONPut(basePath + "/" + environment.getId(), environment);
        return processResponse(response, Environment.class);
    }
}
