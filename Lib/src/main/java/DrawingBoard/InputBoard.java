package DrawingBoard;

import FactoryElements.InputObject;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;


@Getter @Setter
public class InputBoard implements Serializable {
    public String name;
    public String id;
    public double xMax;
    public double yMax;
    public int folderId;

    private ArrayList<InputObject> inputObjectsList;

    public InputBoard(){
        inputObjectsList = new ArrayList<>();
    }
    public ArrayList<InputObject> getInputObjectsList() {
        return inputObjectsList;
    }
    public void setInObjects(ArrayList<InputObject> inObjs){
        inputObjectsList = inObjs;
    }
}
