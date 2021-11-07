package org.osclimate.trino.groupprovider.github;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

import org.osclimate.trino.groupprovider.github.model.Team;
import org.osclimate.trino.groupprovider.github.model.User;

public interface GitHubService {
    // https://docs.github.com/en/enterprise-cloud@latest/rest/reference/teams#list-teams
    @Headers("accept: application/vnd.github.v3+json")
    @GET("/orgs/{org}/teams")
    Call<List<Team>> listTeams(
        @Header("Authorization") String auth,
        @Path("org") String org,
        @Query("per_page") int perPage,
        @Query("page") int page
    );

    // https://docs.github.com/en/enterprise-cloud@latest/rest/reference/teams#list-team-members
    @Headers("accept: application/vnd.github.v3+json")
    @GET("/orgs/{org}/teams/{team_slug}/members")
    Call<List<User>> listTeamMembers(
        @Header("Authorization") String auth,
        @Path("org") String org,
        @Path("team_slug") String teamSlug,
        @Query("per_page") int perPage,
        @Query("page") int page,
	@Query("role") String role
    );
}

