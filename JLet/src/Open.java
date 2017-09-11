import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
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
	private TableView<Set> tbl_files;
	private Set[] sets;
	private int selectedIndex = 0;

	@SuppressWarnings("unchecked")
	public Set openSet() {
		
		initialize();
		
		sets = SetIO.getSets();
		Arrays.sort(sets);
		String[] fileNames = new String[sets.length];
		
		for (int i = 0; i < sets.length; i++) {
			fileNames[i] = sets[i].name + " " + sets[i].getRecentUseStr();
		}
		
		TableColumn<Set, String> tclm_setName = new TableColumn<>("Set");
		tclm_setName.setPrefWidth(300);
		tclm_setName.setCellValueFactory(new PropertyValueFactory<Set, String>("Name"));

		TableColumn<Set, String> tclm_setSize = new TableColumn<>("Cards");
		tclm_setSize.setPrefWidth(50);
		tclm_setSize.setCellValueFactory(new PropertyValueFactory<Set, String>("SizeStr"));
		
		TableColumn<Set, String> tclm_setUsed = new TableColumn<>("Last Used");
		tclm_setUsed.setPrefWidth(110);
		tclm_setUsed.setCellValueFactory(new PropertyValueFactory<Set, String>("RecentUseStr"));
		
		ObservableList<Set> tableSets = FXCollections.observableArrayList(sets);

		tbl_files.setItems(tableSets);
		tbl_files.getColumns().addAll(tclm_setName, tclm_setSize, tclm_setUsed);
		
		window.showAndWait();

		return newSet;
	}

	private void initialize() {
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("JLet - Open Set");
		window.getIcons().add(new Image("file:icon.png"));

		BorderPane layout = new BorderPane();

		tbl_files = new TableView<Set>();
		layout.setCenter(tbl_files);
		layout.setBottom(btn_open);

		BorderPane.setAlignment(tbl_files, Pos.CENTER);
		BorderPane.setMargin(tbl_files, ins_standard);

		BorderPane.setAlignment(btn_open, Pos.BOTTOM_RIGHT);
		BorderPane.setMargin(btn_open, ins_standard);

		btn_open.setOnAction(e -> open());
		btn_open.setDisable(true);

		tbl_files.getSelectionModel().selectedItemProperty().addListener((obs_val, old_val, new_val) -> changeSelection());
		tbl_files.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
		newSet = sets[selectedIndex];

		this.window.close();
	}

	private void changeSelection() {
		if (!tbl_files.getSelectionModel().isEmpty()){
			selectedIndex = tbl_files.getSelectionModel().getSelectedIndex();
			btn_open.setDisable(false);
		} else {
			btn_open.setDisable(true);
		}
	}
}