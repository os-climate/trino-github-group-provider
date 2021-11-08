package org.osclimate.githubrest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public final String login;
    public final long id;
    public final String avatarUrl;
    public final String gravatarId;
    public final String type;
    public final boolean siteAdmin;
    public final String name;
    public final String company;
    public final String blog;
    public final String location;
    public final String email;
    public final boolean hireable;
    public final String bio;
    public final String twitterUsername;
    public final long publicRepos;
    public final long publicGists;
    public final long followers;
    public final long following;
    public final ZonedDateTime createdAt;
    public final ZonedDateTime updatedAt;

    public User(
            @JsonProperty("login") String login,
            @JsonProperty("id") long id,
            @JsonProperty("avatar_url") String avatarUrl,
            @JsonProperty("gravatar_id") String gravatarId,
            @JsonProperty("type") String type,
            @JsonProperty("site_admin") boolean siteAdmin,
            @JsonProperty("name") String name,
            @JsonProperty("company") String company,
            @JsonProperty("blog") String blog,
            @JsonProperty("location") String location,
            @JsonProperty("email") String email,
            @JsonProperty("hireable") boolean hireable,
            @JsonProperty("bio") String bio,
            @JsonProperty("twitter_username") String twitterUsername,
            @JsonProperty("public_repos") long publicRepos,
            @JsonProperty("public_gists") long publicGists,
            @JsonProperty("followers") long followers,
            @JsonProperty("following") long following,
            @JsonProperty("created_at") ZonedDateTime createdAt,
            @JsonProperty("updated_at") ZonedDateTime updatedAt)
    {
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.gravatarId = gravatarId;
        this.type = type;
        this.siteAdmin = siteAdmin;
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.location = location;
        this.email = email;
        this.hireable = hireable;
        this.bio = bio;
        this.twitterUsername = twitterUsername;
        this.publicRepos = publicRepos;
        this.publicGists = publicGists;
        this.followers = followers;
        this.following = following;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

