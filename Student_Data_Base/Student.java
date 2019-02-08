/**
 * version 1.0
 *
 * @author Andre Monte
 */
package jac444.wk5;

import java.io.Serializable;

/**
 * Student class implements Serializable It has name, course and grade as
 * attributes.
 */
public class Student implements Serializable {

    public static int id_new = 1;

    private int id;
    private String name;
    private String course;
    private int grade;

    /**
     * Student default constructor
     */
    public Student() {

    }

    /**
     * Student constructor with 3 arguments
     *
     * @param name_ is set to name
     * @param course_ is set to course
     * @param grade_ is set to grade
     */
    public Student(String name_, String course_, int grade_) {
        id = id_new++;
        setName(name_);
        setCourse(course_);
        setGrade(grade_);
    }

    /**
     * getId method return student id.
     *
     * @return return id
     */
    public int getId() {
        return id;
    }

    /**
     * getName method returns name attribute
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getCourse method returns name attribute
     *
     * @return course
     */
    public String getCourse() {
        return course;
    }

    /**
     * getGrade method returns name attribute
     *
     * @return grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     * setName only set the the parameter into the name attribute.
     *
     * @param name_ name_ is set to name
     */
    public void setName(String name_) {
        this.name = name_;
    }

    /**
     * setCourse only set the the parameter into the course attribute.
     *
     * @param course_ is set to course
     */
    public void setCourse(String course_) {
        this.course = course_;
    }

    /**
     * setGrade only set the the parameter into the grade attribute.
     *
     * @param grade_ is set to grade
     */
    public void setGrade(int grade_) {
        grade = grade_;
    }
}
