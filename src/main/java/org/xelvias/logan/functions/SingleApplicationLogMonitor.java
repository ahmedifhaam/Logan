package org.xelvias.logan.functions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xelvias.logan.configuration.configurationmodels.subelements.ClientAppConfiguration;
import org.xelvias.logan.configuration.configurationmodels.subelements.User;
import org.xelvias.logan.handlers.Handler;
import org.xelvias.logan.handlers.LogEventHandlerRequest;
import org.xelvias.logan.handlers.LogEventHandlerRequestEligibility;
import org.xelvias.logan.handlers.LogEventNotificationHandler;
import org.xelvias.logan.logs.LogSourceAbstractFactory;
import org.xelvias.logan.logs.events.LogEvent;
import org.xelvias.logan.logs.parsers.LogEventParser;
import org.xelvias.logan.logs.source.LogSource;
import org.xelvias.logan.logs.source.SkipableLogSource;
import org.xelvias.logan.logs.source.SkippableLocalFileLogSource;
import org.xelvias.logan.logs.source.SkippableLogSourceFactory;
import org.xelvias.logan.notifier.EmailNotifier;
import org.xelvias.logan.notifier.SmsNotifier;

import java.io.IOException;
import java.util.Iterator;

public class SingleApplicationLogMonitor implements LogMonitorFunction<SkipableLogSource> {


    ClientAppConfiguration configuration;
    SkippableLogSourceFactory logSourceFactory;
    LogEventParser parser;
    SkipableLogSource logSource;

    private Iterator<LogEvent> logEvents;

    private static Logger LOG = LoggerFactory.getLogger(SingleApplicationLogMonitor.class);

    @Autowired
    public SingleApplicationLogMonitor(ClientAppConfiguration configuration, SkippableLogSourceFactory logSourceFactory, LogEventParser parser){
        this.configuration = configuration;
        this.logSourceFactory = logSourceFactory;
        this.parser = parser;
    }

    @Override
    public void execute(){
        LOG.info("Processing started for {}",configuration.getApplication_name());
        try {
            loadlogs();
            notifyUsers();
            updateDataSource(logSource);
        }catch (IOException ex){
            ex.printStackTrace();
            LOG.error(ex.getLocalizedMessage());
            System.out.printf("Failed to process applicatio {}",configuration.getApplication_name());
        }
    }


    @Override
    public void loadlogs() throws IOException {
        LOG.info("Loading logs for application {}",configuration.getApplication_name());
        logSource = logSourceFactory.createLogSource(configuration.getLog_file());
        logSource.skipAlreadyRead();
        this.logEvents = logSource.getEvents(parser);
        logSource.updateSkipLocation();
        LOG.info("Logs loaded successfully for the application {}",configuration.getApplication_name());
    }

    @Override
    public void notifyUsers() {
        //build the chain with required Notfication types
        LOG.info("Notifying users process starting for application {}",configuration.getApplication_name());

        Handler<LogEventHandlerRequest> smsNotificationHandler =
                new LogEventNotificationHandler(new SmsNotifier<LogEvent>(),new LogEventHandlerRequestEligibility("sms"));

        Handler<LogEventHandlerRequest> emailNotificationHandler =
                new LogEventNotificationHandler(new EmailNotifier<LogEvent>(),new LogEventHandlerRequestEligibility("email"));
        smsNotificationHandler.setNextInChain(emailNotificationHandler);

        while(logEvents.hasNext()){
            LogEvent event = logEvents.next();
            for(User user:configuration.getUsers()){
                smsNotificationHandler.handle(
                        new LogEventHandlerRequest(event,user)
                );
            }

        }

        LOG.info("Notifying users process completed for application {}",configuration.getApplication_name());

    }


    @Override
    public void updateDataSource(SkipableLogSource logSource) {
        try {
            logSource.updateSkipLocation();
        }catch (IOException ex){
            LOG.error(ex.getMessage());
            System.out.printf("Couldn't Update Data source %s",configuration.getLog_file());
        }
    }


}
