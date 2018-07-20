/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.ui.listMember;

import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.ViewResults;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.assistant.ui.listbook.BookListController;
import libraryasis.ui.addBook.Databasehandler;

import static libraryasis.ui.addBook.DisplayData.ID1;
import static libraryasis.ui.addBook.DisplayData.IDm1;
import static libraryasis.ui.addBook.DisplayData.Email1;
import static libraryasis.ui.addBook.DisplayData.Contact1;
import static libraryasis.ui.addBook.DisplayData.Name1;
import static libraryasis.ui.addBook.DisplayData.author1;
import static libraryasis.ui.addBook.DisplayData.isAvail1;
import static libraryasis.ui.addBook.DisplayData.publisher1;
import static libraryasis.ui.addBook.DisplayData.title1;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MemberListController implements Initializable {
ObservableList<MemberListController.Member> list = FXCollections.observableArrayList();
    @FXML
    private TableView<Member> tableview;
    @FXML
    private TableColumn<Member, String> idCol;
    @FXML
    private TableColumn<Member, String> contactCol;
    @FXML
    private TableColumn<Member, String> emailCol;
    @FXML
    private TableColumn<Member, String> nameCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCol();
        Databasehandler handler=Databasehandler.getInstance();
        loadData();
    } 
     private void initCol() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }
     public void loadData()
     {
         ViewResults couchViewResults = Databasehandler.studentCouchDb.getAllDocuments();
  
  /*Retieving all document as result to a List*/
  List<Document> studentDocuments = couchViewResults.getResults();
  
  String namex,idx,contactx,emailx;
  for(Document couchDocument: studentDocuments){
   
   String Id = couchDocument.getJSONObject().getString("id");
   
    Document MemberRow = Databasehandler.studentCouchDb.getDocument(Id);
    namex= (String) MemberRow.get(Name1);
     idx =  (String)MemberRow.get(IDm1);
     contactx=(String)MemberRow.get(Contact1);
     emailx=(String)MemberRow.get(Email1);
    if(MemberRow.containsKey(Name1))
   list.add(new MemberListController.Member(namex, idx,contactx, emailx));
  }
   tableview.getItems().setAll(list);
     }
       public static class Member{
        
        private final SimpleStringProperty name;
        private final SimpleStringProperty id;
        private final SimpleStringProperty contact;
        private final SimpleStringProperty email;

        public Member(String Name, String Id, String Contact, String Email) {
            this.name = new SimpleStringProperty(Name);
            this.id = new SimpleStringProperty(Id);
            this.contact = new SimpleStringProperty(Contact);
            this.email = new SimpleStringProperty(Email);
        }

         public String getName() {
            return name.get();
        }

        public String getId() {
            return id.get();
        }

        public String getContact() {
            return contact.get();
        }

        public String getEmail() {
            return email.get();
        }
    }
    
}
