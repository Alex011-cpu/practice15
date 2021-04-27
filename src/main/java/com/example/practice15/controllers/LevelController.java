package com.example.practice15.controllers;


import com.example.practice15.models.Level;
import com.example.practice15.services.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LevelController {

    @Autowired
    private LevelService levelService;

    @PostMapping("/addLvl")
    public void addLevel (@RequestBody Level level) {
        levelService.addLevel(level);
    }

    @GetMapping("/lvl/{id}")
    public Level findLevel (@PathVariable int id) {
        return levelService.findLevel(id);
    }

    @GetMapping("/lvl")
    public List<Level> findAll () {
        return levelService.findAllLevels();
    }

    @DeleteMapping("/delLvl/{id}")
    public void delLevel (@PathVariable int id) {
        levelService.deleteLevel(id);
    }
}
