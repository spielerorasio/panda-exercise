package com.example.pandaexercise.consumers;

import com.example.pandaexercise.event.PandaEvent;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class EventByTypeConsumer extends BaseSubscriber {

    public Map<String, AtomicInteger> getEventsByType(){
        return super.getMap();
    }

    @Override
    public void onNext(String pandaEventStr) {
        PandaEvent pandaEvent = super.convertStr(pandaEventStr);
        if(pandaEvent==null) return;
        super.putInMap(pandaEvent.getEvent_type());
    }

}
