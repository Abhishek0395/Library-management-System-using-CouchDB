/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryasis.ui.addBook;

import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author HP
 */
import java.util.HashMap;
import java.util.Map;
import com.fourspaces.couchdb.Database;
import com.fourspaces.couchdb.Document;
import com.fourspaces.couchdb.Session;
public class Databasehandler {

 /*These are the keys of student document in couch db*/
 public static final String STUDENT_KEY_NAME ="name";
 
 public static final String STUDENT_KEY_MARKS ="marks";
 
 public static final String STUDENT_KEY_ROLL="roll";
 public static final String ID="ID";
 public static final String author="Author";
 public static final String publisher="Publisher";
 public static final String title="Title";
 public static final String isAvail="isAvail";
 public static final String Name= "Name";
 public static final String IDm= "IDm";
 public static final String Email="Email";
 public static final String Contact="Contact";
 public static Database studentCouchDb;
 private static Databasehandler handler=null;
 private Databasehandler()
 {
     /*Creating a session with couch db running in 5984 port*/
  Session studentDbSession = new Session("localhost",5984);
  
  /*Selecting the 'student' database from list of couch database*/
  studentCouchDb = studentDbSession.getDatabase("book");
 }
 public static Databasehandler getInstance()
    {
        if(handler==null)
        {
            handler = new Databasehandler();
        }
        return handler;
    }
 public boolean setdocument(Document newdoc)
 {
  
  
  /*Saving the new document in the 'student' database */
  studentCouchDb.saveDocument(newdoc); 
  return true;
  
 }
}
