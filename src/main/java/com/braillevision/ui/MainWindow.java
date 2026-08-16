package com.braillevision.ui;

import com.braillevision.camera.CameraService;
import com.braillevision.model.Cell;
import com.braillevision.model.Dot;
import com.braillevision.speech.SpeechEngine;
import com.braillevision.utils.FPSCounter;
import com.braillevision.utils.ImageUtils;
import com.braillevision.vision.FrameProcessor;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class MainWindow extends BorderPane {
    private final CameraPanel cameraPanel;
    private final ResultPanel resultPanel;
    private final CameraService cameraService;
    private final FrameProcessor frameProcessor;
    private final SpeechEngine speechEngine;
    private final FPSCounter fpsCounter;

    private boolean isDetecting = true;
    private String lastText = "";

    public MainWindow() {
        cameraPanel = new CameraPanel();
        resultPanel = new ResultPanel();
        cameraService = new CameraService();
        frameProcessor = new FrameProcessor();
        speechEngine = new SpeechEngine();
        fpsCounter = new FPSCounter();

        this.setCenter(cameraPanel);
        this.setRight(resultPanel);

        // Bottom Controls
        HBox bottomBar = new HBox(10);
        bottomBar.setPadding(new Insets(10));
        bottomBar.setStyle("-fx-background-color: #111111;");

        Button btnStart = new Button("Start Camera");
        Button btnStop = new Button("Stop Camera");
        Button btnToggle = new Button("Pause Detection");

        btnStart.setOnAction(e -> cameraService.start(0));
        btnStop.setOnAction(e -> cameraService.stop());
        btnToggle.setOnAction(e -> {
            isDetecting = !isDetecting;
            btnToggle.setText(isDetecting ? "Pause Detection" : "Resume Detection");
        });

        bottomBar.getChildren().addAll(btnStart, btnStop, btnToggle);
        this.setBottom(bottomBar);

        setupCameraListener();
    }

    private void setupCameraListener() {
        cameraService.setListener(frame -> {
            fpsCounter.update();
            
            if (isDetecting) {
                FrameProcessor.ProcessedFrame result = frameProcessor.process(frame);
                
                // Draw dots and cells on the frame
                for (Dot dot : result.dots) {
                    Imgproc.circle(frame, new Point(dot.getX(), dot.getY()), (int)dot.getRadius(), new Scalar(0, 255, 0), 2);
                }
                
                for (Cell cell : result.cells) {
                    Imgproc.rectangle(frame, 
                            new Point(cell.getBoundingBoxX(), cell.getBoundingBoxY()), 
                            new Point(cell.getBoundingBoxX() + cell.getBoundingBoxWidth(), cell.getBoundingBoxY() + cell.getBoundingBoxHeight()), 
                            new Scalar(255, 0, 0), 2);
                    Imgproc.putText(frame, cell.getBinaryPattern(), 
                            new Point(cell.getBoundingBoxX(), cell.getBoundingBoxY() - 10), 
                            Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(0, 0, 255), 1);
                }

                if (!result.text.isEmpty()) {
                    resultPanel.updateText(result.text);
                    if (!result.text.equals(lastText)) {
                        lastText = result.text;
                        speechEngine.speak(result.text);
                    }
                }
            }

            cameraPanel.updateFrame(ImageUtils.mat2Image(frame), fpsCounter.getFps());
            frame.release();
        });
    }

    public void shutdown() {
        cameraService.stop();
    }
}
