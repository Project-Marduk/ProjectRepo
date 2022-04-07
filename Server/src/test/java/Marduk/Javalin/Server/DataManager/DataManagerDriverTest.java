package Marduk.Javalin.Server;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Info Manager
 *
 * @author
 * @version 0.1.0
 */
public class DataManagerTest {

    @Test
    void getInstanceTest(){
        DataManager dataManager = DataManager.getInstance();
        assertNotNull(dataManager);
    }

    
}
