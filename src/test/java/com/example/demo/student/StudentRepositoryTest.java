package com.example.demo.student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void checkIfStudentExistByEmail(){
        //given
        Student student = new Student("Eliyas","umi@gmail.com",Gender.MALE);
        //
         studentRepository.save(student);
         boolean exist = studentRepository.selectExistsEmail("umi@gmail.com");
         assertThat(exist).isTrue();

    }

    @Test
    void checkIfStudentDoesNotExistsEmail() {

        String email = "hiwireta3@gmail.com";

        boolean exist = studentRepository.selectExistsEmail(email);
        assertThat(exist).isFalse();
    }
}