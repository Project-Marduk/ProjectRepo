package FactoryElements.Lines;

import FactoryElements.Interfaces.Line;
import lombok.Getter;
import lombok.Setter;

import static FactoryElements.ShapeSVGFunctions.getLineElement;

/**
 * @author David Lindeman
 * Abstract line class with basic line functionality
 */
@Getter @Setter
public abstract class AbstractLine implements Line {
    double leftXCord;
    double leftYCord;
    double rightXCord;
    double rightYCord;
    String lineStyle;
    String svgData;

//    String color; //Use hex to represent

    public AbstractLine(double lX, double lY, double rX, double rY, String lStyle){
        leftXCord = lX;
        leftYCord = lY;
        rightXCord = rX;
        rightYCord = rY;
        lineStyle = lStyle;
    }

    public void getLineSVG(){
        //TODO: Update getLineElement to allow different line types
        svgData = getLineElement(
                Double.toString(leftXCord),
                Double.toString(rightXCord),
                Double.toString(leftYCord),
                Double.toString(rightYCord)
        );
    }

    public void changeLineStyle(){

    }
}