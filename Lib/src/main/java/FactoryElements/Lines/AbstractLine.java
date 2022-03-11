package FactoryElements.Lines;

/**
 * @author David Lindeman
 * Abstract line class with basic line functionality
 */

public abstract class AbstractLine implements Line{
    int leftXCord;
    int leftYCord;
    int rightXCord;
    int rightYCord;
    String lineStyle;

//    String color; //Use hex or RGB to represent?

    public AbstractLine(int lX, int lY, int rX, int rY, String lStyle){
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