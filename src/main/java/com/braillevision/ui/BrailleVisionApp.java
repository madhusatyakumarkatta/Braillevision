package com.braillevision.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nu.pattern.OpenCV;

public class BrailleVisionApp extends Application {
    private MainWindow mainWindow;

    @Override
    public void init() {
        // Load OpenCV native library
        OpenCV.loadShared();
    }

    @Override
    public void start(Stage primaryStage) {
        mainWindow = new MainWindow();
        Scene scene = new Scene(mainWindow, 1000, 600);
        
        primaryStage.setTitle("BrailleVision - Real-Time Braille Reader");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> {
            mainWindow.shutdown();
            System.exit(0);
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
