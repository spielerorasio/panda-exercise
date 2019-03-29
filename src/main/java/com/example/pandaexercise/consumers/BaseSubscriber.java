package com.example.pandaexercise.consumers;

import com.example.pandaexercise.event.PandaEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseSubscriber implements Subscriber<String> {
    private static final Logger logger = LoggerFactory.getLogger(BaseSubscriber.class);

    private ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap();

    public Map<String, AtomicInteger> getMap(){
        return map;
    }
    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    protected void putInMap(String key) {
        if(key==null) return;
        AtomicInteger atomicInteger = map.get(key);
        if(atomicInteger==null){
            atomicInteger = new AtomicInteger(0);
            AtomicInteger previous = map.putIfAbsent(key, atomicInteger);
            atomicInteger = previous==null? atomicInteger : previous;
        }
        atomicInteger.incrementAndGet();
    }

    protected PandaEvent convertStr(String str){
        try {
            return objectMapper.readValue(str, PandaEvent.class);
        } catch (IOException e) {
            //ignore - no error specific handling
            return null;
        }
    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}
