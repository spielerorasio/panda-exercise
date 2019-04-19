package com.example.pandaexercise.consumers;

import com.example.pandaexercise.event.PandaEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EventByTypeConsumer extends BaseSubscriber {

    public Map<String, Integer> getEventsByType(){
        return super.getMap();
    }

    @Override
    public void onNext(PandaEvent pandaEvent) {
        if(pandaEvent==null || pandaEvent.getEvent_type()==null) return;
        super.putInMap(pandaEvent.getEvent_type());
    }

}
