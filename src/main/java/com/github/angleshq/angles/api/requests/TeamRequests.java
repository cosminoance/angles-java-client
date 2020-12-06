package com.github.angleshq.angles.api.requests;

import com.github.angleshq.angles.api.models.Team;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class TeamRequests extends BaseRequests {

    private String basePath = "team";
    public TeamRequests(String baseUrl) {
        super(baseUrl);
    }

    public Team create(Team team) throws IOException {
        CloseableHttpResponse response = sendJSONPost(basePath, team);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Team.class);
        }
        return null;
    }

    public Team[] get() throws IOException {
        CloseableHttpResponse response = sendJSONGet(basePath);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Team[].class);
        }
        return null;
    }

    public Team get(String teamId) throws IOException {
        CloseableHttpResponse response = sendJSONGet(basePath + "/" + teamId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Team.class);
        }
        return null;
    }

    public Boolean delete(String teamId) throws IOException {
        CloseableHttpResponse response = sendDelete(basePath + "/" + teamId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return true;
        }
        return false;
    }

    public Team update(Team team) throws IOException {
        CloseableHttpResponse response = sendJSONPut(basePath + "/" + team.getId(), team);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String responseBody = EntityUtils.toString(response.getEntity());
            return gson.fromJson(responseBody, Team.class);
        }
        return null;
    }
}
