package com.example.demo;

import com.example.demo.controller.model.PersonResponse;
import com.example.demo.converter.Conversion;
import com.example.demo.persistence.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConversionTest {
    @Autowired
    private Conversion classUnderTest;


    @Test
    void convertFromPersonTest(){
        Person person = new Person("ASD", "ASDYAN", 10, "asd@gmail.com", "111111", "asdUsername", "fgh");
        PersonResponse personResponse = classUnderTest.convertFromPerson(person);

        Assertions.assertEquals(personResponse.getId(), person.getId());
        Assertions.assertEquals(personResponse.getName(), person.getName());
        Assertions.assertEquals(personResponse.getSurname(), person.getSurname());
        Assertions.assertEquals(personResponse.getAge(), person.getAge());
        Assertions.assertEquals(personResponse.getEmail(), person.getEmail());
        Assertions.assertEquals(personResponse.getPhoneNumber(), person.getPhoneNumber());
        Assertions.assertEquals(personResponse.getUsername(), person.getUsername());
    }
}
