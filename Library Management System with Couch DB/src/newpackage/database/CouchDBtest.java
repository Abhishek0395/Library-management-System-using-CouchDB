/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.database;

/**
 *
 * @author HP
 */
import java.util.List;
import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;
import com.fourspaces.couchdb.ViewResults;
import java.util.HashMap;
import java.util.Map;
import static library.assistant.database.CouchDBTest.STUDENT_KEY_MARKS;
import static library.assistant.database.CouchDBTest.STUDENT_KEY_NAME;
import static library.assistant.database.CouchDBTest.STUDENT_KEY_ROLL;

public class CouchDBtest {

 /*These are the keys of student document in couch db*/
 public static final String STUDENT_KEY_NAME ="name";
 
 public static final String STUDENT_KEY_MARKS ="marks";
 
 public static final String STUDENT_KEY_ROLL="roll";
 
 public static final String STUDENT_KEY_CONTACT ="contact";
 
 public static void main(String[] args){
  
  /*Creating a session with couch db running in 5984 port*/
  Session studentDbSession = new Session("localhost",5984);
  
  /*Selecting the student database from list of couch database*/
  Database studentCouchDb = studentDbSession.getDatabase("student");
  
  /*Fetching all Student Document to ViewResult object*/
  ViewResults couchViewResults = studentCouchDb.getAllDocuments();
  
  /*Retieving all document as result to a List*/
  List<Document> studentDocuments = couchViewResults.getResults();
  
   Map<String , String> properties = new HashMap<String,String>();
  
  properties.put(STUDENT_KEY_NAME, "saan");
  
  properties.put(STUDENT_KEY_MARKS, "67");
  
  properties.put(STUDENT_KEY_ROLL, "12");
  String abhi="saan";
  for(Document couchDocument: studentDocuments){
    
   String id = couchDocument.getJSONObject().getString("id");
    
   Document studentRow = studentCouchDb.getDocument(id);

   System.out.println("__________START OF DOCUMENT("+studentRow.get("_id")+")_________");
    
   if(studentRow.containsKey(STUDENT_KEY_NAME)){
     
    System.out.println("NAME : "+studentRow.get(STUDENT_KEY_NAME));
    String cat=(String)studentRow.get(STUDENT_KEY_NAME);
   if(studentRow.containsKey(STUDENT_KEY_MARKS)){
     
    System.out.println("MARK : "+studentRow.get(STUDENT_KEY_MARKS));
     
   }
    
   if(studentRow.containsKey(STUDENT_KEY_ROLL)){
     
    System.out.println("ROLL : "+studentRow.get(STUDENT_KEY_ROLL));
     
   }
  
     
   }
  }
  }
}