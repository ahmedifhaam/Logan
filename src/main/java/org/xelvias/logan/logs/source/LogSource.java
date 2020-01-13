package org.xelvias.logan.logs.source;

import org.xelvias.logan.logs.events.LogEvent;
import org.xelvias.logan.logs.parsers.LogEventParser;

import java.io.IOException;
import java.util.Iterator;

public interface LogSource {
    Iterator<LogEvent> getEvents(LogEventParser parser) throws IOException;
}
