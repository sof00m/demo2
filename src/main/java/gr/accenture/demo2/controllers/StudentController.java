package gr.accenture.demo2.controllers;

import gr.accenture.demo2.models.Address;
import gr.accenture.demo2.models.Course;
import gr.accenture.demo2.models.Student;
import gr.accenture.demo2.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;


    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping
    public Student getStudent(@RequestParam Long id){
        return studentService.getStudent(id);
    }

    @PutMapping("/address")
    public Student addAddress(@RequestBody Address address,
                              @RequestParam Long id){
        return studentService.addAddress(address, id);
    }

    @PostMapping("/course")
    public Course addCourseToStudent(@RequestParam Long id,
                                     @RequestParam String courseName){
        return studentService.addCourse(id,courseName);
    }
}
