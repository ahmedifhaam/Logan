package org.xelvias.logan.configuration;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.xelvias.logan.configuration.configurationmodels.ConfigurationNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class DefaultLocalFileConfigurationFactoryTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    DefaultLocalFileConfigurationFactory configurationFactory = new DefaultLocalFileConfigurationFactory();

    @Test
    public void TestForExceptionOnInvalidLocation() throws ConfigurationNotFoundException {
        expectedException.expect(ConfigurationNotFoundException.class);
        configurationFactory.getConfiguration("invlaidpath");
    }

    @Test
    public void TestFileNotBeingNull() throws ConfigurationNotFoundException{
        Assert.assertNotNull(configurationFactory.getConfiguration("application.config"));
    }


}
