package org.xelvias.logan.handlers.eligibilitystategy;

import org.xelvias.logan.handlers.LogEventHandlerRequest;
import org.xelvias.logan.logs.events.ErrorLogEvent;

public class ErrorLogEventHandlerRequestByTypeEligibility implements Eligibility<LogEventHandlerRequest> {
    String type;

    public ErrorLogEventHandlerRequestByTypeEligibility(String type){
        this.type = type;
    }

    @Override
    public boolean isEligible(LogEventHandlerRequest data) {
        if(data.getUser().getNotificationTypes().indexOf(type)>-1){
            if (data.getLogEvent() instanceof ErrorLogEvent){
                return true;
            }
        }
        return false;
    }
}
