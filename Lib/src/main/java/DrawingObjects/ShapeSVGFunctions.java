package DrawingObjects;

import FactoryElements.InputObject;

/**
 * @author David Lindeman
 * Methods that translate an input object into the string representation of their corresponding svg element
 * These methods will need to feed into a "generateSVG" method which adds the header and tail svg components
 * TODO: Bring up that bounds checking will need to be done with shapes that are generated from the origin of the xy (hexagons, circles) as the uppermost and leftmost positions do not share the same cordinate
 */
public class ShapeSVGFunctions {

    public static String rectToSVG(InputObject inObj){
        return "<rect " +
                "x=\"" + Double.toString(inObj.getXCord()) + "\" " +
                "y=\"" + Double.toString(inObj.getYCord()) + "\" " +
                "fill=\"" + inObj.getFill() + "\" " +
                "width=\"" + Double.toString(inObj.getParams()[0]) + "\" " +
                "stroke-linejoin=\"round\" " +
                "height=\"" + Double.toString(inObj.getParams()[1]) + "\" " +
                "stroke=\"#000000\"/>";
    }

    public static String squareToSVG(InputObject inObj){
        return "<rect " +
                "x=\"" + Double.toString(inObj.getXCord()) + "\" " +
                "y=\"" + Double.toString(inObj.getYCord()) + "\" " +
                "fill=\"" + inObj.getFill() + "\" " +
                "width=\"" + Double.toString(inObj.getParams()[0]) + "\" " +
                "stroke-linejoin=\"round\" " +
                "height=\"" + Double.toString(inObj.getParams()[0]) + "\" " +
                "stroke=\"#000000\"/>";
    }

    public static String circleToSVG(InputObject inObj){
        return "<ellipse " +
                "cy=\"" + Double.toString(inObj.getXCord()) + "\" " +
                "cx=\"" + Double.toString(inObj.getYCord()) + "\" " +
                "ry=\"" + Double.toString(inObj.getParams()[0]) + "\" " +
                "rx=\"" + Double.toString(inObj.getParams()[0]) + "\" " +
                "fill=\""  + inObj.getFill() + "\" " +
                "stroke-linejoin=\"round\" " +
                "stroke=\"#000000\"/>";
    }

    /**
     * @author David Lindeman
     * @param inObj
     * @return
     * to create a hexagon we're going to use 6 different lines starting at the leftmost and drawing clockwise
     */
    public static String hexagonToSvg(InputObject inObj){
        //variables used in calculating the 6 points
        double xOrigin = inObj.getXCord() - inObj.getParams()[0];
        double yOrigin = inObj.getYCord() + inObj.getParams()[0];
        String leftmostX = Double.toString(xOrigin - inObj.getParams()[0]);
        String rightMostX = Double.toString(xOrigin + inObj.getParams()[0]);
        String midY = Double.toString(yOrigin);

        String leftX = Double.toString(xOrigin - (inObj.getParams()[0])/2);
        String upY = Double.toString(yOrigin + (inObj.getParams()[0] - inObj.getParams()[0]/2)); //TODO: Square both inner elements then get the sqrt of the equation

        String rightX = Double.toString(xOrigin + (inObj.getParams()[0])/2);
        String downY = Double.toString(yOrigin - (inObj.getParams()[0] - inObj.getParams()[0]/2)); //TODO: Square both inner elements then get the sqrt of the equation

        //this was made with the assumption that the X Y cords are the center of the shape
        return //line1 leftmost
                getLineElement(leftmostX, leftX, midY, upY) +
                //line2 up left
                getLineElement(leftX, rightX, upY, upY) +
                //line3
                getLineElement(rightX, rightMostX, upY, midY) +
                //line4
                getLineElement(rightMostX, rightX, midY, downY) +
                //line5
                getLineElement(rightX, leftX, downY, downY) +
                //line6
                getLineElement(leftX, leftmostX, downY, midY);
    }

