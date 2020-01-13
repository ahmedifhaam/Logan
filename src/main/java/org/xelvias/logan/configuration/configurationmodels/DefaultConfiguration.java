package org.xelvias.logan.configuration.configurationmodels;


import org.xelvias.logan.configuration.configurationmodels.subelements.ClientAppConfiguration;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DefaultConfiguration implements Configuration {
    public List<ClientAppConfiguration> configurations;

    public Iterator<ClientAppConfiguration> getAllApplicationConfigs() {
        return configurations.iterator();
    }

    public ClientAppConfiguration getClientAppConfiguration(int index) {
        return configurations.get(index);
    }

    public int getTotalApplicationsInConfiguration() {
        return configurations.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultConfiguration that = (DefaultConfiguration) o;
        return Objects.equals(configurations, that.configurations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(configurations);
    }
}
