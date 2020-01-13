package org.xelvias.logan.functions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xelvias.logan.configuration.ConfigurationAbstractFactory;
import org.xelvias.logan.configuration.configurationmodels.Configuration;
import org.xelvias.logan.configuration.configurationmodels.ConfigurationNotFoundException;
import org.xelvias.logan.configuration.configurationmodels.subelements.ClientAppConfiguration;
import org.xelvias.logan.logs.parsers.LogEventParser;
import org.xelvias.logan.logs.source.SkippableLogSourceFactory;
import org.xelvias.logan.state.StateManager;

import java.io.IOException;
import java.util.Iterator;



public class LogMonitorApplication implements Function{
    SkippableLogSourceFactory skippableLogSourceFactory;
    LogEventParser parser;
    StateManager stateManager;
    ConfigurationAbstractFactory configurationFactory;

    private static Logger LOG  = LoggerFactory.getLogger(LogMonitorApplication.class);


    public LogMonitorApplication(SkippableLogSourceFactory skippableLogSourceFactory, LogEventParser parser,
                                 StateManager stateManager, ConfigurationAbstractFactory configurationFactory){
        this.skippableLogSourceFactory = skippableLogSourceFactory;
        this.parser = parser;
        this.stateManager = stateManager;
        this.configurationFactory = configurationFactory;

    }

    @Override
    public void execute() {
        LOG.info("Starting Log Monitor Application");
        try {
            LOG.info("Configuration file loading");
            Configuration configuration = configurationFactory.getConfiguration("application.config");
            Iterator<ClientAppConfiguration> allApplicationConfigs = configuration.getAllApplicationConfigs();
            LOG.info("Application Configurations loaded");
            while (allApplicationConfigs.hasNext()){
                ClientAppConfiguration clientAppConfiguration = allApplicationConfigs.next();
                LOG.info("Processing starting for {}",clientAppConfiguration.application_name);
                new SingleApplicationLogMonitor(clientAppConfiguration,skippableLogSourceFactory,parser)
                        .execute();
            }
            stateManager.saveState();
        }catch (ConfigurationNotFoundException ex){
            System.out.println("Accessing application configuration was not possible please configuration file accessibility");
        }catch (IOException ex){
            LOG.error("Error syncing state to file ");
            LOG.error("Error ",ex.getMessage());
        }
    }
}
