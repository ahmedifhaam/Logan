package org.xelvias.logan.logs.parsers;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.xelvias.logan.logs.events.DefaultLogEvent;
import org.xelvias.logan.logs.events.InfoLogEvent;
import org.xelvias.logan.logs.events.LogEvent;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.CoreMatchers.instanceOf;

@RunWith(MockitoJUnitRunner.class)
public class RegExLogEventParserTest {
    RegExLogEventParser logEventParser = new RegExLogEventParser();

    @Test
    public void ShouldReturn_DefaultLogEvent_ForNull(){
        Assert.assertThat(logEventParser.parse(null),instanceOf(DefaultLogEvent.class));
    }

    @Test
    public void ShouldReturn_DefaultLogEvent_ForInvalidStirng(){
        Assert.assertThat(logEventParser.parse(""),instanceOf(DefaultLogEvent.class));
    }

    @Test
    public void ShouldReturn_InfoLogEvent(){
        String in = "2019/03/22 08:51:01 INFO   :.main: *************** RSVP Agent started ***************";
        LogEvent logEvent = logEventParser.parse(in);
        Assert.assertThat(logEventParser.parse(in),instanceOf(InfoLogEvent.class));
    }

    @Test
    public void Should_Return_SavedMessageValue(){
        String in = "2019/03/22 08:51:01 INFO   :.main";
        LogEvent logEvent = logEventParser.parse(in);
        Assert.assertTrue(logEvent.getMessage().trim().equalsIgnoreCase(".main"));
    }

    @Test
    public void Should_Return_SavedDateValue() throws ParseException {
        String in = "2019/03/22 08:51:01 INFO   :.main";
        LogEvent logEvent = logEventParser.parse(in);
        Assert.assertTrue(logEvent.getTimestamp().equals(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse("2019/03/22 08:51:01")));
    }
}
