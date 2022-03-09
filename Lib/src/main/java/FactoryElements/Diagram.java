package FactoryElements;//This is our java class which will hold and handel all of our diagram elements

import FactoryElements.Lines.LineFactory;

/**
 * @author David Lindeman
 */
public class Diagram{
    //List<DiagramElement> elements;
    int width;
    int height;
    int elementTotal;

//    public static void main(args[]){
//        System.out.println("main");
//    }

    public void addElement(){

    }

    public static AbstractElementFactory getFactory(String choice){
        //assign correct factory based on input
        if("Line".equalsIgnoreCase(choice)){
            return new LineFactory();
        }
        else if("UML".equalsIgnoreCase(choice)){
            return new UMLFactory();
        }
        else if("Wireframe".equalsIgnoreCase(choice)){
            return new WireframeFactory();
        }
        return null;
    }

}
