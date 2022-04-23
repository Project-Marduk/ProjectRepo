package DrawingObjects;

import FactoryElements.InputObject;
import DrawingObjects.Interfaces.ComplexShape;
import DrawingObjects.Interfaces.JavaFXGroupShape;
import javafx.scene.Group;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author David Lindeman
 * All Objects that will be displayed in the pane will extend this
 * Though all have at least one text box the text variable will not be stored in the abstract class
 * This is because all have 1+ text boxes each will be required to be their own text box
 */
@Getter @Setter
public abstract class DrawingObject extends Group implements ComplexShape, JavaFXGroupShape { // extends Model
    InputObject inObject;
    TextBox[] textBoxes;

    public DrawingObject(){

    }
    public DrawingObject(InputObject in){
        inObject = in;
    }
    public DrawingObject(String newId, InputObject inObj){
        inObject = inObj;
        inObject.setId(Integer.valueOf(newId));
    }

    public String getSVGData(){
        return generateShape();
    }

    public void update(){
        getChildren().clear();
        generateJavaFXGroup();
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


    public TextBox getTextBox(int position){
//        TextBox outBox = null;
        try{
            return textBoxes[position];
        }
        catch(IndexOutOfBoundsException e){
            //return the last entry of the list
            return textBoxes[textBoxes.length-1];
        }
        catch(Exception e){
            return null;
        }
    }

    public void setTextBox(int position, TextBox tb){
        try{
            textBoxes[position] = tb;
        }
        catch(IndexOutOfBoundsException e){
            textBoxes[textBoxes.length-1] = tb;
        }
        catch(Exception e){

        }
    }

    protected void addTextBoxesToJavaFXGroup(){
        for (TextBox tb: textBoxes) {
            getChildren().add(tb.getJavaFXText());
        }
    }


    //    public <T> decodeType(DrawingObject dwObj){
//
//    }
}
