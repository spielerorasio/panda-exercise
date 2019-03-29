package com.example.pandaexercise.service;

import com.example.pandaexercise.consumers.EventByTypeConsumer;
import com.example.pandaexercise.consumers.WordCountConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;

@Service
public class PandaExerciseServiceImpl implements  PandaExerciseService {

    @Autowired
    ProcessExecutor processExecutor;

    @Autowired
    EventByTypeConsumer eventByTypeConsumer;

    @Autowired
    WordCountConsumer wordCountConsumer;




    public void execute() throws InterruptedException {
        BufferedReader bufferedReader = processExecutor.prepareProcess();
        Flux<String> flux = Flux.create(fluxSink -> bufferedReader.lines().forEach(fluxSink::next));
        ConnectableFlux<String> publish = flux.publish(1);
        publish.subscribe(eventByTypeConsumer);
        publish.subscribe(wordCountConsumer);
        publish.connect();
        processExecutor.runBlocked();

    }


}
