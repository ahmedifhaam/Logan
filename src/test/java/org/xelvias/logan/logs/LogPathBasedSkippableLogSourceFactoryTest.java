package org.xelvias.logan.logs;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.xelvias.logan.logs.source.SkippableLocalFileLogSource;
import org.xelvias.logan.logs.source.LogSource;
import org.xelvias.logan.state.StateManager;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.instanceOf;

@RunWith(MockitoJUnitRunner.class)
public class LogPathBasedSkippableLogSourceFactoryTest {

    @Mock
    StateManager stateManager;

    @Test
    public void Should_Return_SkippableSource() throws IOException {
        LogSourceAbstractFactory logSourceFactory = new LogPathBasedSkippableLogSourceFactory(stateManager);
        LogSource logSource = logSourceFactory.createLogSource("application.config");
        Assert.assertThat(logSource,instanceOf(SkippableLocalFileLogSource.class));
    }

    @Test
    public void Should_Return_Null_ForFTPLog() throws IOException {
        LogSourceAbstractFactory logSourceAbstractFactory = new LogPathBasedSkippableLogSourceFactory(stateManager);
        LogSource logSource = logSourceAbstractFactory.createLogSource("ftp://testing");
        Assert.assertNull(logSource);
    }


}
