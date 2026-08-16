package com.braillevision.utils;

public class FPSCounter {
    private long lastTime;
    private int frames;
    private double currentFps;

    public FPSCounter() {
        lastTime = System.currentTimeMillis();
        frames = 0;
        currentFps = 0.0;
    }

    public void update() {
        long currentTime = System.currentTimeMillis();
        frames++;
        if (currentTime - lastTime >= 1000) {
            currentFps = frames * 1000.0 / (currentTime - lastTime);
            frames = 0;
            lastTime = currentTime;
        }
    }

    public double getFps() {
        return currentFps;
    }
}
