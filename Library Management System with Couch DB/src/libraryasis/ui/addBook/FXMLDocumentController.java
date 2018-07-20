/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryasis.ui.addBook;
import com.fourspaces.couchdb.Document;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static libraryasis.ui.addBook.Databasehandler.studentCouchDb;
import static libraryasis.ui.addBook.DisplayData.ID1;
import static libraryasis.ui.addBook.DisplayData.author1;
import static libraryasis.ui.addBook.DisplayData.isAvail1;
import static libraryasis.ui.addBook.DisplayData.publisher1;
import static libraryasis.ui.addBook.DisplayData.title1;

/**
 *
 * @author HP
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField Title;
    @FXML
    private TextField Id;
    @FXML
    private TextField author;
    @FXML
    private TextField publisher;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    Databasehandler databasehandler;
    @FXML
    private AnchorPane rootPane;
    DisplayData displaydata;
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        databasehandler=Databasehandler.getInstance();
       // displaydata=new DisplayData();
       // checkdata();
    }    

    @FXML
    private void addBook(ActionEvent event) {
        String BookId=Id.getText();
        String BookTitle=Title.getText();
        String BookAuthor=author.getText();
        String BookPublisher=publisher.getText();
        
        if(BookId.isEmpty()||BookTitle.isEmpty()||BookAuthor.isEmpty()||BookPublisher.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
        }
         Document newdoc = new Document();
  
  /*Map for list of properties for the new document*/
  Map<String , String> properties = new HashMap<String,String>();
  properties.put(ID1,BookId);
 properties.put(title1,BookTitle);
  properties.put(author1,BookAuthor);
   properties.put(publisher1,BookPublisher);
  properties.put(isAvail1,"true");
  
  newdoc.putAll(properties);
  if(databasehandler.setdocument(newdoc))
  {
      Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setHeaderText(null);
            alert1.setContentText("Success");
            alert1.showAndWait();
    }
else //Error
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
        }
    }
    @FXML
    private void cancelBook(ActionEvent event) {
         Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
    public void checkdata()
    {
        displaydata.Display();
    }
    
}
