import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Import {
	Set newSet = new Set();
	Stage window = new Stage();

	TextField tf_setName = new TextField();
	TextArea ta_setTerms = new TextArea();
	Button btn_import = new Button("Import");

	public Set importSet() {

		initialize();

		window.showAndWait();

		return newSet;
	}

	private void initialize() {
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("JLet - Import new Terms");

		BorderPane layout = new BorderPane();
		tf_setName.setPromptText("Set name");
		ta_setTerms.setPromptText("Insert terms");

		layout.setTop(tf_setName);
		layout.setCenter(ta_setTerms);
		layout.setBottom(btn_import);

		BorderPane.setAlignment(btn_import, Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(btn_import, new Insets(12.0, 12.0, 12.0, 12.0));
		BorderPane.setMargin(ta_setTerms, new Insets(12.0, 12.0, 12.0, 12.0));
		BorderPane.setMargin(tf_setName, new Insets(12.0, 12.0, 12.0, 12.0));

		btn_import.setOnAction(e -> importTerms());
		btn_import.setDisable(true);

		ta_setTerms.textProperty().addListener((obsV, oldV, newV) -> changeTF());
		tf_setName.textProperty().addListener((obsV, oldV, newV) -> changeTF());

		Scene scene = new Scene(layout);

		window.setScene(scene);
	}

	private void importTerms() {
		newSet.name = tf_setName.getText();

		String[] input_rows = ta_setTerms.getText().split("\n");

		for (String input_row : input_rows) {
			newSet.addTerm(input_row.split("\t"));
		}
		this.window.close();
	}

	private void changeTF() {
		if (!tf_setName.getText().isEmpty() && ta_setTerms.getText().split("\n").length > 0
				&& ta_setTerms.getText().split("\n")[0].split("\t").length > 1) { // ensure at least 1 term

			btn_import.setDisable(false);
		} else {
			btn_import.setDisable(true);
		}
	}
}