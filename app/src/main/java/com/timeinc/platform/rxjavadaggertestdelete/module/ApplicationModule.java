package com.timeinc.platform.rxjavadaggertestdelete.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bparks1271 on 7/5/15.
 */

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    @Provides
    String getHelloWorldString() {
        return "Hello world, from a string provided by ApplicationModule!" +
                "\nThis is where application-level dependencies can be defined." +
                "\nAll you have to do is define an inject method in the ApplicationComponent" +
                "interface and call that method from the class that will use the dependencies!";
    }

}