    /**
     * @author David Lindeman
     * @param inObj
     * @return
     * Draws a rectangle that appears to have its upper right corner folded in
     * Comprised of 7 line objects
     */
    public static String activationExpressionSVG(InputObject inObj){
        String leftX = Double.toString(inObj.getXCord());
        String rightX = Double.toString(inObj.getXCord() + inObj.getParams()[1]);

        String downY = Double.toString(inObj.getYCord() - inObj.getParams()[1]);
        String upY = Double.toString((inObj.getYCord()));

        String triDownY = Double.toString(inObj.getYCord() - inObj.getParams()[1]*.15);
        String triLeftX = Double.toString(inObj.getXCord() + inObj.getParams()[1]*.85);

        return
                //line1 rightmost line
                getLineElement(leftX, leftX, upY, downY) +
                //line2 bottom line
                getLineElement(leftX, rightX, downY, downY) +
                //line3 rightmost line
                getLineElement(rightX, rightX, downY, triDownY) +
                //line4 fold triangle base
                getLineElement(rightX, triLeftX, triDownY, triDownY) +
                //line5 fold triangle diagonal
                getLineElement(rightX, triLeftX, triDownY, upY) +
                //line6 fold triangle height
                getLineElement(triLeftX, triLeftX, triDownY, upY) +
                //line7 top line
                getLineElement(triLeftX, leftX, upY, upY);
    }

    /**
     * @author David Lindeman
     * @param inObj
     * @return
     */
    public static String parallelogramToSVG(InputObject inObj){
        String upLeftX = Double.toString(inObj.getXCord() + .15*inObj.getParams()[1]);
        String upLeftRightY = Double.toString(inObj.getYCord());
        String upRightX = Double.toString(1.15*inObj.getXCord() + inObj.getParams()[1]);
        String downLeftX = Double.toString(inObj.getXCord());
        String downLeftRightY = Double.toString(inObj.getYCord() - inObj.getParams()[0]);
        String downRightX = Double.toString(inObj.getXCord() + .85*inObj.getParams()[1]);

        return getLineElement(upLeftX, upRightX, upLeftRightY, upLeftRightY) +
                getLineElement(upRightX, downRightX, upLeftRightY, downLeftRightY) +
                getLineElement(downRightX, downLeftX, downLeftRightY, downLeftRightY) +
                getLineElement(downLeftX, upLeftX, downLeftRightY, upLeftRightY);
    }

    //TODO: SVG for creating a triangle

    /**
     * @author David Lindeman
     * @param lineX
     * @param lineY
     * @return
     * Generates a filled black triangle with a static size
     */
    public static String headTriangleToSVG(double lineX, double lineY, boolean pointIsVert, boolean isUpOrRight){
        String upLeftX;
        String upLeftY;
        String x2;
        String y2;
        String x3;
        String y3;

        double triSideLen = 4; //aribitrary length

        //is up
        if(pointIsVert && isUpOrRight){
            upLeftX = Double.toString(lineX - triSideLen/2);
            upLeftY = Double.toString(lineY);
            x2 = Double.toString(lineX-triSideLen/2);
            y2 = Double.toString(lineY-triSideLen);
            x3 = Double.toString(lineX+triSideLen/2);
            y3 = y2;
        }
        //is down
        else if(pointIsVert){
            upLeftX = Double.toString(lineX);
            upLeftY = Double.toString(lineY + triSideLen/2);
            x2 = Double.toString(lineX-triSideLen/2);
            y2 = Double.toString(lineY+triSideLen);
            x3 = Double.toString(lineX+triSideLen/2);
            y3 = y2;
        }
        //is right
        else if(isUpOrRight){
            x2 = Double.toString(lineX - triSideLen);
            y2 = Double.toString(lineY + triSideLen/2);
            x3 = x2;
            y3 = Double.toString(lineY - triSideLen/2);
        }
        //is left
        else{
            x2 = Double.toString(lineX + triSideLen);
            y2 = Double.toString(lineY + triSideLen/2);
            x3 = x2;
            y3 = Double.toString(lineY - triSideLen/2);
        }

        return
                getLineElement(Double.toString(lineX), x2, Double.toString(lineY), y2) +
                getLineElement(x2, x3, y2, y3) +
                getLineElement(x3, Double.toString(lineX), y3, Double.toString(lineY));
        //TODO: Figure out how to fill a line generated polygon
    }

    public static String getLineElement(String x1, String x2, String y1, String y2){
        String outString =
                "<line " +
                "fill=\"none\" " +
                "x1=\"" + x1 + "\" " +
                "x2=\"" + x2 + "\" " +
                "stroke=\"#000000\" " +
                "y1=\"" + y1 + "\" " +
                "y2=\"" + y2 + "\"/>";
        return outString;
    }
}
