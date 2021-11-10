package org.osclimate.trino.groupprovider.github;

import io.trino.spi.classloader.ThreadContextClassLoader;
import io.trino.spi.security.GroupProvider;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

import io.airlift.log.Logger;

import org.osclimate.githubrest.model.Team;
import org.osclimate.githubrest.model.User;
import org.osclimate.githubrest.GitHubRetrofit;
import org.osclimate.githubrest.GitHubRest;

import retrofit2.Response;

import okhttp3.logging.HttpLoggingInterceptor;

public class GitHubGroupProvider implements GroupProvider {

    private final Logger log = Logger.get(GitHubGroupProvider.class);
    private final String apiURL = "https://api.github.com";

    // from config file:
    private final String apiToken;
    private final String githubOrg;

    // set in constructor:
    private final GitHubRest api;

    GitHubGroupProvider(Map<String, String> config) {
        this.apiToken = config.get("github.token");
        this.githubOrg = config.get("github.org");

        this.api = GitHubRetrofit.getClient(
            GitHubRest.class,
            apiURL,
            HttpLoggingInterceptor.Level.BASIC);
    }

    @Override
    public Set<String> getGroups(String user) {
        Set<String> groups = new HashSet<String>();
        // unsure what the classloader logic is for here
        try (ThreadContextClassLoader ignored = new ThreadContextClassLoader(getClass().getClassLoader())) {
            Response<List<Team>> response = api.listTeams("Bearer " + this.apiToken, this.githubOrg, 1000, 1).execute();
            List<Team> teams = response.body();
            for (Team t : teams) {
                String team = t.slug;
                Response<List<User>> mresponse = api.listTeamMembers("Bearer " + this.apiToken, this.githubOrg, team, 1000, 1, "all").execute();
                List<User> users = mresponse.body();
                Set<String> logins = users.stream().map(u -> u.login).collect(Collectors.toSet());
                if (logins.contains(user)) {
                    groups.add(team.replace('-', '_'));
                }
            }
        } catch (Exception e) {
            log.error("ERROR in getGroups:\n%s\n", e.toString());
        }
        log.info("User: [%s], Groups: [%s]", user, groups.toString());
        return groups;
    }
}

