package org.xelvias.logan.handlers;

import org.xelvias.logan.configuration.configurationmodels.subelements.User;
import org.xelvias.logan.logs.events.LogEvent;

import java.io.Serializable;

public class LogEventHandlerRequest implements Serializable {
    LogEvent logEvent;
    User user;

    public LogEventHandlerRequest(LogEvent logEvent, User user) {
        this.logEvent = logEvent;
        this.user = user;
    }

    public LogEvent getLogEvent() {
        return logEvent;
    }

    public void setLogEvent(LogEvent logEvent) {
        this.logEvent = logEvent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LogEventHandlerRequest{" +
                "logEvent=" + logEvent +
                ", user=" + user +
                '}';
    }
}
