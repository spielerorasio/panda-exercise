package com.example.pandaexercise.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ProcessExecutorImpl implements ProcessExecutor {
    private static final Logger logger = LoggerFactory.getLogger(ProcessExecutorImpl.class);

    BufferedReader reader;
    Process process;

    @Override
    public BufferedReader prepareProcess() {
        try {
            ProcessBuilder builder = new ProcessBuilder();
            //new File("./src/main/resources").getAbsolutePath()
            builder.directory(new File("./src/main/resources"));

            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                builder.command("cmd.exe", "/c", "generator-windows-amd64.exe");
            } else {
                builder.command("./generator-macosx-amd64");
            }
            process = builder.start();
            reader =  new BufferedReader(new InputStreamReader(process.getInputStream()));
            return reader;
        } catch (IOException e) {
            logger.error("Got an execution Error",e);
            return null;
        }


    }

    @Override
    public int runBlocked() throws InterruptedException {
        return process.waitFor();
    }
}
