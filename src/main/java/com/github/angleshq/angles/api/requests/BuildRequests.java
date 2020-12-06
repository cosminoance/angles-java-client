package com.github.angleshq.angles.api.requests;

import com.github.angleshq.angles.api.models.build.Artifact;
import com.github.angleshq.angles.api.models.build.Build;
import com.github.angleshq.angles.api.models.build.CreateBuild;
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

    public Build create(CreateBuild createBuild) throws IOException {
        CloseableHttpResponse response = sendJSONPost(basePath, createBuild);
        String responseBody = EntityUtils.toString(response.getEntity());
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
            return gson.fromJson(responseBody, Build.class);
        } else {
            System.out.println(response.getStatusLine().getStatusCode() + ": " + responseBody);
        }
        return null;
    }

    public Build[] get(String teamId, Integer limit, Integer skip) throws IOException, URISyntaxException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("teamId", teamId);
        if (limit != null) { parameters.put("limit", limit); }
        if (skip != null) { parameters.put("skip", skip); }
        CloseableHttpResponse response = sendJSONGet(basePath, parameters);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Build[].class);
        }
        return null;
    }

    public Build get(String buildId) throws IOException {
        CloseableHttpResponse response = sendJSONGet(basePath + "/" + buildId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Build.class);
        }
        return null;
    }

    public Boolean delete(String buildId) throws IOException {
        CloseableHttpResponse response = sendDelete(basePath + "/" + buildId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return true;
        }
        return false;
    }

    public Build update(Build build) throws IOException {
        CloseableHttpResponse response = sendJSONPut(basePath + "/" + build.getId(), build);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Build.class);
        }
        return null;
    }

    public Build keep(String buildId, Boolean keep) throws IOException {
        String request = "{ \"keep\": " + keep + " }";
        CloseableHttpResponse response = sendJSONPut(basePath + "/" + buildId + "/keep", request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Build.class);
        }
        return null;
    }

    public Build artifacts(String buildId, Artifact artifact) throws IOException {
        Artifact[] artifacts = { artifact };
        return artifacts(buildId, artifacts);
    }

    public Build artifacts(String buildId, Artifact[] artifacts) throws IOException {
        CloseableHttpResponse response = sendJSONPut(basePath + "/" + buildId + "/artifacts", artifacts);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Build.class);
        }
        return null;
    }

}
