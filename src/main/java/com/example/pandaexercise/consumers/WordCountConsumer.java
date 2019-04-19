package com.example.pandaexercise.consumers;

import com.example.pandaexercise.event.PandaEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WordCountConsumer extends BaseSubscriber {

    public Map<String, Integer> getWordsMap(){
        return super.getMap();
    }

    @Override
    public void onNext(PandaEvent pandaEvent) {
        if(pandaEvent==null || pandaEvent.getEvent_type()==null) return;
        String data = pandaEvent.getData();
        if(data==null) return;

        String[] split = data.split(" ");
        for (String s : split) {
            super.putInMap(s);
        }
    }


}
