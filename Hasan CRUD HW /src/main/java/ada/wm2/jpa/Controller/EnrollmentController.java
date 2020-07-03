package ada.wm2.jpa.Controller;


import ada.wm2.jpa.entity.Course;
import ada.wm2.jpa.entity.Student;
import ada.wm2.jpa.repository.CourseRepository;
import ada.wm2.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/enrollment")
public class EnrollmentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/")
    public String getIndexPage(Model model){
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "enrollment/index";
    }
    @GetMapping("/{courses}")
    public String getTheCoursesForStudent(Model model, @RequestParam Integer id){
        String keepName = studentRepository.findById(id).get().getFirstname();
        String keepSurname = studentRepository.findById(id).get().getLastname();
        Iterable<Course> courses = studentRepository.findById(id).get().getCourses();
        Iterable<Course> courses1=courseRepository.findLeftCourses(id);
        model.addAttribute("course",courses1);
        model.addAttribute("courses", courses );
        model.addAttribute("keepId",id) ;
        model.addAttribute("keepName",keepName+" "+keepSurname);
        return "enrollment/correspondingCourses";
    }
    @GetMapping("/{add}")
    public String addNewCourse(Model model,@RequestParam Integer id) {
        Iterable<Course> courses1=courseRepository.findLeftCourses(id);
        model.addAttribute("courses",courses1);
        String keepName = studentRepository.findById(id).get().getFirstname();
        String keepSurname = studentRepository.findById(id).get().getLastname();
        model.addAttribute("keepName",keepName+" "+keepSurname);
        model.addAttribute("course",new Course());
        model.addAttribute("keepId",id);
        return "enrollment/add";
    }
}
