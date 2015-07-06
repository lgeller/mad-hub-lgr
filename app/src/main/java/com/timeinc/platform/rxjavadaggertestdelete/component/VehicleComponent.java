package com.timeinc.platform.rxjavadaggertestdelete.component;

import com.timeinc.platform.rxjavadaggertestdelete.model.Vehicle;
import com.timeinc.platform.rxjavadaggertestdelete.module.VehicleModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by bparks1271 on 7/5/15.
 */
@Singleton
@Component(modules = {VehicleModule.class})
public interface VehicleComponent {
    Vehicle provideVehicle();
}

