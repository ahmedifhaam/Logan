package org.xelvias.logan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.xelvias.logan.configuration.ConfigurationAbstractFactory;
import org.xelvias.logan.functions.LogMonitorApplication;
import org.xelvias.logan.logs.parsers.LogEventParser;
import org.xelvias.logan.logs.source.SkippableLogSourceFactory;
import org.xelvias.logan.state.StateManager;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication implements CommandLineRunner {
    SkippableLogSourceFactory skippableLogSourceFactory;
    LogEventParser parser;
    StateManager stateManager;
    ConfigurationAbstractFactory configurationFactory;

    @Autowired
    public SpringBootApplication(
        SkippableLogSourceFactory skippableLogSourceFactory, LogEventParser parser,
                StateManager stateManager, ConfigurationAbstractFactory configurationFactory){

        this.skippableLogSourceFactory = skippableLogSourceFactory;
        this.parser = parser;
        this.stateManager = stateManager;
        this.configurationFactory = configurationFactory;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started");

        new LogMonitorApplication(skippableLogSourceFactory,parser,stateManager,configurationFactory)
                .execute();
        stateManager.saveState();
    }
}
