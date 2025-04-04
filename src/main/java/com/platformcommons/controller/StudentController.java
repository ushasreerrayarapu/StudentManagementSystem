package com.platformcommons.controller;

import com.platformcommons.DTO.StudentProfileDTO;
import com.platformcommons.model.Course;
import com.platformcommons.model.Student;
import com.platformcommons.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/createStudent")
    @Operation(description = "Create new Student")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @GetMapping("/findByName")
    @Operation(description = "get student by name")
    public ResponseEntity<List<Student>> getStudentsByName(@RequestParam String name) {
        return ResponseEntity.ok(studentService.getStudentsByName(name));
    }

    @GetMapping("/course/{courseId}")
    @Operation(description = "get students by course")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable Long courseId) {
        List<Student> students = studentService.getStudentsByCourse(courseId);
        return ResponseEntity.ok(students);
    }

    @PutMapping("/{studentId}/assign-courses")
    @Operation(description = "assign courses to Student")
    public ResponseEntity<Student> assignCoursesToStudent(
            @PathVariable Long studentId,
            @RequestBody List<Long> courseIds) {

        Student updatedStudent = studentService.assignCoursesToStudent(studentId, courseIds);

        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/{studentId}/courses")
    @Operation(description = "get courses assigned to a student")
    public ResponseEntity<List<Course>> getCoursesAssignedToStudent(@PathVariable Long studentId) {
        List<Course> courses = studentService.getCoursesAssignedToStudent(studentId);
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/{studentId}/update-profile")
    @Operation(description = "update student profile")
    public ResponseEntity<Student> updateStudentProfile(
            @PathVariable Long studentId,
            @RequestBody StudentProfileDTO studentProfileDTO) {

        Student updatedStudent = studentService.updateStudentProfile(studentId, studentProfileDTO);

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentId}/unenroll/{courseId}")
    @Operation(description = "Remove courses for student")
    public ResponseEntity<Student> unenrollFromCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.unenrollFromCourse(studentId, courseId));
    }
}
