package FactoryElements;

import UML.UMLObject;
import Wireframe.WireframeObject;

public class WireframeFactory implements AbstractElementFactory<WireframeObject>{
    public WireframeObject create(String l){
        WireframeObject i = new WireframeObject();
        return i;
    }
}
