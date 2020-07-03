package ada.wm2.jpa.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Students")
public class Student {
    @Id
    @Column(name = "Student_ID")
    Integer id;

    @Column(name = "First_Name")
    String firstname;

    @Column(name = "Last_Name")
    String lastname;

    @Column(name = "Country")
     String country;

    @Column(name = "Gpa")
    double gpa;

    @ManyToMany
    @JoinTable(name = "Courses_Students",
            joinColumns = @JoinColumn(name = "Student_ID"),
            inverseJoinColumns = @JoinColumn(name = "Course_ID"))
    List<Course> courses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public double getGpa() {

        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
