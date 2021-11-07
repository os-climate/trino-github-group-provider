# Overview

An implementation of Trino Group Provider, which will map GitHub org teams to correspondin trino user groups.

## Build


```
mvn clean package
```

## cli

```
$ java -cp target/trino-group-provider-osc-github-gp-0.1.0-jar-with-dependencies.jar org.osclimate.trino.groupprovider.cli.ListTeams os-climate
```

## Deploy

### Copy artifacts

Copy the following artifacts from build or from release to the Trino plugin folder (`<path_to_trino>/plugin/osc-github-gp/`)

```
target/trino-group-provider-osc-github-gp-0.1.0/*.jar
```

### Prepare configuration file

Create `<path_to_trino_config>/group-provider.properties` with the following required parameters, e.g.:

```
group-provider.name=osc-github-gp
# add parameters when I know what they are
```

#### Optional Parameters

| Configuration                            | Default  | Description                |
| ---------------------------------------- | -------- | -------------------------- |
| x | x | x |

