package com.github.angleshq.angles.api.requests;

import com.github.angleshq.angles.api.exceptions.AnglesServerException;
import com.github.angleshq.angles.api.models.build.Artifact;
import com.github.angleshq.angles.api.models.build.Build;
import com.github.angleshq.angles.api.models.build.CreateBuild;
import com.github.angleshq.angles.api.models.build.StoreArtifacts;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class BuildRequests extends BaseRequests {

    private String basePath = "build";
    public BuildRequests(String baseUrl) {
        super(baseUrl);
    }

    public Build create(CreateBuild createBuild) throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendJSONPost(basePath, createBuild);
        String responseBody = EntityUtils.toString(response.getEntity());
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
            return gson.fromJson(responseBody, Build.class);
        } else {
            throw new AnglesServerException(getDefaultErrorMessage(response));
        }
    }

    public Build[] get(String teamId, Integer limit, Integer skip) throws IOException, URISyntaxException, AnglesServerException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("teamId", teamId);
        if (limit != null) { parameters.put("limit", limit); }
        if (skip != null) { parameters.put("skip", skip); }
        CloseableHttpResponse response = sendJSONGet(basePath, parameters);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Build[].class);
        } else {
            throw new AnglesServerException(getDefaultErrorMessage(response));
        }
    }

    public Build get(String buildId) throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendJSONGet(basePath + "/" + buildId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Build.class);
        } else {
            throw new AnglesServerException(getDefaultErrorMessage(response));
        }
    }

    public Boolean delete(String buildId) throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendDelete(basePath + "/" + buildId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return true;
        } else {
            throw new AnglesServerException(getDefaultErrorMessage(response));
        }
    }

    public Build update(Build build) throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendJSONPut(basePath + "/" + build.getId(), build);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Build.class);
        } else {
            throw new AnglesServerException(getDefaultErrorMessage(response));
        }
    }

    public Build keep(String buildId, Boolean keep) throws IOException, AnglesServerException {
        String request = "{ \"keep\": " + keep + " }";
        CloseableHttpResponse response = sendJSONPut(basePath + "/" + buildId + "/keep", request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Build.class);
        } else {
            throw new AnglesServerException(getDefaultErrorMessage(response));
        }
    }

    public Build artifacts(String buildId, Artifact artifact) throws IOException, AnglesServerException {
        Artifact[] artifacts = { artifact };
        return artifacts(buildId, artifacts);
    }

    public Build artifacts(String buildId, Artifact[] artifacts) throws IOException, AnglesServerException {
        StoreArtifacts request = new StoreArtifacts();
        request.setArtifacts(artifacts);
        CloseableHttpResponse response = sendJSONPut(basePath + "/" + buildId + "/artifacts", request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Build.class);
        } else {
            throw new AnglesServerException(getDefaultErrorMessage(response));
        }
    }

}
