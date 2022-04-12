package Marduk.Javalin.Server.DataManager;


import Marduk.Javalin.Server.DataManager.DataManagerDriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Info Manager
 *
 * @author
 * @version 0.1.0
 */
public class DataManagerDriverTest {

    @Test
    void getInstanceTest(){
        DataManagerDriver dataManagerDriver = DataManagerDriver.getInstance();
        assertNotNull(dataManagerDriver);
    }

    
}
