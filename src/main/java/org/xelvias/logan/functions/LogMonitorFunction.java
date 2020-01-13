package org.xelvias.logan.functions;

import org.xelvias.logan.logs.source.LogSource;

import java.io.IOException;

public interface LogMonitorFunction<T extends LogSource> extends Function {

    void loadlogs() throws IOException;
    void notifyUsers();
    void updateDataSource(T logSource);


}
