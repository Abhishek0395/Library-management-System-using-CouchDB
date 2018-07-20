/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.asssistant.ui.addMember;

import com.fourspaces.couchdb.Document;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import libraryasis.ui.addBook.Databasehandler;
import static libraryasis.ui.addBook.Databasehandler.Contact;
import static libraryasis.ui.addBook.Databasehandler.Email;
import static libraryasis.ui.addBook.Databasehandler.IDm;
import static libraryasis.ui.addBook.Databasehandler.Name;
import static libraryasis.ui.addBook.Databasehandler.isAvail;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MemberAddController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField Id;
    @FXML
    private TextField contact;
    @FXML
    private TextField email;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    Databasehandler databasehandler;
    @FXML
    private AnchorPane rootpane;
    @FXML
    private VBox vbox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        databasehandler=Databasehandler.getInstance();
    }    


    @FXML
    private void cancelBook(ActionEvent event) {
        Stage stage = (Stage) rootpane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void addMember(ActionEvent event) {
        String mName = name.getText();
        String mID = Id.getText();
        String mMobile = contact.getText();
        String mEmail = email.getText();

        Boolean flag = mName.isEmpty() || mID.isEmpty() || mMobile.isEmpty() || mEmail.isEmpty();
        if (flag) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter in all fields");
            alert.showAndWait();
            return;
    }
             Document newdoc = new Document();
  
  /*Map for list of properties for the new document*/
  Map<String , String> properties = new HashMap<String,String>();
  properties.put(IDm,mID);
 properties.put(Name,mName);
  properties.put(Contact,mMobile);
   properties.put(Email,mEmail);
  properties.put(isAvail,"true");
  
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
}
