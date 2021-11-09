package org.osclimate.githubrest.cli;

import org.osclimate.githubrest.model.Team;
import org.osclimate.githubrest.model.User;
import org.osclimate.githubrest.GitHubRetrofit;
import org.osclimate.githubrest.GitHubRest;

import retrofit2.Response;

import okhttp3.logging.HttpLoggingInterceptor;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

class UserTeams {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.printf("usage: UserTeams <github-org-name> <github-user-name>\n");
            System.exit(1);
        }
        String org = args[0];
        String user = args[1];

        String apiToken = System.getenv("GITHUB_API_TOKEN");
        if (apiToken == null) {
            System.out.printf("environment variable GITHUB_API_TOKEN must be set\n");
            System.exit(1);
        }

        GitHubRest api = GitHubRetrofit.getClient(
            GitHubRest.class,
            "https://api.github.com",
            HttpLoggingInterceptor.Level.NONE);

        Response<List<Team>> response = api.listTeams("Bearer " + apiToken, org, 1000, 1).execute();
        List<Team> teams = response.body();
        List<String> userTeams = new ArrayList<>();
        for (Team t : teams) {
            Response<List<User>> mresponse = api.listTeamMembers("Bearer " + apiToken, org, t.slug, 1000, 1, "all").execute();
            List<User> users = mresponse.body();
            List<String> logins = users.stream().map(u -> u.login).collect(Collectors.toList());
            if (logins.contains(user)) {
                userTeams.add(t.slug);
            }
        }
        System.out.printf("teams for user %s in org %s:\n", user, org);
        for (String t : userTeams) {
            System.out.printf("    %s\n", t);
        }
    }
}

