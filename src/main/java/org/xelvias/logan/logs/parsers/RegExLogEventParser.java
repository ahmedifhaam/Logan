package org.xelvias.logan.logs.parsers;


import org.springframework.stereotype.Component;
import org.xelvias.logan.logs.events.DefaultLogEvent;
import org.xelvias.logan.logs.events.LogEvent;
import org.xelvias.logan.logs.events.LogEventFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegExLogEventParser implements LogEventParser {
    Pattern regularExpression ;

    public RegExLogEventParser(){
       regularExpression = Pattern.compile("((\\d{2}|\\d{4})\\/\\d{2}\\/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\s)(INFO|TRACE|ERROR|WARNING)(\\s*)(:)(.*)");

    }

    @Override
    public LogEvent parse(String line) {
        LogEvent logEvent = new DefaultLogEvent();
        if(line==null)return logEvent;
        Matcher matcher = regularExpression.matcher(line);
        if(matcher.matches()){
            matcher.group(0);
            logEvent = LogEventFactory.CreateLogEvent(matcher.group(3));
            try {
                logEvent.setTimestamp(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").parse(matcher.group(1)));
            } catch (ParseException e) {
                //TODO:Adder log and continue
            }
            logEvent.setMessage(matcher.group(6));
        }
        return logEvent;
    }
}
