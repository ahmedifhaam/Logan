package org.xelvias.logan.logs.events;

public class LogEventFactory {
    public static LogEvent CreateLogEvent(String type){
        LogEvent logEvent = null;
        switch (type.toUpperCase()){
            case "INFO":
                logEvent = new InfoLogEvent();
                break;
            case "ERROR":
                logEvent = new ErrorLogEvent();
                break;

            case "TRACE":
                logEvent = new TraceLogEvent();
                break;

            default:
                logEvent = new DefaultLogEvent();
        }

        return logEvent;
    }
}
