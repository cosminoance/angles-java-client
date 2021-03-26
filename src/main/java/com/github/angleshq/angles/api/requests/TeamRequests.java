package com.github.angleshq.angles.api.requests;

import com.github.angleshq.angles.api.exceptions.AnglesServerException;
import com.github.angleshq.angles.api.models.Team;
import com.github.angleshq.angles.api.models.screenshot.Screenshot;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class TeamRequests extends BaseRequests {

    private String basePath = "team";
    public TeamRequests(String baseUrl) {
        super(baseUrl);
    }

    public Team create(Team team) throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendJSONPost(basePath, team);
        return processResponse(response, Team.class);
    }

    public Team[] get() throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendJSONGet(basePath);
        return processResponse(response, Team[].class);
    }

    public Team get(String teamId) throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendJSONGet(basePath + "/" + teamId);
        return processResponse(response, Team.class);
    }

    public Boolean delete(String teamId) throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendDelete(basePath + "/" + teamId);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return true;
        } else {
            processErrorResponse(response);
        }
        return false;
    }

    public Team update(Team team) throws IOException, AnglesServerException {
        CloseableHttpResponse response = sendJSONPut(basePath + "/" + team.getId(), team);
        return processResponse(response, Team.class);
    }
}
