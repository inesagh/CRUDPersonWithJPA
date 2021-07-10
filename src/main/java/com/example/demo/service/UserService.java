package com.example.demo.service;

import com.example.demo.controller.model.PersonRequest;
import com.example.demo.controller.model.PersonResponse;
import com.example.demo.converter.Conversion;
import com.example.demo.persistence.model.Person;
import com.example.demo.persistence.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Conversion conversion;
    static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository,
                       Conversion conversion) {
        this.userRepository = userRepository;
        this.conversion = conversion;
    }

    public PersonResponse create(PersonRequest request){
        Person person = buildPerson(request);
        userRepository.save(person);
        return conversion.convertFromPerson(person);
    }

    public List<PersonResponse> getAll(){
        List<Person> all = userRepository.findAll();
        List<PersonResponse> responseList = new ArrayList<>();

        all.stream().map(each -> conversion.convertFromPerson(each))
                    .forEach(each -> responseList.add(each));

        return responseList;
    }

    public PersonResponse getById(Long id){
        Person person = userRepository.getById(id);
        return conversion.convertFromPerson(person);
    }

    public PersonResponse update(Long id, PersonRequest request){
        Person person = buildPerson(request);
        Person byId = userRepository.getById(id);

        byId.setName(person.getName());
        byId.setSurname(person.getSurname());
        byId.setAge(person.getAge());
        byId.setEmail(person.getEmail());
        byId.setPhoneNumber(person.getPhoneNumber());
        byId.setUsername(person.getUsername());
        byId.setPassword(request.getPassword());

        Person updated = userRepository.save(byId);
        return conversion.convertFromPerson(updated);
    }

    public boolean delete(Long id){
        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }

    public Person buildPerson(PersonRequest request){
        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        System.out.println(encodedPassword);
        Person person = new Person(request.getName(),
                request.getSurname(),
                request.getAge(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getUsername(),
                encodedPassword);
        return person;
    }

    public BCryptPasswordEncoder getbCryptPasswordEncoder() {
        return bCryptPasswordEncoder;
    }

    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
