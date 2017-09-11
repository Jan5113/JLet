import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Open {
	private Set newSet = new Set();
	private Stage window = new Stage();

	private Button btn_open = new Button("Open");
	private Insets ins_standard = new Insets(12, 12, 12, 12);
	private ListView<String> list;
	private File[] files;
	private int selectedIndex = 0;

	public Set openSet() {
		
		initialize();
		
		files = SetIO.getFiles();
		String[] fileNames = new String[files.length];
		
		for (int i = 0; i < files.length; i++) {
			fileNames[i] = files[i].getName();
		}
		
		ObservableList<String> items = FXCollections.observableArrayList(fileNames);

		list.setItems(items);
		
		window.showAndWait();

		return newSet;
	}

	private void initialize() {
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("JLet - Open Set");

		BorderPane layout = new BorderPane();

		list = new ListView<>();
		layout.setCenter(list);
		layout.setBottom(btn_open);

		BorderPane.setAlignment(list, Pos.CENTER);
		BorderPane.setMargin(list, ins_standard);

		BorderPane.setAlignment(btn_open, Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(btn_open, ins_standard);

		btn_open.setOnAction(e -> open());
		btn_open.setDisable(true);

		list.getSelectionModel().selectedItemProperty().addListener((obs_val, old_val, new_val) -> changeSelection());
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
		        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
		            if(mouseEvent.getClickCount() == 2){
		                open();
		            }
		        }
		    }
		});

		Scene scene = new Scene(layout);

		window.setScene(scene);
	}

	private void open() {
		newSet = SetIO.defaultOpen(files[selectedIndex]);

		this.window.close();
	}

	private void changeSelection() {
		if (!list.getSelectionModel().isEmpty()){
			selectedIndex = list.getSelectionModel().getSelectedIndex();
			btn_open.setDisable(false);
		} else {
			btn_open.setDisable(true);
		}
	}
}