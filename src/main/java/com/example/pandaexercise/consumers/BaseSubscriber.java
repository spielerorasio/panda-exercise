package com.example.pandaexercise.consumers;

import com.example.pandaexercise.event.PandaEvent;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseSubscriber implements Subscriber<PandaEvent> {

    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap();

    public Map<String, Integer> getMap(){
        return map;
    }


    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    protected void putInMap(String key) {
        if(key==null) return;
        map.computeIfAbsent(key, key1 -> new Integer(0));
        map.computeIfPresent(key, (k,v)-> { v++; return v; } );

    }


    @Override
    public void onError(Throwable t) {
        System.out.println(t);
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete");

    }
}
