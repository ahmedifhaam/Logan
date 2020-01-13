package org.xelvias.logan.handlers.eligibilitystategy;

import org.xelvias.logan.handlers.LogEventHandlerRequest;
import org.xelvias.logan.logs.events.InfoLogEvent;

public class InfoLogEventHandlerRequestByTypeEligibility implements Eligibility<LogEventHandlerRequest> {
        String type;

        public InfoLogEventHandlerRequestByTypeEligibility(String type){
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
