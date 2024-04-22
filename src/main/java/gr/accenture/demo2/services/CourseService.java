package gr.accenture.demo2.services;


import gr.accenture.demo2.models.Course;
import gr.accenture.demo2.models.Professor;
import gr.accenture.demo2.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public List<Course> addCourse(Course course){
        courseRepository.save(course);
        return courseRepository.findAll();
    }

    public Course getCourse(Long id) {
        Optional<Course> courseOptional =
                courseRepository.findById(id);

        if (courseOptional.isPresent())
            return courseOptional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Course with id " + id + " doesnt exist");
    }

    //added later
    public Course createCourse(Course course){
        courseRepository.save(course);
        return course;
    }
}
