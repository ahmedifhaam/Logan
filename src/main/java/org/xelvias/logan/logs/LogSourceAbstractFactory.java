package org.xelvias.logan.logs;

import org.xelvias.logan.logs.source.LogSource;

import java.io.IOException;

public interface LogSourceAbstractFactory<T extends LogSource> {
    T createLogSource(String type) throws IOException;
}
