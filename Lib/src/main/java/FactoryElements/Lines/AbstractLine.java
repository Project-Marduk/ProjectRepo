package FactoryElements.Lines;

import FactoryElements.Interfaces.Line;

/**
 * @author David Lindeman
 * Abstract line class with basic line functionality
 */

public abstract class AbstractLine implements Line {
    double leftXCord;
    double leftYCord;
    double rightXCord;
    double rightYCord;
    String lineStyle;

//    String color; //Use hex or RGB to represent?

    public AbstractLine(double lX, double lY, double rX, double rY, String lStyle){
        leftXCord = lX;
        leftYCord = lY;
        rightXCord = rX;
        rightYCord = rY;
        lineStyle = lStyle;
    }

    public void draw(){

    }

    public void changeLineStyle(){

    }
}