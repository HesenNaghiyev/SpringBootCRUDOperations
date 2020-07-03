package ada.wm2.jpa.repository;

import ada.wm2.jpa.entity.Course;
import ada.wm2.jpa.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends CrudRepository<Student, Integer> {


    @Query(value = "select * from Students", nativeQuery = true)
    List<Student> getAllStudents();

    @Query(value = "select  * from Students  where gpa>3.5 ", nativeQuery = true)
    List<Student> getStudentsMoreThan3Gpa();

   @Query(value = "select * from Students where Student_ID=?1",nativeQuery = true)
    Optional<Student> getStudentByID(Integer id);

}
