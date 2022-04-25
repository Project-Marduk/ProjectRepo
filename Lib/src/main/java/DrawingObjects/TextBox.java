package DrawingObjects;

import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;
import javafx.scene.text.Text;

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

    public Text getJavaFXText(){
        Text t = new Text(text);
        t.setX(xCord);
        t.setY(yCord);
        return t;
    }
}
