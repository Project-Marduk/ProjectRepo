package DrawingObjects.JavaFXConversion;

import FactoryElements.InputObject;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;


/**
 * A set of functions that mirror ShapeSCGFunctions.
 * They take out Drawing objects and produce a JavaFx object to mirror them.
 *
 * @author Traae
 * @version 1.0
 */
public class ShapeJavaFXFunctions {

    public static Path addLinetoPath(double x1, double x2, double y1, double y2, Path p){
        p.getElements().addAll(new MoveTo(x1, y1), new LineTo(x2, y2));
        return p;
    }

    public static Color hexToRgb(String fill) {
        return new Color(
                Integer.valueOf(fill.substring( 1, 3 ), 16),
                Integer.valueOf(fill.substring( 3, 5 ), 16),
                Integer.valueOf(fill.substring( 5, 7 ), 16 ),
                1);
    }

    public static Rectangle rectToJavaFX(InputObject inObj){
        Rectangle r = new Rectangle();
        r.setX(inObj.getXCord());
        r.setY(inObj.getYCord());
        r.setFill(hexToRgb(inObj.getFill()));
        r.setWidth(inObj.getParams()[0]);
        r.setHeight(inObj.getParams()[1]);
        r.setArcWidth(r.getWidth() / 20);
        r.setArcHeight(r.getHeight() / 20);
        return r;
    }

    public static Rectangle squareToJavaFX(InputObject inObj){
        Rectangle square = new Rectangle();
        square.setX(inObj.getXCord());
        square.setY(inObj.getXCord());
        square.setFill(hexToRgb(inObj.getFill()));
        square.setWidth(inObj.getParams()[0]);
        square.setHeight(inObj.getParams()[0]);
        square.setArcWidth(square.getWidth() / 20);
        square.setArcHeight(square.getHeight() / 20);
        return square;
    }

    public static Circle circleToJavaFX(InputObject inObj){
        Circle c = new Circle();
        c.setCenterX(inObj.getXCord());
        c.setCenterY(inObj.getYCord());
        c.setRadius(inObj.getParams()[0]);
        c.setFill(hexToRgb(inObj.getFill()));
        return c;
    }

    public static Path hexagonToJavaFX(InputObject inObj){
        Path path = new Path();
        path.setFill(hexToRgb(inObj.getFill()));

        //variables used in calculating the 6 points
        double xOrigin = inObj.getXCord() - inObj.getParams()[0];
        double yOrigin = inObj.getYCord() + inObj.getParams()[0];

        double leftmostX = xOrigin - inObj.getParams()[0];
        double rightMostX = xOrigin + inObj.getParams()[0];
        double midY = yOrigin;

        double leftX = xOrigin - (inObj.getParams()[0])/2;
        double upY = yOrigin + (inObj.getParams()[0] - inObj.getParams()[0]/2); //TODO: Square both inner elements then get the sqrt of the equation

        double rightX = xOrigin + (inObj.getParams()[0])/2;
        double downY = yOrigin - (inObj.getParams()[0] - inObj.getParams()[0]/2); //TODO: Square both inner elements then get the sqrt of the equation

        addLinetoPath(leftmostX, leftX, midY, upY, path);
        addLinetoPath(leftX, rightX, upY, upY, path);
        addLinetoPath(rightX, rightMostX, upY, midY, path);
        addLinetoPath(rightMostX, rightX, midY, downY, path);
        addLinetoPath(rightX, leftX, downY, downY, path);
        addLinetoPath(leftX, leftmostX, downY, midY, path);
        return path;
    }

    public static Path activationExpressionJFX(InputObject inObj){
        Path path = new Path();
        path.setFill(hexToRgb(inObj.getFill()));

        double leftX = (inObj.getXCord());
        double rightX = (inObj.getXCord() + inObj.getParams()[0]);

        double downY = (inObj.getYCord() + inObj.getParams()[1]);
        double upY = ((inObj.getYCord()));

        double triDownY = (inObj.getYCord() + inObj.getParams()[1]*.15);
        double triLeftX = (inObj.getXCord() + inObj.getParams()[0]*.85);

        addLinetoPath(leftX, leftX, upY, downY, path);
                //line2 bottom line
        addLinetoPath(leftX, rightX, downY, downY, path);
                //line3 rightmost line
        addLinetoPath(rightX, rightX, downY, triDownY, path);
                //line4 fold triangle base
        addLinetoPath(rightX, triLeftX, triDownY, triDownY, path);
                //line5 fold triangle diagonal
        addLinetoPath(rightX, triLeftX, triDownY, upY, path);
                //line6 fold triangle height
        addLinetoPath(triLeftX, triLeftX, triDownY, upY, path);
                //line7 top line
        addLinetoPath(triLeftX, leftX, upY, upY, path);

        return path;
    }


    public static Path parallelogramToJAVAFX(InputObject inObj){
        Path path = new Path();
        path.setFill(hexToRgb(inObj.getFill()));

        double upLeftX = (inObj.getXCord() + .15*inObj.getParams()[0]);
        double upLeftRightY = (inObj.getYCord());
        double upRightX = (1.15*inObj.getXCord() + inObj.getParams()[0]);
        double downLeftX = (inObj.getXCord());
        double downLeftRightY = (inObj.getYCord() - inObj.getParams()[1]);
        double downRightX = (inObj.getXCord() + .85*inObj.getParams()[0]);

        addLinetoPath(upLeftX, upRightX, upLeftRightY, upLeftRightY, path);
        addLinetoPath(upRightX, downRightX, upLeftRightY, downLeftRightY, path);
        addLinetoPath(downRightX, downLeftX, downLeftRightY, downLeftRightY, path);
        addLinetoPath(downLeftX, upLeftX, downLeftRightY, upLeftRightY, path);

        return path;
    }


    public static Path headTriangleToJavaFX(double lineX, double lineY, boolean pointIsVert, boolean isUpOrRight){
        Path path = new Path();

        double upLeftX;
        double upLeftY;
        double x2;
        double y2;
        double x3;
        double y3;

        double triSideLen = 8; //arbitrary length

        //is up
        if(pointIsVert && isUpOrRight){
            upLeftX = (lineX - triSideLen/2);
            upLeftY = (lineY);
            x2 = (lineX-triSideLen/2);
            y2 = (lineY-triSideLen);
            x3 = (lineX+triSideLen/2);
            y3 = y2;
        }
        //is down
        else if(pointIsVert){
            upLeftX = (lineX);
            upLeftY = (lineY + triSideLen/2);
            x2 = (lineX-triSideLen/2);
            y2 = (lineY+triSideLen);
            x3 = (lineX+triSideLen/2);
            y3 = y2;
        }
        //is right
        else if(isUpOrRight){
            x2 = (lineX - triSideLen);
            y2 = (lineY + triSideLen/2);
            x3 = x2;
            y3 = (lineY - triSideLen/2);
        }
        //is left
        else{
            x2 = (lineX + triSideLen);
            y2 = (lineY + triSideLen/2);
            x3 = x2;
            y3 = (lineY - triSideLen/2);
        }

        addLinetoPath((lineX), x2, (lineY), y2, path);
        addLinetoPath(x2, x3, y2, y3, path);
        addLinetoPath(x3, (lineX), y3, (lineY), path);

        return path;
    }


}
