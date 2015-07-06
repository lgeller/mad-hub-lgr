package com.timeinc.platform.rxjavadaggertestdelete.component;

import com.timeinc.platform.rxjavadaggertestdelete.model.WebsiteService;
import com.timeinc.platform.rxjavadaggertestdelete.module.WebModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by bparks1271 on 7/5/15.
 */

@Singleton
@Component (modules = {WebModule.class})
public interface WebComponent {
    WebsiteService provideWebsiteAPI();
}
