package com.braillevision.ui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CameraPanel extends VBox {
    private final ImageView imageView;
    private final Label fpsLabel;

    public CameraPanel() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #222222; -fx-padding: 10;");

        imageView = new ImageView();
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(640);
        imageView.setFitHeight(480);

        fpsLabel = new Label("FPS: 0");
        fpsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        this.getChildren().addAll(imageView, fpsLabel);
    }

    public void updateFrame(Image image, double fps) {
        Platform.runLater(() -> {
            if (image != null) {
                imageView.setImage(image);
            }
            fpsLabel.setText(String.format("FPS: %.1f", fps));
        });
    }
}
