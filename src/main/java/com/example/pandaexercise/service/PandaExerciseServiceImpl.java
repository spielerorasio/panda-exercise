package com.example.pandaexercise.service;

import com.example.pandaexercise.consumers.EventByTypeConsumer;
import com.example.pandaexercise.consumers.WordCountConsumer;
import com.example.pandaexercise.event.PandaEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.function.Function;

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

        ObjectMapper objectMapper = new ObjectMapper();

        Function<Flux<String>, Flux<PandaEvent>> transformer =
                f -> f.map(str -> {
                    if(str==null) {
                        return new PandaEvent();
                    }
                    try {
                        return objectMapper.readValue(str, PandaEvent.class);
                    } catch (IOException e) {
                        //ignore - no error specific handling
                        return new PandaEvent();
                    }
                });


        //ConnectableFlux can connect multiple subscribers
        ConnectableFlux<String> publish = flux.publish(10);//10 is the prefetch
        Flux<PandaEvent> transform = publish.transform(transformer);
        transform.subscribe(eventByTypeConsumer);
        transform.subscribe(wordCountConsumer);
        publish.connect();
        processExecutor.runBlocked();

    }


}
