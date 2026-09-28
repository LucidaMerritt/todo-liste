import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.*;



public class Controller {
	private ToDoListe a;
	@FXML
	private Button eingabe;
	@FXML
	private Button erledigt;
	@FXML
	private TextField aufgabe;
	@FXML
	private ListView<String> aufgaben;
	@FXML
	private Button remove;
	
	 @FXML
	  public void initialize() {
		 a = ToDoListe.getModel();
	    if (a != null) {
	            aufgaben.setItems(a.getSortedList());
	        }
	    }
	@FXML
	public void onActionSolved() throws IOException {
		if(a !=null) {
			String selected = aufgaben.getSelectionModel().getSelectedItem();
			if(selected != null) {
				onActionRemove();
				selected += " (done)";
				aufgabe.setText(selected);
				onActionEntered();
				aufgabe.clear();
				
			}
		}
	}
	@FXML
	public void onActionRemove() throws IOException {
		if (a != null) {
            String selected = aufgaben.getSelectionModel().getSelectedItem();
            if (selected != null)
                a.removing(selected);
            }
	}
	
	@FXML
	public void onActionEntered() throws IOException {
		if (a != null) {
            String s = aufgabe.getText().trim();
            if (!s.isEmpty())
            	a.adding(s);
               aufgabe.clear();
		}
	}
}
