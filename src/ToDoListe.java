
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class ToDoListe extends Application{
	private  ObservableList<String>aufgaben_liste;
	private SortedList<String> sorted;
	private static ToDoListe a;
	
	public ToDoListe() throws IOException {
		String path ="tasks.txt";
		String [] tasks = loadAufgaben(path);
		a = this;
		aufgaben_liste = FXCollections.observableArrayList(tasks);
		sorted = new SortedList<>(aufgaben_liste,Comparator.naturalOrder());
		}
	public static ToDoListe getModel() {
        return a;
    }
	public ObservableList<String> getSortedList(){
		return FXCollections.unmodifiableObservableList(sorted);
	}
		
		public void adding(String a) throws IOException {
			if(!aufgaben_liste.contains(a))
				aufgaben_liste.add(a);
			saveAufgaben(aufgaben_liste);
		}
		
		public void removing(String a) throws IOException {
			aufgaben_liste.remove(a);
			saveAufgaben(aufgaben_liste);
		}
		public void start(Stage primaryStage) throws IOException{
		 FXMLLoader fxmlloader = new FXMLLoader(ToDoListe.class.getResource("liste.fxml"));
	     Parent root = fxmlloader.load();
	     primaryStage.setTitle("To-Do-Liste");
	     primaryStage.setScene(new Scene(root));
	     
	     primaryStage.show();
		}
		public void saveAufgaben(ObservableList<String> list) throws IOException {
			FileOutputStream fos = new FileOutputStream("tasks.txt");
			PrintStream ps = new PrintStream(fos);
			for(int i=0; i < list.size(); i++)
				ps.println(list.get(i));
			ps.close();
		}
		public String[] loadAufgaben(String path) throws IOException {
			List<String> tasks = new ArrayList<String>();
			Path pathTask= Paths.get(System.getProperty("user.home"),"eclipse-workspace","To-Do-Liste","tasks.txt");
			try(BufferedReader br= Files.newBufferedReader(pathTask)){
				String s = null;
				while((s = br.readLine()) != null) {
					tasks.add(s);
					}
				}
			String [] aufgaben = tasks.toArray(new String[0]);
			return aufgaben;
			
		}
	public static void main(String[] args) throws IOException {
		
		launch();
		
	}
}
