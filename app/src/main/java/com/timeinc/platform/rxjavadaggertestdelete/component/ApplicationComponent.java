package com.timeinc.platform.rxjavadaggertestdelete.component;

import com.timeinc.platform.rxjavadaggertestdelete.LibraryDemoFragment;
import com.timeinc.platform.rxjavadaggertestdelete.MainActivity;
import com.timeinc.platform.rxjavadaggertestdelete.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by bparks1271 on 7/5/15.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MainActivity activity);
    void inject(LibraryDemoFragment fragment);
}
