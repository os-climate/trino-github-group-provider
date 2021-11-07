package org.osclimate.trino.groupprovider.github.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {
    public final String name;
    public final long id;
    public final String nodeId;
    public final String slug;
    public final String description;
    public final String privacy;
    public final String url;
    public final String htmlUrl;
    public final String membersUrl;
    public final String repositoriesUrl;
    public final String permission;
    public final Team parent;

    public Team(
        @JsonProperty("name") String name,
	@JsonProperty("id") long id,
	@JsonProperty("node_id") String nodeId,
	@JsonProperty("slug") String slug,
	@JsonProperty("description") String description,
	@JsonProperty("privacy") String privacy,
	@JsonProperty("url") String url,
	@JsonProperty("html_url") String htmlUrl,
	@JsonProperty("members_url") String membersUrl,
	@JsonProperty("repositories_url") String repositoriesUrl,
	@JsonProperty("permission") String permission,
	@JsonProperty("parent") Team parent
    ) {
        this.name = name;
	this.id = id;
	this.nodeId = nodeId;
	this.slug = slug;
	this.description = description;
	this.privacy = privacy;
	this.url = url;
	this.htmlUrl = htmlUrl;
	this.membersUrl = membersUrl;
	this.repositoriesUrl = repositoriesUrl;
	this.permission = permission;
	this.parent = parent;
    }
}
