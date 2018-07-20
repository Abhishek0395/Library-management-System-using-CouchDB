/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.assistant.ui.main;

import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.ViewResults;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import library.assistant.ui.listMember.MemberListController;
import library.assistant.ui.listbook.BookListController;
import libraryasis.ui.addBook.Databasehandler;
import static libraryasis.ui.addBook.DisplayData.Contact1;
import static libraryasis.ui.addBook.DisplayData.Email1;
import static libraryasis.ui.addBook.DisplayData.ID1;
import static libraryasis.ui.addBook.DisplayData.IDm1;
import static libraryasis.ui.addBook.DisplayData.Name1;
import static libraryasis.ui.addBook.DisplayData.author1;
import static libraryasis.ui.addBook.DisplayData.isAvail1;
import static libraryasis.ui.addBook.DisplayData.publisher1;
import static libraryasis.ui.addBook.DisplayData.title1;
import static newpackage.database.CouchDBtest.STUDENT_KEY_ROLL;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MainController implements Initializable {

    @FXML
    private HBox book_info;
    @FXML
    private HBox member_info;
    @FXML
    private TextField bookIdInput;
    @FXML
    private Text bookName;
    @FXML
    private Text bookAuthor;
    @FXML
    private Text bookStatus;
    Databasehandler databasehandler;
    @FXML
    private TextField memberIdInput;
    @FXML
    private Text memberName;
    @FXML
    private Text memberMobile;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        databasehandler= Databasehandler.getInstance();
    }    

    @FXML
    private void loadAddMember(ActionEvent event) {
       loadWindow("/library/asssistant/ui/addMember/MemberAdd.fxml","Add New Member");
    }

    @FXML
    private void loadAddBook(ActionEvent event) {
        loadWindow("/libraryasis/ui/addBook/FXMLDocument.fxml","Add New Book");
    }

    @FXML
    private void loadMemberTable(ActionEvent event) {
        loadWindow("/library/assistant/ui/listMember/member_list.fxml","Member List");
    }

    @FXML
    private void loadBookTable(ActionEvent event) {
        loadWindow("/library/assistant/ui/listbook/BookList.fxml","Book List");
    }
    void loadWindow(String loc,String title)
    {
        try {
            Parent parent=FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadBookInfo(ActionEvent event) {
        String id = bookIdInput.getText();
        ViewResults couchViewResults = Databasehandler.studentCouchDb.getAllDocuments();
  
  /*Retieving all document as result to a List*/
  List<Document> studentDocuments = couchViewResults.getResults();
  boolean flag=false;
  System.out.println("0");
   String titlex="",Idx,authorx="",publisherx="",availx="true",status="";
  for(Document couchDocument: studentDocuments){
   boolean flag1=false;
   
   String Id = couchDocument.getJSONObject().getString("id");
   System.out.println("1");
   Document BookRow = Databasehandler.studentCouchDb.getDocument(Id);
    titlex= (String) BookRow.get(title1);
     Idx =  (String)BookRow.get(ID1);
     authorx=(String)BookRow.get(author1);
     publisherx=(String)BookRow.get(publisher1);
     availx= (String)BookRow.get(isAvail1);
    //if(BookRow.containsKey(ID1));
    if((BookRow.containsKey(ID1))){
    if(Idx.equals(id))
    {
        System.out.println("11");
        flag=true;
        break;
    }
    }
  }
  System.out.println("111");
  if(flag==true)
  {
      System.out.println(availx);
   bookName.setText(titlex);
   bookAuthor.setText(authorx);
   if(availx.equals("true"))
   {
     status = "Available";
   }
   else
   {
       status="Not Available";
   }
    bookStatus.setText(status);
  }
    if (flag==false) {
        System.out.println("11111");
      bookName.setText("No Such Book Available");
      bookAuthor.setText("");
      bookStatus.setText("");
    }
}

    @FXML
    private void loadMemberInfo(ActionEvent event) {
         String id = memberIdInput.getText();
        ViewResults couchViewResults = Databasehandler.studentCouchDb.getAllDocuments();
  
  /*Retieving all document as result to a List*/
  List<Document> studentDocuments = couchViewResults.getResults();
  boolean flag=false;
  System.out.println("0");
   String namex="",idx="",contactx="",emailx="";
  for(Document couchDocument: studentDocuments){
   boolean flag1=false;
   String Id = couchDocument.getJSONObject().getString("id");
   
    Document MemberRow = Databasehandler.studentCouchDb.getDocument(Id);
        System.out.println("1");
    namex= (String) MemberRow.get(Name1);
     idx =  (String)MemberRow.get(IDm1);
     contactx=(String)MemberRow.get(Contact1);
     emailx=(String)MemberRow.get(Email1);
    if(MemberRow.containsKey(Name1)){
         System.out.println("11");
    if(idx.equals(id))
    {
        System.out.println("110");
        flag=true;
        break;
    }
    }
  } 
  System.out.println("111");
  if(flag==true)
  {
      System.out.println(namex);
   memberName.setText(namex);
   memberMobile.setText(contactx);
  }
    if (flag==false) {
        System.out.println("11111");
   memberName.setText("No such member available");
   memberMobile.setText("");
    }
    }

    @FXML
    private void loadIssueOperation(ActionEvent event) {
        String memberId=memberIdInput.getText();
        String bookId=bookIdInput.getText();
    }
}
