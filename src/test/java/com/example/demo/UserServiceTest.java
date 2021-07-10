package com.example.demo;

import com.example.demo.controller.model.PersonRequest;
import com.example.demo.persistence.model.Person;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService classUnderTest;

    @Test
    void buildPersonTest() {
        PersonRequest request = new PersonRequest("ASD", "ASDYAN", 10, "asd@gmail.com", "1111111", "asdUsername", "zxc");
        Person person = classUnderTest.buildPerson(request);
        String password = request.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = classUnderTest.getbCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches(password, person.getPassword());
        Assertions.assertTrue(matches);
    }


}
