package com.braillevision.camera;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class CameraService {
    private VideoCapture capture;
    private boolean isRunning = false;
    private Thread cameraThread;
    private FrameListener listener;

    public interface FrameListener {
        void onFrame(Mat frame);
    }

    public void setListener(FrameListener listener) {
        this.listener = listener;
    }

    public void start(int cameraId) {
        if (isRunning) return;

        capture = new VideoCapture(cameraId);
        if (!capture.isOpened()) {
            System.err.println("Failed to open camera " + cameraId);
            return;
        }

        isRunning = true;
        cameraThread = new Thread(() -> {
            Mat frame = new Mat();
            while (isRunning) {
                if (capture.read(frame)) {
                    if (listener != null) {
                        listener.onFrame(frame.clone()); // Pass a clone to avoid concurrent modification
                    }
                }
                try {
                    Thread.sleep(30); // ~33 FPS limit to avoid maxing out CPU
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            frame.release();
        });
        cameraThread.setDaemon(true);
        cameraThread.start();
    }

    public void stop() {
        isRunning = false;
        if (cameraThread != null) {
            try {
                cameraThread.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        if (capture != null && capture.isOpened()) {
            capture.release();
        }
    }
}
