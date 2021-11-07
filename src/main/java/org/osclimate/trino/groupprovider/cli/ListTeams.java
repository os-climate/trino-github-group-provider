package org.osclimate.trino.groupprovider.cli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import org.osclimate.trino.groupprovider.github.GitHubService;

class ListTeams {
    public static void main(String[] args) {
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
        GitHubService api = getService(GitHubService.class, "https://api.github.com");
    }

    // this should eventually be in its own class so it is reusable
    public static <T> T getService(Class<T> type, String url) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(interceptor);

        return new Retrofit.Builder()
            .baseUrl(url)
            .client(clientBuilder.build())
            .addConverterFactory(JacksonConverterFactory.create(
                new ObjectMapper()
                    .registerModule(new Jdk8Module())
                    .registerModule(new JavaTimeModule())))
            .build()
            .create(type);
    }

}
