package org.xelvias.logan.configuration;


import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.xelvias.logan.configuration.configurationmodels.Configuration;
import org.xelvias.logan.configuration.configurationmodels.ConfigurationNotFoundException;
import org.xelvias.logan.configuration.configurationmodels.DefaultConfiguration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class DefaultLocalFileConfigurationFactory implements LocalFileConfigurationFactory {
    public Configuration getConfiguration(String path) throws ConfigurationNotFoundException {
        Gson gson = new Gson();
        Configuration configuration ;
        try {
            File file = ResourceUtils.getFile("classpath:"+path);
            FileReader fileReader = new FileReader(file);
            configuration = gson.fromJson(fileReader, DefaultConfiguration.class);
        }catch (IOException ex){
            throw new ConfigurationNotFoundException(ex.getMessage());
        }
        return configuration;
    }
}
