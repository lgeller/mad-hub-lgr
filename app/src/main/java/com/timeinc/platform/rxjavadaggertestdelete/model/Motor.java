package com.timeinc.platform.rxjavadaggertestdelete.model;

/**
 * Created by bparks1271 on 7/5/15.
 */
public class Motor {

    private int rpm;

    public Motor() {
        this.rpm = 200;
    }

    public int getRpm() {
        return rpm;
    }

    public void accelerate(int value) {
        rpm = rpm + value;
    }

    public void brake() {
        rpm = 0;
    }
}
