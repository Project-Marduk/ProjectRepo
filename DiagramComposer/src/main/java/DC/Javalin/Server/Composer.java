package DC.Javalin.Server;


/**
 * The Diagram Composer
 *
 * Holds the actual logic for modifying Diagram
 *
 * @author Traae
 * @version 0.1.0
 */
public class Composer {
    private static Composer instance = null;
    //Diagram initial, active;
    String errorMessage;

    private Composer(){
    }
    public static Composer getInstance() {
        if (instance == null){
            instance = new Composer();
        }
        return instance;
    }

    /**
     * @return A description of the error the last error to occur.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    public int loadTestObject(){

        return 0;
    }
    public void processTo(){}

    public int loadDiagram(/*Diagram diagram*/){
        //initial = diagram;
        //active = diagram;
        return 0;
    }




}
