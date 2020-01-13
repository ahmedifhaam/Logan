package org.xelvias.logan.logs.parsers;

import org.xelvias.logan.logs.events.LogEvent;

public interface LogEventParser {
    LogEvent parse(String line);
}
