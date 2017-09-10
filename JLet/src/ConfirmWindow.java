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

public class ConfirmWindow {

	static boolean answer;
	static Insets ins_standard = new Insets(12, 12, 12, 12);

	public static boolean display(String title, String message) {
		Stage window = new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText(message);
		label.setTextAlignment(TextAlignment.CENTER);

		Button btn_ok = new Button("OK");
		Button btn_cancel = new Button("Cancel");
		
		btn_ok.setOnAction(e -> {
			answer = true;
			window.close();
		});
		
		btn_cancel.setOnAction(e -> {
			answer = false;
			window.close();
		});
		
		BorderPane root = new BorderPane();
		root.setCenter(label);
		BorderPane.setAlignment(label, Pos.CENTER);
		BorderPane.setMargin(label, ins_standard);
		HBox hb_buttons = new HBox(40);
		hb_buttons.getChildren().addAll(btn_ok, btn_cancel);
		hb_buttons.setAlignment(Pos.CENTER);
		root.setBottom(hb_buttons);
		BorderPane.setMargin(hb_buttons, ins_standard);
		
		Scene scene = new Scene(root);
		window.setScene(scene);
		btn_cancel.requestFocus();
		window.showAndWait();
		
		return answer;
	}

}
