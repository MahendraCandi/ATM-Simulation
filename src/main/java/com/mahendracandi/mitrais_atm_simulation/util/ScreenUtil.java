package com.mahendracandi.mitrais_atm_simulation.util;

public class ScreenUtil<T>  {
    private boolean backScreen = false;
    private T payload;

    public boolean isBackScreen() {
        return backScreen;
    }

    public void setBackScreen(boolean backScreen) {
        this.backScreen = backScreen;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
