package com.timeinc.platform.rxjavadaggertestdelete.module;

import com.timeinc.platform.rxjavadaggertestdelete.model.Motor;
import com.timeinc.platform.rxjavadaggertestdelete.model.Vehicle;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by bparks1271 on 7/5/15.
 */

@Module
public class WebModule {

    @Provides
    @Singleton
    Motor provideMotor() {
        return new Motor();
    }

    @Provides @Singleton
    Vehicle provideVehicle() {
        return new Vehicle(new Motor());
    }
}