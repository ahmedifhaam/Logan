package org.xelvias.logan.logs.source;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.xelvias.logan.logs.events.LogEvent;
import org.xelvias.logan.logs.parsers.LogEventParser;
import org.xelvias.logan.logs.parsers.RegExLogEventParser;
import org.xelvias.logan.state.StateManager;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.Iterator;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkippableLocalFileLogSourceTest {

    @Mock
    StateManager stateManager;


    LogEventParser logEventParser = new RegExLogEventParser();

    SkippableLocalFileLogSource localFileLogSource;

    @Before
    public void setup() throws IOException {
        when(stateManager.getValue(any(String.class))).thenReturn("100");
        localFileLogSource = new SkippableLocalFileLogSource("/Users/xelvias/Workings/Logan/src/test/resources/samplelogfile.log",stateManager);
    }

    @Test
    public void ShouldBeNonEmptyEvent() throws IOException {
        Assert.assertNotNull(localFileLogSource.getEvents(logEventParser));
    }


    @Test
    public void ShouldSkipWithoutError() throws IOException {
        localFileLogSource.skipAlreadyRead();
    }

    @Test
    public void ShouldReturnNonNulLogEvents() throws IOException {
        Iterator<LogEvent> events = localFileLogSource.getEvents(logEventParser);

        LogEvent event = events.next();
        Assert.assertNotNull(event);
    }

    @Test
    public void TotalEventsTest() throws IOException {
        Iterator<LogEvent> events = localFileLogSource.getEvents(logEventParser);
        int total = 0;
        while (events.hasNext()){
            LogEvent logEvent = events.next();
            total++;
        }

        Assert.assertEquals(284,total);

    }



}
