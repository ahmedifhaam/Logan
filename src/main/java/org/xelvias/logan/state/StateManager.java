package org.xelvias.logan.state;

import java.io.IOException;

public interface StateManager {
    String getValue(String key);
    void setValue(String key,String value);
    void saveState() throws IOException;
}
