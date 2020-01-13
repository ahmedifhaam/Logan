package org.xelvias.logan.handlers;

import org.slf4j.Logger;

public class LogEventLoggerHandler implements Handler<LogEventHandlerRequest> {
    Handler<LogEventHandlerRequest> nextInChain;
    int count = 0;

    Logger logger;

    public LogEventLoggerHandler(Logger logger){
        this.logger = logger;
    }

    @Override
    public void setNextInChain(Handler<LogEventHandlerRequest> handler) {
        this.nextInChain = handler;
    }

    @Override
    public void handle(LogEventHandlerRequest request) {
        logger.info("Dumping request : {} \n",request.toString());
    }
}
