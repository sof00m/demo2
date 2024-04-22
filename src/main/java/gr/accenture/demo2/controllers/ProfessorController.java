package gr.accenture.demo2.controllers;

import gr.accenture.demo2.models.Course;
import gr.accenture.demo2.models.Professor;
import gr.accenture.demo2.models.Student;
import gr.accenture.demo2.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;


    //added later
    @PostMapping
    public Professor createProfessor(@RequestBody Professor professor){
        return professorService.createProfessor(professor);
    }

    //added later
    @GetMapping("/all")
    public List<Professor> getAllProfessors(){
        return professorService.getAllProfessors();
    }

    @GetMapping("/courses")
    public List<Course> getCoursesOfProfessor(@RequestParam
                                              Long id){
        return professorService.getCourses(id);
    }

    @PostMapping("/course")
    public Course addCourseToProfessor(@RequestParam Long professorid,
                                       @RequestParam Long courseid){
        return professorService.addCourse(professorid, courseid);
    }




//  Explaining the thinking of creating better endpoints
//
//
//
//    "/professorsstudentcourse?pr=1&cou=2&sort=asending"
//
////    "/professor/1/course/2?sort=asending"
//    @GetMapping("/{profid}/course/{courseid}")
//    public List<Student> get(@PathVariable(name = "profid"),
//                             @PathVariable(name = "courseid"),
//                             @RequestParam String sort){
//    }
//
//
//    "/professor/{profid}/{courseid}"
//    "/professor/1/course/1"
//    @GetMapping()
//    public List<Student> getProfessorsStudentForCourses(){
//    }
}
