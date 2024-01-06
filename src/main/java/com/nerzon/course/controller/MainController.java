package com.nerzon.course.controller;

import com.nerzon.course.DTO.CatDTO;
import com.nerzon.course.entity.Cat;
import com.nerzon.course.repository.CatRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author nerzon
 */
@Tag(name = "main_methods")
@RestController
public class MainController {

    private final CatRepo catRepo;

    public MainController(CatRepo catRepo) {
        this.catRepo = catRepo;
    }

    @Operation(
            summary = "кладет нового котика в базу",
            description = "Получает DTO кота и билдером собирает и сохраняет сущность в базу"
    )
    @PostMapping("/api/add")
    public void addCat(@RequestBody CatDTO catDTO) {
        System.out.println(
                "New row: " + catRepo.save(
                        Cat.builder()
                                .id(1)
                                .age(catDTO.getAge())
                                .weight(catDTO.getWeight())
                                .name(catDTO.getName())
                                .build())
        );
    }

    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> getAll() {
        return catRepo.findAll();
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        catRepo.deleteById(id);
    }

    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such row";
        }
        return catRepo.save(cat).toString();
    }
}
