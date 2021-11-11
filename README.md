# Overview

An implementation of Trino Group Provider, which will map GitHub org teams to correspondin trino user groups.

## Build

### build plugin jar files
```sh
$ cd /path/to/plugin/repo
$ mvn clean package
```

### build and push image
- Be sure to do this after a successful package build above.
- Specify your preferred image registry and username. 
```sh
$ podman login -u your_user_name your.registry.com 
$ mvn -Dpush.registry=your.registry.com -Dpush.user=your_user_name -pl plugin podman:build
$ mvn -Dpush.registry=your.registry.com -Dpush.user=your_user_name -pl plugin podman:push
```

## cli

```
$ export GITHUB_API_TOKEN=your_token

$ java -cp cli/target/osc-github-rest-cli-0.1.0-jar-with-dependencies.jar org.osclimate.githubrest.cli.ListTeams os-climate
Nov 07, 2021 1:51:14 PM okhttp3.internal.platform.Platform log
INFO: --> GET https://api.github.com/orgs/os-climate/teams?per_page=1000&page=1
Nov 07, 2021 1:51:14 PM okhttp3.internal.platform.Platform log
INFO: <-- 200 https://api.github.com/orgs/os-climate/teams?per_page=1000&page=1 (649ms, unknown-length body)
team: aicoe-osc-demo
team: corporate-data-pipeline-admin
team: corporate-data-pipeline-developers
team: entity-matching
team: itr-data-pipeline-admin
team: itr-data-pipeline-developers
team: odh-env-users
team: os-climate-corporate-data-project-team
team: os-climate-data-commons-project-team
team: os-climate-itr-tool-project-team
team: os-climate-physical-risk-tool-project
team: physrisk-data-pipeline-admin
team: physrisk-data-pipeline-developers
```

### plugin configuration file

Create `<path_to_trino_config>/group-provider.properties` with the following required parameters, e.g.:

```
group-provider.name=osc-github-gp
# add parameters when I know what they are
```

