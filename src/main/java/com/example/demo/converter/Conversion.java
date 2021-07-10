package com.example.demo.converter;

import com.example.demo.controller.model.PersonResponse;
import com.example.demo.persistence.model.Person;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
public class Conversion {

    private PersonResponse response;

    public PersonResponse convertFromPerson(Person person){
        response = new PersonResponse(person.getId(), person.getName(), person.getSurname(),
                person.getAge(), person.getEmail(), person.getPhoneNumber(), person.getUsername());
        return response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversion that = (Conversion) o;
        return Objects.equals(response, that.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(response);
    }

    @Override
    public String toString() {
        return "Conversion{" +
                "response=" + response +
                '}';
    }
}
