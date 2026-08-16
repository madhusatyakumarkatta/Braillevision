package com.braillevision.ui;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ResultPanel extends VBox {
    private final Label titleLabel;
    private final Label textLabel;

    public ResultPanel() {
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);
        this.setStyle("-fx-background-color: #333333; -fx-padding: 20;");
        this.setPrefWidth(300);

        titleLabel = new Label("Translation Output:");
        titleLabel.setStyle("-fx-text-fill: #cccccc; -fx-font-size: 18px; -fx-font-weight: bold;");

        textLabel = new Label("");
        textLabel.setFont(Font.font("Arial", 48));
        textLabel.setStyle("-fx-text-fill: #00ff00;");
        textLabel.setWrapText(true);

        this.getChildren().addAll(titleLabel, textLabel);
    }

    public void updateText(String text) {
        Platform.runLater(() -> {
            textLabel.setText(text);
        });
    }
}
