package gr.accenture.demo2.controllers;

import gr.accenture.demo2.models.Course;
import gr.accenture.demo2.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//added later
@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }

    @GetMapping("/all")
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }
}
