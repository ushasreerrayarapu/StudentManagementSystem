package com.platformcommons.controller;

import com.platformcommons.model.Course;
import com.platformcommons.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam String keyword) {
        return ResponseEntity.ok(courseService.searchCourses(keyword));
    }

    @PostMapping
    public ResponseEntity<Course> createOrUpdateCourse(@RequestBody Course course) {
        course = courseService.createorUpdateCourse(course);
        return ResponseEntity.ok(course);
    }
}