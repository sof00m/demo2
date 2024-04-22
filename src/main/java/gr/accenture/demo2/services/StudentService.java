package gr.accenture.demo2.services;

import gr.accenture.demo2.models.Address;
import gr.accenture.demo2.models.Course;
import gr.accenture.demo2.models.Student;
import gr.accenture.demo2.repositories.AddressRepository;
import gr.accenture.demo2.repositories.CourseRepository;
import gr.accenture.demo2.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    AddressService addressService;

    public Student createStudent(Student student){
        Optional<Student> studentOptional =
                studentRepository.findByEmail(student.getEmail());
        if(studentOptional.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Student with email "+student.getEmail()+
                    "already exists!");

        studentRepository.save(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Error")
        );
    }

    public Student addAddress(Address address, Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Error")
        );

        Address address1= addressService.createAddress(address);
        address1.setStudent(student);
        addressRepository.save(address1);
        student.setAddress(address1);

        studentRepository.save(student);

        return student;
    }

    public Course addCourse(Long id, String courseName) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Error")
        );

        Course course = courseRepository.findByName(courseName).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Error")
        );

        student.addCourse(course);
        course.addStudent(student);

        courseRepository.save(course);
        studentRepository.save(student);
        return course;
    }
}
