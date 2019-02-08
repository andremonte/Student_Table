/**
 * version 1.0
 *
 * @author Andre Monte
 */
package jac444.wk4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.System.in;
import java.util.Scanner;
import javafx.event.ActionEvent;

/**
 * Student class has name, course and grade as attributes
 */
public class Student {

    private String name;
    private String course;
    private int grade;

    public Student(String name_, String course_, int grade_) {
        setName(name_);
        setCourse(course_);
        setGrade(grade_);
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public int getGrade() {
        return grade;
    }

    public void setName(String name_) {
        this.name = name_;
    }

    public void setCourse(String course_) {
        this.course = course_;
    }

    public void setGrade(int grade_) {
        if (grade_ < 0 || grade_ > 100) {
            int i = 0;
            int tmpGrade;
            while (i == 0) {
                System.out.println("Error! Grade should be between 0 and 100\nPlease re-entry grade");
                Scanner scan = new Scanner(System.in);
                tmpGrade = scan.nextInt();
                if (tmpGrade >= 0 && tmpGrade <= 100) {
                    i++;
                    grade = tmpGrade;
                }
            }
        } else {
            grade = grade_;
        }
    }

    public void writeToFile(Student student_) throws IOException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.txt"));
            oos.writeObject(student_);
            oos.close();
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public void readFile() throws IOException, ClassNotFoundException {
       try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.txt"));
            Student st = (Student) ois.readObject();
            System.out.println(st);
       }catch(IOException e) {
          System.out.println(e.getMessage()); 
       }
    }
}
