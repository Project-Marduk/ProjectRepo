package DrawingObjects;

import FactoryElements.InputObject;
import FactoryElements.Interfaces.ComplexShape;
import lombok.Getter;
import lombok.Setter;

/**
 * @author David Lindeman
 * All Objects that will be displayed in the pane will extend this
 * Though all have at least one text box the text variable will not be stored in the abstract class
 * This is because all have 1+ text boxes each will be required to be their own text box
 */
@Getter @Setter
public abstract class DrawingObjectAJDBC implements ComplexShape { // extends Model
    String id;
    double x;
    double y;
    InputObject inObject;
    TextBox[] textBoxes;

    public DrawingObjectAJDBC(String newId, InputObject inObj){
        id = newId;
        inObject = inObj;
        x = inObj.getXCord();
        y = inObj.getYCord();
    }

    public String getSVGData(){
        return generateShape();
    }

    //This will be the translation of text to SVG data
    //This may be unnecessary depending on how we convert text to SVG
    public String txtToSVG(){
        String svgTextData = "";
        for(TextBox tb : textBoxes){
            svgTextData += tb.getSVGData() + "\n";
        }

        return svgTextData;
    }

    public TextBox getTextBox(int pos){
//        TextBox outBox = null;
        try{
            return textBoxes[pos];
        }
        catch(IndexOutOfBoundsException e){
            //return the last entry of the list
            return textBoxes[textBoxes.length-1];
        }
        catch(Exception e){
            return null;
        }
    }

    public void setTextBox(int pos, TextBox tb){
        try{
            textBoxes[pos] = tb;
        }
        catch(IndexOutOfBoundsException e){
            textBoxes[textBoxes.length-1] = tb;
        }
        catch(Exception e){

        }
    }

//    public <T> decodeType(DrawingObject dwObj){
//
//    }
}
