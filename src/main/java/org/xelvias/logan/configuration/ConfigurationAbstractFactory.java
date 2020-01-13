package org.xelvias.logan.configuration;

import org.xelvias.logan.configuration.configurationmodels.Configuration;
import org.xelvias.logan.configuration.configurationmodels.ConfigurationNotFoundException;

public interface ConfigurationAbstractFactory {
    Configuration getConfiguration(String path) throws ConfigurationNotFoundException;
}
