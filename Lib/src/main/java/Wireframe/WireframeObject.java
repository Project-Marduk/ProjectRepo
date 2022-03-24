package Wireframe;

import FactoryElements.InputObject;

public abstract class WireframeObject {
    String svgData;
    String id;
    double x;
    double y;
    InputObject inObject;
    String shapeSVG;

    public WireframeObject(String newId, InputObject inObj){
        id = newId;
        svgData = "";
        inObject = inObj;
        x = inObj.getXCord();
        y = inObj.getYCord();
    }

    public String getSVGData(){
        return svgData;
    }

    //This will be the translation of text to SVG data
    //This may be unnecessary depending on how we convert text to SVG
    public String txtToSVG(String text, double xPos, double yPos){
        return "<text x=" + '"' + Double.toString(xPos) + '"' +
                "y=" + '"' + Double.toString(yPos) +
                " font-family=" + '"' + "Verdana" + '"' +
                " font-size="+'"' + "18" + '"' +
                "fill="+ '"' + "black" + '"' + ">" +
                text + "</text>";
    }

}
