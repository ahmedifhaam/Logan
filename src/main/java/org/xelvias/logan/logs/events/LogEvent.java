package org.xelvias.logan.logs.events;

import java.io.Serializable;
import java.util.Date;

public abstract class LogEvent implements Serializable {
    Date timestamp;
    String message;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
