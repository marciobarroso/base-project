package com.icodeuplay.base.view.javafx.common;

import com.icodeuplay.base.common.utils.MessageUtils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

	public static void show(String message) {
		String title = MessageUtils.getString("app.alert.box.title");

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setResizable(false);

		BorderPane root = new BorderPane();

		HBox center = new HBox();
		center.setAlignment(Pos.CENTER);

		Label label = new Label(message);
		label.setPadding(new Insets(10, 10, 10, 10));
		label.setTextAlignment(TextAlignment.JUSTIFY);
		label.setMaxWidth(250);
		label.setWrapText(true);

		center.getChildren().add(label);
		root.setCenter(center);

		Button button = new Button(MessageUtils.getString("app.label.close"));
		button.setOnAction(e -> window.close());

		HBox bottom = new HBox();
		bottom.setAlignment(Pos.CENTER);
		bottom.getChildren().add(button);
		root.setBottom(bottom);

		HBox.setMargin(button, new Insets(0, 10, 10, 10));

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.showAndWait();
	}

}
