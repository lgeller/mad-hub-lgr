package com.timeinc.platform.rxjavadaggertestdelete;

import android.app.Application;

import com.timeinc.platform.rxjavadaggertestdelete.component.ApplicationComponent;
import com.timeinc.platform.rxjavadaggertestdelete.component.DaggerApplicationComponent;
import com.timeinc.platform.rxjavadaggertestdelete.module.ApplicationModule;

/**
 * Created by bparks1271 on 7/5/15.
 */
public class MainApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static ApplicationComponent getApplicationComponent(Application mainApplication) {
        if (mainApplication instanceof MainApplication) {
            return ((MainApplication) mainApplication).getApplicationComponent();
        }

        return null;
    }
}
