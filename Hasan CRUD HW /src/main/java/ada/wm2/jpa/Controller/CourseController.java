package ada.wm2.jpa.Controller;

import ada.wm2.jpa.entity.Course;
import ada.wm2.jpa.entity.Student;
import ada.wm2.jpa.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/")
    public String index(){
        return "courses/index";
    }

    @GetMapping("/list")
    public String getCourseList(Model model){
        Iterable<Course> courses= courseRepository.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses/CourseList";
    }

    @GetMapping("/id")
    public String getStudentById(Model mode, @RequestParam Integer id){
        Optional<Course> result= courseRepository.getCourseByID(id);
        if (result.isPresent()){
            List<Course> courselist =new ArrayList<Course>();
            courselist.add(result.get());
            mode.addAttribute("courses",courselist);
            return "courses/CourseList";
        }

         else {
            mode.addAttribute("message1","There is no such Courses  according to given ID");
            return "courses/index";
        }
    }

    @GetMapping("/godelete")
    public String goDelete(Model mode, @RequestParam Integer id){
        Optional<Course> result= courseRepository.findById(id);
        if (result.isPresent()){
            mode.addAttribute("course", result.get());
            return "courses/delete";
        }
        else {
            mode.addAttribute("message2","There is no such Courses  according to given ID");
            return "courses/index";
        }
    }

    @PostMapping("/delete")
    public String deleteCourse(Model model,  Course data){
       try {
           courseRepository.delete(data);
           Iterable<Course> courses= courseRepository.findAll();
           model.addAttribute("courses", courses);

           return "courses/CourseList";

       }
       catch (Exception ex) {
           model.addAttribute("notAllowed","You cannot Delete this course ID which is Foreign key In");
           model.addAttribute("course",data);
           return "courses/delete";
       }

    }

    @GetMapping("/new")
    public String newCoursePage(Model model){
        model.addAttribute("course",new Course());
        return "courses/add";
    }

    @GetMapping("/goupdate")
    public String updateCoursePage(Model model, @RequestParam Integer id){
        Optional<Course> result= courseRepository.findById(id);
        if (result.isPresent()){
            model.addAttribute("course", result.get());
            return "courses/add";
        }
        else {
            model.addAttribute("message3","There is no such Courses  according to given ID");
            return "courses/index";
        }
    }

        @PostMapping("/update")
        public String addNewCourses(Model model, Course courseData ){
            courseRepository.save(courseData);
            Iterable<Course> courses = courseRepository.findAll();
            model.addAttribute("courses", courses);
            return "courses/CourseList";
    }
    @GetMapping("/courselevel")
    public  String getHighGpaStudents(Model model){
        Iterable<Course> courses= courseRepository.getCourseByLevel();
        model.addAttribute("courses", courses);
        return "courses/CourseList";
    }
    @GetMapping("/prerequisite")
    public  String getprerequisite(Model model){
        Iterable<Course> courses= courseRepository.getCourseByPrerequisite();
        model.addAttribute("courses", courses);
        return "courses/CourseList";
    }

}
