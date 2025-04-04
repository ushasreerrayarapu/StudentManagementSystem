package com.platformcommons.service;

import com.platformcommons.DTO.StudentProfileDTO;
import com.platformcommons.model.Address;
import com.platformcommons.model.Course;
import com.platformcommons.model.Student;
import com.platformcommons.repository.CourseRepository;
import com.platformcommons.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Student addStudent(Student student) {
        Optional.ofNullable(student.getAddresses())
                .ifPresent(addresses -> addresses.forEach(address -> address.setStudent(student)));
        return studentRepository.save(student);
    }

    public List<Student> getStudentsByName(String name) {
        return studentRepository.findByNameIgnoreCase(name);
    }

    public List<Student> getStudentsByCourse(Long courseId) {
        return studentRepository.findStudentsByCourseId(courseId);
    }

    public List<Course> getCoursesAssignedToStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return student.getCourses();
    }

    public Student unenrollFromCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        student.getCourses().removeIf(course -> course.getId().equals(courseId));
        return studentRepository.save(student);
    }

    public Student assignCoursesToStudent(Long studentId, List<Long> courseIds) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<Course> courses = courseRepository.findAllById(courseIds);

        student.getCourses().addAll(courses);
        return studentRepository.save(student);
    }

    public Student updateStudentProfile(Long studentId, StudentProfileDTO updateDTO) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setEmail(updateDTO.getEmail());
        student.setMobileNumber(updateDTO.getMobileNumber());
        student.setFatherName(updateDTO.getFatherName());
        student.setMotherName(updateDTO.getMotherName());

        if (updateDTO.getAddresses() != null) {
            student.setAddresses(updateDTO.getAddresses());
        }
        return studentRepository.save(student);
    }
}
