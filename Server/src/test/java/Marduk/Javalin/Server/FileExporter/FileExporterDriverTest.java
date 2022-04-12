package Marduk.Javalin.Server.FileExporter;

import Marduk.Javalin.Server.FileExporter.FileExporterDriver;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**\
 * The test class for the File Exporter
 *
 *
 * @author
 * @version 0.1.0
 */
public class FileExporterDriverTest {

    @Test
    void getInstanceTest(){
        FileExporterDriver fileExporterDriver = FileExporterDriver.getInstance();
        assertNotNull(fileExporterDriver);
    }

}
