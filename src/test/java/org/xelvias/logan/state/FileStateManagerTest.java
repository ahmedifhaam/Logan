package org.xelvias.logan.state;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;

@RunWith(MockitoJUnitRunner.class)
public class FileStateManagerTest {
    File file ;


    @Before
    public void CreateFileBeforeEachRun(){
        file = new File("filestate.o");
    }

    @After
    public void DeleteAfterEachRun(){
        file.delete();
    }


    @Test
    public void TestForRuntimeValues() throws IOException {
        FileStateManager fileStateManager = new FileStateManager("filestate.o");
        String key = "testkey";
        String value = "testvalue";
        fileStateManager.setValue(key,value);
        Assert.assertEquals(value,fileStateManager.getValue(key));
    }

    @Test
    public void TestForLongValues() throws IOException{
        FileStateManager fileStateManager = new FileStateManager("filestate.o");
        String key = "/User/xelvias/home";
        Long value = 232556L;
        fileStateManager.setValue(key,Long.toString(value));
        Long ret = Long.parseLong(fileStateManager.getValue(key));
        Assert.assertEquals(value,ret);

    }

    @Test
    public void TestForPersistence_ExpectNullValueWithoutSaving() throws IOException {
        FileStateManager fileStateManager = new FileStateManager("filestate.o");
        String key = "testkey";
        String value = "testvalue";
        fileStateManager.setValue(key,value);
        fileStateManager = new FileStateManager("filestate.o");
        Assert.assertNull(fileStateManager.getValue(key));
    }

    @Test
    public void TestForPersistence_ExpectNotNullAfterSaving() throws IOException{
        FileStateManager fileStateManager = new FileStateManager("filestate.o");
        String key = "testkey";
        String value = "testvalue";
        fileStateManager.setValue(key,value);
        fileStateManager.saveState();
        fileStateManager = new FileStateManager("filestate.o");
        Assert.assertEquals(value,fileStateManager.getValue(key));
    }



}
