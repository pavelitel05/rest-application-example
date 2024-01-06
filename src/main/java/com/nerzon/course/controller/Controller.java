package com.nerzon.course.controller;

import com.nerzon.course.kafka.KafkaProducer;
import com.nerzon.course.repository.CatRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private final KafkaProducer kafkaProducer;
    private final CatRepo catRepo;
    public Controller(KafkaProducer kafkaProducer, CatRepo catRepo) {
        this.kafkaProducer = kafkaProducer;
        this.catRepo = catRepo;
    }

    @PostMapping("/kafka/send")
    public String send(@RequestParam int id) {
        var cat = catRepo.findById(id).orElseThrow();

        kafkaProducer.sendMessage(cat.toString());
        return "Success";
    }
}
