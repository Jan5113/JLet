import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertWindow {

	static Insets ins_standard = new Insets(12, 12, 12, 12);

	public static void display(String title, String message) {
		Stage window = new Stage();
		window.getIcons().add(new Image("file:icon.png"));
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		
		Label label = new Label();
		label.setText(message);
		label.setTextAlignment(TextAlignment.CENTER);

		Button btn_ok = new Button("OK");	
		
		btn_ok.setOnAction(e -> window.close());
		
		BorderPane root = new BorderPane();
		root.setCenter(label);
		BorderPane.setAlignment(label, Pos.CENTER);
		BorderPane.setMargin(label, ins_standard);
		root.setBottom(btn_ok);
		BorderPane.setAlignment(btn_ok, Pos.CENTER);
		BorderPane.setMargin(btn_ok, ins_standard);
		
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.showAndWait();
	}

}
