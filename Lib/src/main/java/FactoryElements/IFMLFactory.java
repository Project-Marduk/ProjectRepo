package FactoryElements;

import IFML.IFMLObject;
import IFML.Label.IFMLLabel;
import IFML.Label.IFMLLabelAction;
import IFML.Node.IFMLMail;
import IFML.Node.IFMLNode;

public class IFMLFactory implements AbstractElementFactory<IFMLObject>{
    public IFMLObject create(String l){
        if(l.equals("Node")){
            return new IFMLMail("1");
        }
        else return new IFMLLabelAction("1", 3);
    }
}
