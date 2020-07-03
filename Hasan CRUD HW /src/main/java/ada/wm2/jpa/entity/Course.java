package ada.wm2.jpa.entity;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "Courses")
public class Course {
  @Id
  @Column(name = "Course_ID")
    Integer CourseID;

  String Coursename;

 @Column(name = "Course_level")
  String Courselevel;

  String prerequisite;

  public List<Student> getStudents() {
    return students;
  }

  public void setStudents(List<Student> students) {
    this.students = students;
  }

  @ManyToMany(mappedBy = "courses")
  List<Student> students;

  public Integer getCourseID() {
    return CourseID;
  }

  public String getCourselevel() {
    return Courselevel;
  }

  public void setCourselevel(String courselevel) {
    Courselevel = courselevel;
  }

  public String getPrerequisite() {
    return prerequisite;
  }

  public void setPrerequisite(String prerequisite) {
    this.prerequisite = prerequisite;
  }

  public void setCourseID(Integer courseID) {
    CourseID = courseID;
  }

  public String getCoursename() {
    return Coursename;
  }

  public void setCoursename(String coursename) {
    Coursename = coursename;
  }
}
