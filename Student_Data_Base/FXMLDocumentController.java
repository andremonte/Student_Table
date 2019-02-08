/**
 * version 1.0
 *
 * @author Andre Monte
 */
package jac444.wk5;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

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
    private Button saveBut, editBut, newBut, loadBut, deleteBut;

    @FXML
    private TableView<Student> tableView;

    @FXML
    private TableColumn<Student, Integer> idColumn, gradeColumn;

    @FXML
    private TableColumn<Student, String> nameColumn, courseColumn;

    private StudentList studentList;

    private Student temp;

    private final int NEW = 1;
    private final int EDITION = 2;
    private final int DELETE = 3;
    private int state;

    private int idEdt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set up the columns int the table
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("course"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("grade"));

        studentList = new StudentList(100);

        //load data
        tableView.setItems(getStudent(new ArrayList<>()));
    }

    /**
     * newStudent method clear all textFields and textField & buttons
     * editable(or not) or disable(or not)
     *
     * @param event
     */
    @FXML
    private void newStudent(ActionEvent event) {
        state = NEW;
        
        //fileField.setDisable(false);
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
    }

    /**
     * editStudent method set field and buttons editable/enable or not
     *
     * @param event
     */
    @FXML
    private void editStudent(ActionEvent event) {
        state = EDITION;

        newBut.setDisable(true);
        editBut.setDisable(true);
        deleteBut.setDisable(false);
        saveBut.setDisable(false);

        nameField.setEditable(true);
        courseField.setEditable(true);
        gradeField.setEditable(true);
        fileField.setEditable(true);
    }

    /**
     * saveStudent method checks if a file exists if yes add a student to it, if
     * not creates a new file with a new student.
     *
     * @param event
     */
    @FXML
    private void saveStudent(ActionEvent event) throws IOException, InterruptedException {
        if (state != DELETE) {
            //if fields are NOT empty
            if (Integer.parseInt(gradeField.getText()) < 0 || Integer.parseInt(gradeField.getText()) > 100) {
                alert("!!ERROR", "INVALID GRADE", "Error! Grade should be between 0 and 100\nPlease re-entry grade");
                return;
            }
            if (nameField.getText().equals("")) {
                alert("!!ERROR", "Name is empty", "Please provide a name to store your student information");
                return;
            }
            if (fileField.getText().equals("")) {
                alert("!!ERROR", "FileName is empty", "Please provide a file to store your student information");
                return;
            }
            if (courseField.getText().equals("")) {
                alert("!!ERROR", "course name is empty", "Please provide a course name to store your student information");
                return;
            }
            if (gradeField.getText().equals("")) {
                alert("!!ERROR", "grade is empty", "Please provide a grade to store your student information");
                return;
            }

            if (!(nameField.getText().equals("") && courseField.getText().equals("") && gradeField.getText().equals("") && fileField.getText().equals(""))) {

                //creating student object
                Student student_ = new Student(nameField.getText(), courseField.getText(), Integer.parseInt(gradeField.getText()));

                // Verificando se é uma ediçção ou um novo estudante
                if (state == NEW) {
                    //adding student object into students ArrayList
                    studentList.addNaLista(student_);
alert("Successfully", "FILE SAVED SUCCESSFULLY", "Your changes were saved to the disk");
                } else {
                    Student sEdit = studentList.getStudentById(idEdt);
                    sEdit.setName(student_.getName());
                    sEdit.setCourse(student_.getCourse());
                    sEdit.setGrade(student_.getGrade());
                }
            }

        }
        studentList.writeToFile(fileField.getText());

        
        loadFile(null);
        //set fields to begging of application.
        nameField.clear();
        nameField.setEditable(false);

        courseField.clear();
        courseField.setEditable(false);

        gradeField.clear();
        gradeField.setEditable(false);

        fileField.setEditable(true);

        newBut.setDisable(false);
        editBut.setDisable(true);
        deleteBut.setDisable(true);
        saveBut.setDisable(true);
        loadBut.setDisable(false);

    }

    /**
     * deleteStudent method checks if fields are empty and delete a student in a
     * list.
     *
     * @param event
     */
    @FXML
    private void deleteStudent(ActionEvent event) throws IOException, InterruptedException {
        state = DELETE;

        if (verificarArquivo(fileField.getText()) == false) {
            return;
        }
        /*File file = new File(fileField.getText());
        file.delete();*/
        //Finding student to be deleted
        Student s = tableView.getSelectionModel().getSelectedItem();
        int i = s.getId();
        System.out.println(i);

        studentList.deleteStudentByID(i);
        saveStudent(null);

        tableView.setItems(getStudent(studentList.getList()));

        nameField.clear();
        courseField.clear();
        gradeField.clear();
        //fileField.clear();
        saveBut.setDisable(true);
        deleteBut.setDisable(true);
        editBut.setDisable(true);
        fileField.setEditable(true);
    }

    /**
     * loadFile method creates an empty object and read file. Set fields and
     * buttons
     *
     * @param event
     */
    @FXML
    private void loadFile(ActionEvent event) throws IOException {
        //if the file does not exists
        if (verificarArquivo(fileField.getText()) == false) {
            return;
        }

        //if the file exists.
        studentList.readFile(fileField.getText());

        //setting fields as not editable.
        nameField.setEditable(false);
        courseField.setEditable(false);
        gradeField.setEditable(false);
        fileField.setEditable(false);
        deleteBut.setDisable(false);
        editBut.setDisable(false);

        //Set up the columns int the table
        idColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        courseColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("course"));
        gradeColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("grade"));

        //load data
        tableView.setItems(getStudent(studentList.getList()));
        fileField.setEditable(true);

    }

    /**
     * verificarArquivo checks if file exists or not and throw an alert
     *
     * @param arq
     */
    private boolean verificarArquivo(String arq) {
        File file = new File(arq);

        if (!file.exists()) {
            alert("!!ERROR", "FILE DOES NOT EXISTS", "Double check file name!");
            return false;
        }

        return true;
    }

    /**
     * alert method creates an alert with the following parameters.
     *
     * @param title
     * @param headerText
     * @param contentText
     */
    private void alert(String title, String headerText, String contentText) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public ObservableList<Student> getStudent(List<Student> lista) {
        ObservableList<Student> student = FXCollections.observableArrayList();

        lista.forEach((s) -> {
            student.add(s);
        });
        return student;
    }

    /**
     * handleRowSelect is a method responsible for get to know which row the
     * user is clicking (trying to get access).
     */
    @FXML
    private void handleRowSelect() {
        Student s = tableView.getSelectionModel().getSelectedItem();

        if (s == null) {
            return;
        }

        idEdt = s.getId();
        nameField.setText(s.getName());
        courseField.setText(s.getCourse());
        gradeField.setText("" + s.getGrade());

        deleteBut.setDisable(false);
        editBut.setDisable(false);
    }

    /**
     * FileChooser method is responsible for open files.
     */
    @FXML
    private void FileChooser() throws IOException {
        //creating a file
        File file_path = new File(fileField.getText());
        //File file_path = null;
        //fileField.setDisable(true);
        //creating a fileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        file_path = fileChooser.showOpenDialog(null);
        file_path.getName();

        fileField.setText("" + file_path);

        loadFile(null);

    }
}
