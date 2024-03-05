package com.example.springelastictest.controller;

import com.example.springelastictest.services.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/apis")
public class ApiController {

    private final EsService esService;

    @Autowired
    public ApiController(EsService esService) {
        this.esService = esService;
    }

    @PutMapping("/question_add")
    public String addQuestion(@RequestParam("question") String question,
                              @RequestParam("answer") String answer) {
        String id = UUID.randomUUID().toString();
        esService.updateQuestion(id, question, answer);
        return id;
    }
}
