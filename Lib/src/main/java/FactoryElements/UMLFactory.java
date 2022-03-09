package FactoryElements;

import BasicShapes.PlainLine;
import FactoryElements.Lines.Line;
import UML.UMLObject;

public class UMLFactory implements AbstractElementFactory<UMLObject>{
    public UMLObject create(String l){
        UMLObject i = new UMLObject();
        return i;
    }
}
