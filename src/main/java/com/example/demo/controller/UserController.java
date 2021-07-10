package com.example.demo.controller;

import com.example.demo.controller.model.PersonRequest;
import com.example.demo.controller.model.PersonResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "user/create")
    public ResponseEntity<PersonResponse> create(@RequestBody PersonRequest user){
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping(value = "user/read")
    public ResponseEntity<List<PersonResponse>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<PersonResponse> readById(@PathVariable Long id){
        PersonResponse response = userService.getById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "user/{id}")
    public ResponseEntity<PersonResponse> update(@PathVariable Long id, @RequestBody PersonRequest request){
        return ResponseEntity.ok(userService.update(id, request));
    }

    @DeleteMapping(value = "user/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(userService.delete(id));
    }

}
