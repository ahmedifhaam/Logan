package org.xelvias.logan.handlers;

import org.xelvias.logan.logs.events.ErrorLogEvent;
import org.xelvias.logan.logs.events.InfoLogEvent;

public class LogEventHandlerRequestEligibility implements Eligibility<LogEventHandlerRequest> {
    String type;

    public LogEventHandlerRequestEligibility(String type){
        this.type = type;
    }

    @Override
    public boolean isEligible(LogEventHandlerRequest data) {
        if(data.getUser().getNotificationTypes().indexOf(type)>-1){
            if (data.getLogEvent() instanceof InfoLogEvent){
                return true;
            }
        }
        return false;
    }
}
