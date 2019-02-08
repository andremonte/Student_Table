/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jac444.wk5;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Andre Monte
 */
public class StudentList implements Serializable {

    ArrayList<Student> studentList;
    int maxsize;

    /**
     * StudentList constructor that receives the size of the Student List.
     *
     * @param size is set to the max size of studentList
     */
    StudentList(int size) {
        studentList = new ArrayList<Student>(size);
    }

    /**
     * addNaLista is a method that receives a student object and adds to the
     * list.
     *
     * @param obj is add to studentList
     */
    public void addNaLista(Student obj) {
        studentList.add(obj);
    }

    /**
     * getList is a method that returns an ArrayList of Students
     * @return studentList when called
     */
    public ArrayList<Student> getList() {
        return studentList;
    }

    /**
     * writeToFile create a file and write on it.
     *
     * @param path is set to objectOutputStream
     */
    public void writeToFile(String path) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * readFile receive a path address to the file and read it.
     *
     * @param path is received as a parameter
     */
    public void readFile(String path) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));

            StudentList lista = (StudentList) ois.readObject();

            //passing values to my Student obj because st is tmp.
            this.maxsize = lista.maxsize;
            this.studentList = new ArrayList<>(lista.studentList);

            // Passando por todos os estudantes em busca do maior ID
            int id = 0;
            for (Student s : studentList) {
                if (s.getId() > id) {
                    id = s.getId();
                }
            }
            Student.id_new = id + 1;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * getStudentById receive an id an look to the student represented by this
     * id.
     *
     * @param id_ is received to be look into studentList
     * @return the student matched
     */
    public Student getStudentById(int id_) {

        Student result = null;

        for (Student temp : studentList) {
            if (id_ == temp.getId()) {
                result = temp;
            }
        }
        return result;
    }

    /**
     * deleteStudentByID deletes the student in the index of
     *
     * @param i is the id of student that will be deleted
     */
    public void deleteStudentByID(int i) {
        Student std = studentList.stream().filter(s -> s.getId() == i).findFirst().get();
        studentList.remove(std);
    }

}
