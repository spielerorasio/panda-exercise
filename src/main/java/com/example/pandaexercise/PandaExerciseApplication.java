package com.example.pandaexercise;

import com.example.pandaexercise.service.PandaExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@EnableWebFlux
public class PandaExerciseApplication {

	@Autowired
	PandaExerciseService pandaExerciseService;

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext run = SpringApplication.run(PandaExerciseApplication.class, args);
		run.getBean(PandaExerciseService.class).execute();
	}

}
