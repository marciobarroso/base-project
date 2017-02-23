package com.icodeuplay.base.view.javafx.common;

import com.icodeuplay.base.common.utils.MessageUtils;
import com.icodeuplay.base.view.javafx.utils.ScreenUtils;

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

public class ConfirmBox {

	private static boolean answer = false;

	public static boolean show(String message) {
		String title = MessageUtils.getString("app.confirm.box.title");

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

		Button yes = new Button(MessageUtils.getString("app.label.yes"));
		yes.setOnAction(e -> {
			answer = true;
			window.close();
		});

		Button no = new Button(MessageUtils.getString("app.label.no"));
		no.setOnAction(e -> {
			answer = false;
			window.close();
		});

		HBox bottom = new HBox();
		bottom.setAlignment(Pos.CENTER);
		bottom.getChildren().addAll(yes, no);
		root.setBottom(bottom);

		HBox.setMargin(yes, new Insets(0, 10, 10, 10));
		HBox.setMargin(no, new Insets(0, 10, 10, 10));

		Scene scene = new Scene(root);
		window.setScene(scene);
		window.showAndWait();

		ScreenUtils.centralize(window);

		return answer;
	}

}
