package ada.wm2.jpa.Controller;


import ada.wm2.jpa.entity.Course;
import ada.wm2.jpa.entity.Student;
import ada.wm2.jpa.repository.CourseRepository;
import ada.wm2.jpa.repository.StudentRepository;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.jvm.hotspot.ui.tree.CStringTreeNodeAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @GetMapping("/")
    public String index() {
        return "students/index";
    }

    @GetMapping("/list")
    public String getAllStudentsList(Model model) {
        Iterable<Student> students = studentRepository.getAllStudents();
        model.addAttribute("students", students);
        return "students/StudentList";
    }

    @GetMapping("/id")
    public String getStudentById(Model mode, @RequestParam Integer id) {
        Optional<Student> result = studentRepository.getStudentByID(id);
        if (result.isPresent()) {
            Student student = result.get();
            List<Student> stList = new ArrayList<Student>();
            stList.add(student);
            mode.addAttribute("students", stList);
            return "students/StudentList";
        }
        else{
            mode.addAttribute("message1","There is no such students  according to given ID");
            return "students/index";
        }
    }

    @GetMapping("/new")
    public String newPage(Model m) {
        m.addAttribute("student", new Student());
        return "students/add";
    }

    @GetMapping("/update")
    public String updatePage(Model m, @RequestParam Integer id) {
        Optional<Student> result = studentRepository.findById(id);
        if (result.isPresent()) {
            m.addAttribute("student", result.get());
            return "students/add";
        }
        else {
            m.addAttribute("message2","There is no such students  according to given ID");
            return "students/index";
        }

    }

    @PostMapping("/add-update")
    public String addNewStudents(Model model, Student studentData) {
        studentRepository.save(studentData);
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        model.addAttribute("message", "Student " + studentData.getFirstname() + " " + studentData.getLastname() + " is new to Student List");
        return "students/StudentList";
    }
    @PostMapping("/addcoursesforstudent")
      public String addNewCoursesForChosenStudent(Model model, Course coursedata, @RequestParam("id") Integer id){
       Optional<Student> newStudent = studentRepository.findById(id);
        Student getStudent = newStudent.get();
        getStudent.getCourses().add(coursedata);
        studentRepository.save(getStudent);
        Iterable<Course> courses = studentRepository.findById(id).get().getCourses();
        model.addAttribute("courses", courses);
        model.addAttribute("keepId",id);
        String keepName = getStudent.getFirstname();
        String keepSurname = getStudent.getLastname();
        Iterable<Course> courses1=courseRepository.findLeftCourses(id);
        model.addAttribute("course",courses1);
        model.addAttribute("keepName",keepName+" "+keepSurname);
        return "enrollment/correspondingCourses";
    }
    @PostMapping("/deletecoursesforstudent")
       public String deleteCourseFromChosenStudent(Model model, @RequestParam Integer courseId, @RequestParam Integer studentID){
        Optional<Student> newStudent = studentRepository.findById(studentID);
        if (newStudent.isPresent()){
            Optional<Course> newCourse =courseRepository.findById(courseId);
            if (newCourse.isPresent()){
                Course course =newCourse.get();
                Student student =newStudent.get();
                student.getCourses().remove(course);
                studentRepository.save(student);
                String name =newStudent.get().getFirstname();
                String surname =newStudent.get().getLastname();
                model.addAttribute("keepName",name+" "+ surname);
                model.addAttribute("courses", student.getCourses());
                model.addAttribute("keepId",student.getId());
            }
            Iterable<Course> courses1=courseRepository.findLeftCourses(studentID);
            model.addAttribute("course",courses1);
        }
        return "enrollment/correspondingCourses";
    }

    @GetMapping("/deleted")
    public String getdeleteStudent(Model m, @RequestParam Integer id) {
        Optional<Student> result = studentRepository.findById(id);
        if (result.isPresent()) {
            m.addAttribute("student", result.get());
            return "students/delete";
        }
        else{
            m.addAttribute("message3","There is no such students  according to given ID");
        }
        return "students/index";
    }

    @PostMapping("/delete")
    public String deleteStudent(Model model, Student data) {
        studentRepository.delete(data);
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        model.addAttribute("message", "Student " + data.getFirstname() + " " + data.getLastname() + " is deleted from StudentList");
        return "students/StudentList";
    }

    @GetMapping("/gethighgpa")
    public  String getHighGpaStudents(Model model){
        Iterable<Student> students= studentRepository.getStudentsMoreThan3Gpa();
        model.addAttribute("students", students);
        return "students/StudentList";
    }
}
