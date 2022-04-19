package DrawingObjects;

import FactoryElements.InputObject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author David Lindeman
 *
 */
@Getter @Setter
public class TextBox  {
    String text = "";
    double fontSize = 12;
    String fontStyle = "Verdana";
    double xCord;
    double yCord;


    public void initialize(String t, double x, double y){


    }
    public TextBox(){}

    public String getSVGData(){
        return "<text x=" + '"' + Double.toString(xCord) + '"' +
                " y=" + '"' + Double.toString(yCord) + '"' +
                " font-family=" + '"' + fontStyle + '"' +
                " font-size="+'"' + Double.toString(fontSize) + '"' +
                " fill="+ '"' + "black" + '"' + ">" +
                text + "</text>";
    }
}
