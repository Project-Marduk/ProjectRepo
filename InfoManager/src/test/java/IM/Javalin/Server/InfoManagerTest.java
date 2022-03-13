package IM.Javalin.Server;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Info Manager
 *
 * @author
 * @version 0.1.0
 */
public class InfoManagerTest {

    @Test
    void getInstanceTest(){
        InfoManager infoManager = InfoManager.getInstance();
        assertNotNull(infoManager);
    }

    
}
