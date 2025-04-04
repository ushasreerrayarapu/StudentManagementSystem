package com.platformcommons.service;

import com.platformcommons.model.Student;
import com.platformcommons.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        student.setName("Usha");
        student.setDateOfBirth(LocalDate.of(2000, 1, 1));
        student.setGender("Female");
        student.setStudentCode("STUDENT1");
    }

    @Test
    void testAddStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        Student savedStudent = studentService.addStudent(student);

        assertNotNull(savedStudent);
        assertEquals("Usha", savedStudent.getName());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetStudentsByName() {
        when(studentRepository.findByNameIgnoreCase("Usha")).thenReturn(Arrays.asList(student));
        List<Student> students = studentService.getStudentsByName("Usha");

        assertFalse(students.isEmpty());
        assertEquals(1, students.size());
        assertEquals("Usha", students.get(0).getName());
        verify(studentRepository, times(1)).findByNameIgnoreCase("Usha");
    }
}
