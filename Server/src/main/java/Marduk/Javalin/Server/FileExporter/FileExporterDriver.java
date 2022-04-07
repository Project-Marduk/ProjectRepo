package Marduk.Javalin.Server;

/**
 * File Exporter Driver class.
 *
 * Handles the calls from the server to interact with the File Exporter Microservice.
 *
 * @author Steve
 * @version 0.1.0
 */
public class FileExporter {
    private static FileExporter instance = null;

    private FileExporter(){}
    public static FileExporter getInstance() {
        if (instance == null){
            instance = new FileExporter();
        }
        return instance;
    }
}
