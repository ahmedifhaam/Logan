package org.xelvias.logan.logs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xelvias.logan.logs.source.SkipableLogSource;
import org.xelvias.logan.logs.source.SkippableLocalFileLogSource;
import org.xelvias.logan.logs.source.LogSource;
import org.xelvias.logan.logs.source.SkippableLogSourceFactory;
import org.xelvias.logan.state.StateManager;

import java.io.IOException;

@Component
public class LogPathBasedSkippableLogSourceFactory implements SkippableLogSourceFactory {
    StateManager stateManager;

    @Autowired
    public LogPathBasedSkippableLogSourceFactory(StateManager stateManager){
        this.stateManager = stateManager;
    }

    @Override
    public SkipableLogSource createLogSource(String path) throws IOException {
        if(path.indexOf("ftp:")>-1){
            return null;
        }else{
            return new SkippableLocalFileLogSource(path,stateManager);
        }
    }


}
