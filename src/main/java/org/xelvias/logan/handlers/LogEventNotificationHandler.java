package org.xelvias.logan.handlers;

import org.xelvias.logan.logs.events.LogEvent;
import org.xelvias.logan.notifier.Notifier;

public class LogEventNotificationHandler implements Handler<LogEventHandlerRequest> {

    Handler<LogEventHandlerRequest> nextInChain;
    Notifier<LogEvent> notifier;
    Eligibility<LogEventHandlerRequest> eligibility;

    public LogEventNotificationHandler(Notifier<LogEvent> notifier, Eligibility<LogEventHandlerRequest> eligibility){

        this.notifier = notifier;
        this.eligibility = eligibility;
    }

    @Override
    public void setNextInChain(Handler<LogEventHandlerRequest> handler) {
        this.nextInChain = handler;
    }

    @Override
    public void handle(LogEventHandlerRequest request) {
        if((eligibility.isEligible(request)))notifier.notify(request.getLogEvent(),request.getUser());
        if(nextInChain!=null) nextInChain.handle(request);
    }



}
