package IFML.Node;

/**
 * @author David Lindeman
 * IFMLMail is a shape of one box that contains a text header and then contains two boxes also with text headers and text bodies
 */
public class IFMLMail extends IFMLNode{

    public IFMLMail(String id){
        super(id);
    }

    //The actual generation of each shape will need to be handled in the concrete implementation of the class
    public void generateShape(){

    }

}
