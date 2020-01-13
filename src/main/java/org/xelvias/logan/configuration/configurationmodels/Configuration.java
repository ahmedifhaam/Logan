package org.xelvias.logan.configuration.configurationmodels;

import org.xelvias.logan.configuration.configurationmodels.subelements.ClientAppConfiguration;
import java.util.Iterator;

public interface Configuration {
    Iterator<ClientAppConfiguration> getAllApplicationConfigs();
    ClientAppConfiguration getClientAppConfiguration(int index);
    int getTotalApplicationsInConfiguration();
}
