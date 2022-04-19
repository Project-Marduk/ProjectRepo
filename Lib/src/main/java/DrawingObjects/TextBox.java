package DrawingObjects;

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

    public TextBox(String t, double x, double y){
        text = t;
        xCord = x;
        yCord = y;
    }

    public String getSVGData(){
        return "<text x=" + '"' + Double.toString(xCord) + '"' +
                " y=" + '"' + Double.toString(yCord) + '"' +
                " font-family=" + '"' + fontStyle + '"' +
                " font-size="+'"' + Double.toString(fontSize) + '"' +
                " fill="+ '"' + "black" + '"' + ">" +
                text + "</text>";
    }
}
