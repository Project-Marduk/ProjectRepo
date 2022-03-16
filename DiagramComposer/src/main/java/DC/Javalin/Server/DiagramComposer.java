package DC.Javalin.Server;

/**
 * Diagram Composer Driver class.
 *
 * Handles the calls from the server to interact with the File Exporter Microservice.
 *
 * @author Traae
 * @version 0.1.0
 */
public class DiagramComposer {
    private static DiagramComposer instance = null;
    //private Diagram active, initial;

    private DiagramComposer(){}
    public static DiagramComposer getInstance() {
        if (instance == null){
            instance = new DiagramComposer();
        }
        return instance;
    }
}
