package FactoryElements;

import Wireframe.WireframeObject;

public class WireframeFactory extends AbstractElementFactory<WireframeObject>{
    public WireframeObject create(InputObject input){
        WireframeObject i = new WireframeObject();
        return i;
    }
}
