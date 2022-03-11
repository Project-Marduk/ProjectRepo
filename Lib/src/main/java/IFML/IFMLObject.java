package IFML;

public abstract class IFMLObject {
    public String id;
    public String svgData;

    public IFMLObject(String newId){
        id = newId;
        svgData = "";
    }

    public String getSVGData(){
        return svgData;
    };
}
