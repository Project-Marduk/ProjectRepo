package IFML;

import FactoryElements.ComplexShape;

public abstract class IFMLObject implements ComplexShape {
    public String id;
    public String svgData;

    public IFMLObject(String newId){
        id = newId;
        svgData = "";
    }

    public String getSVGData(){
        return svgData;
    }

//    public String generateShape(){
//        return "";
//    }
}
