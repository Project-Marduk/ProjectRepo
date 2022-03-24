package FactoryElements;

import Wireframe.WireframeObject;
import Wireframe.XButton;

public class WireframeFactory extends AbstractElementFactory<WireframeObject>{
    public WireframeObject create(InputObject input){
        WireframeObject i = new XButton("1", input);
        return i;
    }
}
