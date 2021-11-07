package org.osclimate.trino.groupprovider.github;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class GitHubApiClient {
    public static <T> T getClient(Class<T> type, String url) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
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

