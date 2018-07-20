/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryasis.ui.addBook;

/**
 *
 * @author HP
 */
import java.util.List;
import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;
import com.fourspaces.couchdb.ViewResults;
public class DisplayData {
   
 public static final String ID1="ID";
 public static final String author1="Author";
 public static final String publisher1="Publisher";
 public static final String title1="Title";
 public static final String isAvail1="isAvail";
  public static final String Name1= "Name";
 public static final String IDm1= "IDm";
 public static final String Email1="Email";
 public static final String Contact1="Contact";
 public boolean Display()
 {
       ViewResults couchViewResults = Databasehandler.studentCouchDb.getAllDocuments();
  
  /*Retieving all document as result to a List*/
  List<Document> studentDocuments = couchViewResults.getResults();
  
  
  for(Document couchDocument: studentDocuments){
   
   String id = couchDocument.getJSONObject().getString("id");
   
   Document BookRow = Databasehandler.studentCouchDb.getDocument(id);
   
   System.out.println("__________START OF DOCUMENT("+BookRow.get("_id")+")_________");
   if(BookRow.containsKey(title1)){
    
    System.out.println("Title : "+BookRow.get(title1));
    
   }
   if(BookRow.containsKey(ID1)){
    
    System.out.println("ID : "+BookRow.get(ID1));
    
   }
   if(BookRow.containsKey(author1)){
    
    System.out.println("Author : "+BookRow.get(author1));
    
   }
   
   if(BookRow.containsKey(publisher1)){
    
    System.out.println("Publisher : "+BookRow.get(publisher1));
    
   }
   if(BookRow.containsKey(isAvail1)){
    
    System.out.println("Availability : "+BookRow.get(isAvail1));
    
   }
  }
  return true;
}
}
