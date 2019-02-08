/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac444.wk4;

import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author andremonte
 */
public class FXMLDocumentController implements Initializable {

    
    
    @FXML
    private Label label, nome, curso, nota, carregar;
    
    @FXML
    private TextField nameField, courseField, gradeField, fileField;
    
    @FXML
    private Button saveBut, editBut, newBut, loadBut, deleteBut, cancelBut;
    
    /*@FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }*/

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void newStudent(ActionEvent event) {
      System.out.println("NEW button clicked!");
      nameField.clear(); 
      nameField.setEditable(true);
      
      courseField.clear();
      courseField.setEditable(true);
      
      gradeField.clear();
      gradeField.setEditable(true);
      
      fileField.clear();
      fileField.setEditable(true);
      
      newBut.setDisable(true);
      loadBut.setDisable(true);
      editBut.setDisable(true);
      deleteBut.setDisable(true);
      saveBut.setDisable(false);
      cancelBut.setDisable(false);
    //  if(String nome = iif(! this.getText().isEmpty());
    //  System.out.println(getRemark());
    }

    @FXML
    private void editStudent(ActionEvent event) {
        System.out.println("EDIT button clicked!");
        // String name = get.TextField();
        // String course = get.TextField();
        // int grade
    }

    @FXML
    private void saveStudent(ActionEvent event) throws IOException, InterruptedException {
        System.out.println("SAVE button clicked!");
        String n = nameField.getText();
        String c = courseField.getText();
        int m = Integer.parseInt(gradeField.getText());
        
        Student student_ = new Student (n, c, m);
        
 /*       try {
            writeToFile(student_);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Successfully");
            alert.setHeaderText("STUDENT ADDED SUCCESSFULLY");
            alert.setContentText("A new student was created");
            alert.showAndWait();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }*/
        nameField.clear();
        courseField.clear();
        gradeField.clear();
        
    } 
    
    @FXML
    private void deleteStudent(ActionEvent event) {
        System.out.println("DELETE button clicked!");
        // String name = get.TextField();
        // String course = get.TextField();
        // int grade
    }
    
    @FXML
    private void loadFile(ActionEvent event) throws IOException {
      System.out.println("LOADFILE button clicked!");
        /* try {
           readFile();
       }catch(IOException e) {
           System.out.println(e.getMessage());
       }*/
       
    }
    
    @FXML
    private void cancel(ActionEvent event) {
       System.out.println("CANCEL button clicked!");
    }

}
