package com.platformcommons.service;

import com.platformcommons.model.Course;
import com.platformcommons.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> searchCourses(String keyword) {
        return courseRepository.findAll().stream()
                .filter(course -> course.getCourseName().toLowerCase().contains(keyword.toLowerCase()) ||
                        course.getTopics().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Course createorUpdateCourse(Course course) {
        return courseRepository.save(course);
    }

}
