package org.osclimate.trino.groupprovider.github;

import io.trino.spi.Plugin;
import io.trino.spi.security.GroupProviderFactory;

import java.util.Collections;

public final class GitHubGroupProviderPlugin implements Plugin {

    @Override
    public Iterable<GroupProviderFactory> getGroupProviderFactories() {
        return Collections.singletonList(new GitHubGroupProviderFactory());
    }
}
