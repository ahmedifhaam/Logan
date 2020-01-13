package org.xelvias.logan.state;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Calendar;
import java.util.Properties;


@Component
public class FileStateManager implements StateManager {
    Properties props = new Properties();
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;

    public FileStateManager(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()) file.createNewFile();
        fileInputStream = new FileInputStream(path);
        props.load(fileInputStream);
        fileInputStream.close();
        fileOutputStream = new FileOutputStream(path);

    }

    public FileStateManager() throws IOException {
        this("statemanager.o");
    }

    @Override
    public String getValue(String key) {
        return props.getProperty(key);
    }

    @Override
    public void setValue(String key, String value) {
        props.setProperty(key,value);
    }

    @Override
    public synchronized void saveState() throws IOException {
        props.store(fileOutputStream, Calendar.getInstance().getTime().toString());

    }
}
