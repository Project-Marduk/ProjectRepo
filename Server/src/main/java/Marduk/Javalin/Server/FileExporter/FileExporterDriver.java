package Marduk.Javalin.Server.FileExporter;

/**
 * File Exporter Driver class.
 *
 * Handles the calls from the server to interact with the File Exporter Microservice.
 *
 * @author Steve
 * @version 0.1.0
 */
public class FileExporterDriver {
    private static FileExporterDriver instance = null;

    private FileExporterDriver(){}
    public static FileExporterDriver getInstance() {
        if (instance == null){
            instance = new FileExporterDriver();
        }
        return instance;
    }
}
