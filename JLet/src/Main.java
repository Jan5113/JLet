import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	Stage window;
	Scene scn_home;
	Insets ins_statard = new Insets(12, 12, 12, 12);
	String lbl = "\n Hi there!";
	Label lbl_title_home;
	Set set_active = new Set();

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {

		Button btn_import;
		BorderPane bp_home = new BorderPane();

		window = primaryStage;
		window.setTitle("JLet");

		scn_home = new Scene(bp_home, 800, 600);
		window.setScene(scn_home);
		

		
		// ================================================
		// SETUP MENUBAR

		Menu mn_import = new Menu("Import");
		{
			MenuItem mi_importText = new MenuItem("Text");
			mi_importText.setOnAction(e -> Import());

			MenuItem mi_importFile = new MenuItem("File");
			mi_importFile.setOnAction(e -> Import());

			mn_import.getItems().addAll(mi_importFile, mi_importText);
		}

		Menu mn_set = new Menu("Set");
		{
			MenuItem mi_setOpen = new MenuItem("Open");
			mi_setOpen.setOnAction(e -> Import());

			MenuItem mi_setSave = new MenuItem("Save");
			mi_setSave.setOnAction(e -> Import());

			mn_set.getItems().addAll(mi_setOpen, mi_setSave);
		}

		MenuBar mb_main = new MenuBar();
		bp_home.setTop(mb_main);
		BorderPane.setAlignment(mb_main, Pos.TOP_LEFT);
		mb_main.getMenus().addAll(mn_set, mn_import);

		// ================================================

		
		lbl_title_home = new Label("JLet - Home" + lbl);
		bp_home.setCenter(lbl_title_home);
		BorderPane.setAlignment(lbl_title_home, Pos.CENTER);
		BorderPane.setMargin(lbl_title_home, ins_statard);

		btn_import = new Button("Import");
		bp_home.setBottom(btn_import);
		BorderPane.setAlignment(btn_import, Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(btn_import, ins_statard);

		window.show();

		btn_import.setOnAction(e -> Import());

	}

	private void Import() {
		Set set_import = new Import().importSet();
		if (!set_import.isEmpty()) {
			set_active = set_import;
		}
		
		updateInfoLbl();
	}
	
	private void updateInfoLbl() {
		if (!set_active.isEmpty()) {
			lbl_title_home.setText(set_active.setToString());
		} else {
			lbl_title_home.setText("No active Set");
		}
	}
}