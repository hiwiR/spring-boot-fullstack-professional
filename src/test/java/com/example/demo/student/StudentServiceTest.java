package com.example.demo.student;

import com.example.demo.student.exception.BadRequestException;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.AssertTrue;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    void beforeAll() {
        studentService = new StudentService(studentRepository);
    }

    @Test
    void getAllStudents() {
        studentService.getAllStudents();
        Mockito.verify(studentRepository).findAll();
    }

    @Test
    @Disabled
    void addStudent() {

        Student student = new Student("Camila","Bentton@gmail.com",Gender.FEMALE);
        studentService.addStudent(student);

        ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);

        Mockito.verify(studentRepository).save(studentArgumentCaptor.capture());
        assertThat(student).isEqualTo(studentArgumentCaptor.getValue());
    }

    @Test
    void deleteStudent() {

        Student student = new Student(1L,"Camila","Bentton@gmail.com",Gender.FEMALE);
        studentService.addStudent(student);

        //studentService.deleteStudent(1L);
        //ArgumentCaptor<Long> idCaptor = ArgumentCaptor.forClass(Long.class);

       // Mockito.verify(studentRepository).deleteById(idCaptor.capture());




    }
    @Test
    void emailTakenTest(){
        Student student = new Student(1L,"Camila","Bentton@gmail.com",Gender.FEMALE);
        //studentService.addStudent(student)
        given(studentRepository.selectExistsEmail(student.getEmail())).willReturn(true);

        assertThatThrownBy(()->studentService.addStudent(student))
                .isInstanceOf(BadRequestException.class);
        verify(studentRepository,never()).save(any());
    }
}