package com.marinafx.cloudnative.tema6.logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.marinafx.cloudnative.tema6.service.Petstore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class InfoLogger {

    private final Logger logger = LoggerFactory.getLogger(Petstore.class);
    private final List<String> logs = new ArrayList<>();

    @Inject
    public InfoLogger(){}

    public void info(String msg){
        logs.add(msg);
        logger.info(msg);
    }

    public List<String> getLogs(){
        return new ArrayList<>(logs);
    }
}
