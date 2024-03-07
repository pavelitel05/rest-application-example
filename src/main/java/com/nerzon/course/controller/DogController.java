package com.nerzon.course.controller;

import com.nerzon.course.entity.Dog;
import com.nerzon.course.repository.DogRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dogs")
@RequiredArgsConstructor
public class DogController {
    private final DogRepo dogRepo;

    @GetMapping("/all")
    public List<Dog> getAllDogsFromDB() {
        return dogRepo.findAll();
    }

    @GetMapping
    public ResponseEntity<Dog> getDogByName(@RequestParam String name) {
        var dog = dogRepo.findByName(name);
        if (dog == null) {
            return ResponseEntity
                    .status(HttpStatusCode.valueOf(404))
                    .build();
        }
        return new ResponseEntity<>(dog, HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public Dog putDogIntoDB(@RequestBody Dog dog) {
        dog.setId(UUID.randomUUID());
        return dogRepo.save(dog);
    }
}
