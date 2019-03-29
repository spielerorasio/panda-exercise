package com.example.pandaexercise.service;

import java.io.BufferedReader;

public interface ProcessExecutor {
    BufferedReader prepareProcess();
    int runBlocked() throws InterruptedException;
}
