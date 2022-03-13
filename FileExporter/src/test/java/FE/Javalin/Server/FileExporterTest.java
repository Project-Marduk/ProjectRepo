package FE.Javalin.Server;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**\
 * The test class for the File Exporter
 *
 *
 * @author
 * @version 0.1.0
 */
public class FileExporterTest {

    @Test
    void getInstanceTest(){
        FileExporter fileExporter = FileExporter.getInstance();
        assertNotNull(fileExporter);
    }

}
