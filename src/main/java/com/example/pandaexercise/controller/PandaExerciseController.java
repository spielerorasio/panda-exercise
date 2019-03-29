package com.example.pandaexercise.controller;

import com.example.pandaexercise.consumers.EventByTypeConsumer;
import com.example.pandaexercise.consumers.WordCountConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class PandaExerciseController {

    @Autowired
    EventByTypeConsumer eventByTypeConsumer;
    @Autowired
    WordCountConsumer wordCountConsumer;

//    @GetMapping("/{id}")
//    private Mono<Employee> getEmployeeById(@PathVariable String id) {
//        return employeeRepository.findEmployeeById(id);
//    }


    @GetMapping("/eventsByType")
    private Mono<Map<String, AtomicInteger>> getEventsByType() {
        return Mono.just(eventByTypeConsumer.getEventsByType());
    }

    @GetMapping("/wordsCount")
    private Mono<Map<String, AtomicInteger>> getWordsCount() {
        return Mono.just(wordCountConsumer.getWordsMap());
    }

}
