package org.osclimate.trino.groupprovider.github;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableMap;
import io.trino.spi.classloader.ThreadContextClassLoader;
import io.trino.spi.security.GroupProvider;

import javax.naming.AuthenticationException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import io.airlift.log.Logger;
import io.airlift.units.Duration;

import static javax.naming.Context.*;

public class GitHubGroupProvider implements GroupProvider {

    private static final Logger log = Logger.get(GitHubGroupProvider.class);

    GitHubGroupProvider(Map<String, String> config) {
	// load structure parameters from config
        //this.maxRetryCount = config.getOrDefault("ldap.max-retry-count", "5");
    }

    @Override
    public Set<String> getGroups(String user)  {
	// unsure what the classloader logic is for here
	try (ThreadContextClassLoader ignored = new ThreadContextClassLoader(getClass().getClassLoader())) {
            // logic to fill groups from github will go here
	    Set<String> groups = new HashSet<String>();
            log.debug("User: [%s], Groups: [%s]", user, groups.toString());
            return groups;
        }
    }
}

