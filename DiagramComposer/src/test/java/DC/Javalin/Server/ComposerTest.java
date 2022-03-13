package DC.Javalin.Server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Diagram Composer
 *
 * @author Traae
 * @version 0.1.0
 */
public class ComposerTest {

    @Test void getInstanceTest(){
        assertNotNull(Composer.getInstance());
    }
}
