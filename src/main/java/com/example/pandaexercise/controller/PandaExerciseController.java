package com.example.pandaexercise.controller;

import com.example.pandaexercise.consumers.EventByTypeConsumer;
import com.example.pandaexercise.consumers.WordCountConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
public class PandaExerciseController {

    @Autowired
    EventByTypeConsumer eventByTypeConsumer;
    @Autowired
    WordCountConsumer wordCountConsumer;


    @GetMapping("/eventsByType")
    private Mono<Map<String, Integer>> getEventsByType() {
        return Mono.just(eventByTypeConsumer.getEventsByType());
    }

    @GetMapping("/wordsCount")
    private Mono<Map<String, Integer>> getWordsCount() {
        return Mono.just(wordCountConsumer.getWordsMap());
    }

}
