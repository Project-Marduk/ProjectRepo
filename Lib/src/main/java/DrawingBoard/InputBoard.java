package DrawingBoard;

import FactoryElements.InputObject;

import java.io.Serializable;
import java.util.ArrayList;

public class InputBoard implements Serializable {
    public String name;
    public String id;
    public double xMax;
    public double yMax;
    public int idIndex;

    private ArrayList<InputObject> inputObjectsList;

    public InputBoard(){
        inputObjectsList = new ArrayList<>();
    }
    public ArrayList<InputObject> getInputObjectsList() {
        return inputObjectsList;
    }
}
