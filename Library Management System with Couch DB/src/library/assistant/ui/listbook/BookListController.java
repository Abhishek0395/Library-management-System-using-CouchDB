/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.ui.listbook;

import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.ViewResults;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
//import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import libraryasis.ui.addBook.Databasehandler;
import static libraryasis.ui.addBook.DisplayData.ID1;
import static libraryasis.ui.addBook.DisplayData.author1;
import static libraryasis.ui.addBook.DisplayData.isAvail1;
import static libraryasis.ui.addBook.DisplayData.publisher1;
import static libraryasis.ui.addBook.DisplayData.title1;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class BookListController implements Initializable {
     ObservableList<Book> list = FXCollections.observableArrayList();
    @FXML
    private AnchorPane rootpane;
    @FXML
    private TableView<Book> tableview;
    @FXML
    private TableColumn<Book,String> titleCol;
    @FXML
    private TableColumn<Book,String> idCol;
    @FXML
    private TableColumn<Book,String> authorCol;
    @FXML
    private TableColumn<Book,String> publisherCol;
    @FXML
    private TableColumn<Book,String> availabilityCol;

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
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availabilty"));
    }
     public void loadData()
     {
         ViewResults couchViewResults = Databasehandler.studentCouchDb.getAllDocuments();
  
  /*Retieving all document as result to a List*/
  List<Document> studentDocuments = couchViewResults.getResults();
  
  String titlex,id,author,publisher,avail;
  for(Document couchDocument: studentDocuments){
   
   String Id = couchDocument.getJSONObject().getString("id");
   
   Document BookRow = Databasehandler.studentCouchDb.getDocument(Id);
    titlex= (String) BookRow.get(title1);
     id =  (String)BookRow.get(ID1);
     author=(String)BookRow.get(author1);
     publisher=(String)BookRow.get(publisher1);
     avail= (String)BookRow.get(isAvail1);
    if(BookRow.containsKey(ID1))
   list.add(new Book(titlex, id, author, publisher, avail));
  }
   tableview.getItems().setAll(list);
     }
   public static class Book{
        
        private final SimpleStringProperty title;
        private final SimpleStringProperty id;
        private final SimpleStringProperty author;
        private final SimpleStringProperty publisher;
        private final SimpleStringProperty availabilty;

        public Book(String title, String id, String author, String pub, String avail) {
            this.title = new SimpleStringProperty(title);
            this.id = new SimpleStringProperty(id);
            this.author = new SimpleStringProperty(author);
            this.publisher = new SimpleStringProperty(pub);
            this.availabilty = new SimpleStringProperty(avail);
        }

        public String getTitle() {
            return title.get();
        }

        public String getId() {
            return id.get();
        }

        public String getAuthor() {
            return author.get();
        }

        public String getPublisher() {
            return publisher.get();
        }

        public String getAvailabilty() {
            return availabilty.get();
        }
        
    }
}
