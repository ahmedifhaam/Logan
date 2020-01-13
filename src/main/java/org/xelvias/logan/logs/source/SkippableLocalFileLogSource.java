package org.xelvias.logan.logs.source;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xelvias.logan.logs.events.LogEvent;
import org.xelvias.logan.logs.parsers.LogEventParser;
import org.xelvias.logan.state.StateManager;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;




public class SkippableLocalFileLogSource implements SkipableLogSource {
    String path;
    RandomAccessFile fileAccess;
    StateManager stateManager;

    private static final Logger LOG = LoggerFactory.getLogger(SkippableLocalFileLogSource.class);

    public SkippableLocalFileLogSource(String path, StateManager stateManager) throws IOException {
        this.path = path;
        fileAccess = new RandomAccessFile(path,"rw");
        this.stateManager = stateManager;
    }


    @Override
    public Iterator<LogEvent> getEvents(LogEventParser parser) throws IOException {
        String line = null;
        ArrayList<LogEvent> logEvents = new ArrayList<>();
        while(null != (line = fileAccess.readLine())){
            logEvents.add(parser.parse(line));
        }
        return logEvents.iterator();
    }

    @Override
    public void updateSkipLocation() throws IOException {
        stateManager.setValue(path, Long.toString(fileAccess.getFilePointer()));
    }

    @Override
    public void skipAlreadyRead() throws IOException {
        Long lastRead = null;
        try{
            lastRead = Long.parseLong(stateManager.getValue(path));
        }catch (NumberFormatException ex){
            LOG.error(ex.getMessage());
        }

        if(lastRead!=null){
            fileAccess.seek(lastRead);

        }
    }
}
