package org.xelvias.logan.state;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class FileStateManagerTest {
    File file ;





    @Test
    public void TestFor_RuntimeValues() throws IOException {
        FileStateManager fileStateManager = new FileStateManager();
        String key = "testkey";
        String value = "testvalue";
        fileStateManager.setValue(key,value);
        Assert.assertEquals(value,fileStateManager.getValue(key));
    }

    @Test
    public void TestFor_LongValuesConversion() throws IOException{
        FileStateManager fileStateManager = new FileStateManager();
        String key = "/User/xelvias/home";
        Long value = 232556L;
        fileStateManager.setValue(key,Long.toString(value));
        Long ret = Long.parseLong(fileStateManager.getValue(key));
        Assert.assertEquals(value,ret);

    }

    @Test
    public void TestFor_Persistence_ExpectNullValue_WithoutSaving() throws IOException {
        FileStateManager fileStateManager = new FileStateManager();
        String key = "testkey";
        String value = "testvalue";
        fileStateManager.setValue(key,value);
        fileStateManager = new FileStateManager();
        Assert.assertNull(fileStateManager.getValue(key));
    }

    @Test
    public void TestFor_Persistence_ExpectNotNullAfterSaving() throws IOException{
        FileStateManager fileStateManager = new FileStateManager();
        String key = "testkey";
        String value = "testvalue";
        fileStateManager.setValue(key,value);
        fileStateManager.saveState();
        fileStateManager = new FileStateManager();
        Assert.assertEquals(value,fileStateManager.getValue(key));
    }



}
