package org.xelvias.logan.logs.events;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;

public class LogEventFactoryTest {



    @Test
    public void Should_Return_InfoLogEvent(){
        LogEvent logEvent = LogEventFactory.CreateLogEvent("info");

        Assert.assertThat(logEvent,instanceOf(InfoLogEvent.class));
    }

    @Test
    public void Should_Return_ErrorLogEvent(){
        LogEvent logEvent = LogEventFactory.CreateLogEvent("error");

        Assert.assertThat(logEvent,instanceOf(ErrorLogEvent.class));
    }

    @Test
    public void Should_Return_TraceLogEvent(){
        LogEvent logEvent = LogEventFactory.CreateLogEvent("trace");

        Assert.assertThat(logEvent,instanceOf(TraceLogEvent.class));
    }

    @Test
    public void Should_Return_DefaultLogEvent(){
        LogEvent logEvent = LogEventFactory.CreateLogEvent("wrong");

        Assert.assertThat(logEvent,instanceOf(DefaultLogEvent.class));
    }
}
