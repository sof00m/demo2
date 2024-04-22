package gr.accenture.demo2.repositories;

import gr.accenture.demo2.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

    Optional<Course> findByName(String name);
}
