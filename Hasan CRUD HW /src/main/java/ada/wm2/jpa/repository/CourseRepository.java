package ada.wm2.jpa.repository;

import ada.wm2.jpa.entity.Course;
import ada.wm2.jpa.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends CrudRepository<Course, Integer> {

    @Query(value = "SELECT * FROM  Courses WHERE Course_ID NOT IN (SELECT Course_ID FROM Courses_Students WHERE Student_ID=?1)",nativeQuery = true)
    List<Course> findLeftCourses(Integer id);

    @Query(value = "select * from Courses", nativeQuery = true)
    List<Course> getAllCourses();

    @Query(value = "select * from Courses where Course_ID=?1",nativeQuery = true)
    Optional<Course> getCourseByID(Integer id);

    @Query(value = "select * from Courses where Course_level='Hard'",nativeQuery = true)
    List<Course> getCourseByLevel();
    @Query(value = "select * from Courses where prerequisite='yes'",nativeQuery = true)
    List<Course> getCourseByPrerequisite();
}
