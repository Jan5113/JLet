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

		Menu mn_file = new Menu("File");
		{
			MenuItem mi_fileOpen = new MenuItem("Open");
			mi_fileOpen.setOnAction(e -> Import());

			MenuItem mi_fileSave = new MenuItem("Save");
			mi_fileSave.setOnAction(e -> Import());

			mn_file.getItems().addAll(mi_fileOpen, mi_fileSave);
		}

		MenuBar mb_main = new MenuBar();
		bp_home.setTop(mb_main);
		BorderPane.setAlignment(mb_main, Pos.TOP_LEFT);
		mb_main.getMenus().addAll(mn_file, mn_import);

		// ================================================

		
		Label lbl_title_home = new Label("JLet - Home");
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

		System.out.println(set_import.termToString(1));
	}
}
