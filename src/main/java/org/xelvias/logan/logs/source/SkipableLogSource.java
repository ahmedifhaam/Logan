package org.xelvias.logan.logs.source;

import org.xelvias.logan.logs.source.LogSource;

import java.io.IOException;

public interface SkipableLogSource extends LogSource {
    void skipAlreadyRead() throws IOException;
    void updateSkipLocation() throws IOException;
}
