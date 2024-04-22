package gr.accenture.demo2.services;

import gr.accenture.demo2.models.Course;
import gr.accenture.demo2.models.Professor;
import gr.accenture.demo2.repositories.CourseRepository;
import gr.accenture.demo2.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    CourseRepository courseRepository;

    public List<Professor> getAllProfessors(){
        return professorRepository.findAll();
    }

    public List<Professor> addProfessor(Professor
                                        professor){
        professorRepository.save(professor);
        return professorRepository.findAll();
    }

    public Professor getProfessor(Long id){
        Optional<Professor> professorOptional=
                professorRepository.findById(id);

        if(professorOptional.isPresent())
            return professorOptional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Professor with id "+id+ " doesnt exist");

        //Alternative
//        return professorRepository.findById(id).orElseThrow(
//                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
//                "Professor with id "+id+ " doesnt exist")
//        );
    }

    //added later
    public Professor createProfessor(Professor professor) {
        professorRepository.save(professor);
        return professor;
    }

    public List<Course> getCourses(Long id) {
        Professor professor= professorRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Professor with id "+id+ " doesnt exist")
        );

        return professor.getCourses();
    }

    public Course addCourse(Long professorId, Long courseId) {
        Professor professor= professorRepository.findById(professorId).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Professor with id "+professorId+ " doesnt exist")
        );

        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Course with id "+courseId+ " doesnt exist")
        );

        professor.addCourse(course);
        course.setProfessor(professor);
        professorRepository.save(professor);
        courseRepository.save(course);
        return course;
    }

}
