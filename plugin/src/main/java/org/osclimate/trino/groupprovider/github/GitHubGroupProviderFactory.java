package org.osclimate.trino.groupprovider.github;

import io.trino.spi.security.GroupProvider;
import io.trino.spi.security.GroupProviderFactory;

import java.util.Map;

public class GitHubGroupProviderFactory implements GroupProviderFactory {

    @Override
    public String getName() {
        return "osc-github-gp";
    }

    @Override
    public GroupProvider create(Map<String, String> config) {
        //if (config.isEmpty()) {
        //    throw new IllegalArgumentException("this group provider requires configuration properties");
        //}
        return new GitHubGroupProvider(config);
    }
}
