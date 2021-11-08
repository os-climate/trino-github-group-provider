package org.osclimate.githubrest.cli;

import org.osclimate.githubrest.model.Team;
import org.osclimate.githubrest.GitHubRetrofit;
import org.osclimate.githubrest.GitHubRest;

import java.util.List;

class ListTeams {
    public static void main(String[] args) throws Exception {
	if (args.length != 1) {
	    System.out.printf("usage: ListTeams <github-org-name>\n");
	    System.exit(1);
	}
	String org = args[0];
	String apiToken = System.getenv("GITHUB_API_TOKEN");
        if (apiToken == null) {
	    System.out.printf("environment variable GITHUB_API_TOKEN must be set\n");
	    System.exit(1);
	}
	System.out.printf("teams for github org %s\n", org);
        GitHubRest api = GitHubRetrofit.getClient(GitHubRest.class, "https://api.github.com");
	retrofit2.Response<List<Team>> response = api.listTeams("Bearer " + apiToken, org, 1000, 1).execute();
        List<Team> teams = response.body();
	for (Team t : teams) {
	    System.out.printf("team: %s\n", t.slug);
	}
    }
}
