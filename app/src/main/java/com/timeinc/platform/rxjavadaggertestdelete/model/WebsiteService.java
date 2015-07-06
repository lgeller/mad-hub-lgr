package com.timeinc.platform.rxjavadaggertestdelete.model;

import java.util.List;

import javax.inject.Inject;

import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by bparks1271 on 7/5/15.
 *
 * Modified from https://github.com/square/retrofit/blob/master/samples/src/main/java/com/example/retrofit/SimpleService.java
 */
public final class WebsiteService {

    public GitHub getGithub() {
        return github;
    }

    GitHub github;

    @Inject
    public WebsiteService() {
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .build();

        // Create an instance of our GitHub API interface.
        github = retrofit.create(GitHub.class);
    }

    public static final String API_URL = "https://api.github.com";

    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Observable<List<Contributor>> contributors(
                @Path("owner") String owner,
                @Path("repo") String repo);
    }

    //Taken from: https://gist.github.com/jooyunghan/f1936ba83d2bb6181a1e
    private static <T> Observable.Operator<T, List<T>> flattenList() {
        return new Observable.Operator<T, List<T>>() {
            @Override
            public Subscriber<? super List<T>> call(final Subscriber<? super T> subscriber) {
                return new Subscriber<List<T>>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(List<T> contributors) {
                        for (T c: contributors)
                            subscriber.onNext(c);
                    }
                };
            }
        };
    }
}
