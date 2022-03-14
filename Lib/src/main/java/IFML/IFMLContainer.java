package IFML;

import FactoryElements.InputObject;
import FactoryElements.ShapeFactory;
import lombok.Getter;
import lombok.Setter;

/**
 * @author David Lindeman
 * The IFML Container object can have 4 different headers as per below
 * View, XOR, Landmark, Default
 * To cut down on the size of this file we will be storing the data that will be presented in the factory object
 * Takes in a rectangle for input object type
 */
@Getter @Setter
public class IFMLContainer extends IFMLObject {
    String containerHeader;
    String text;


    public IFMLContainer(String cHeader, String defaultText, String id, InputObject inObj){
        super(id, inObj);
        containerHeader = cHeader;
        text = defaultText; //text starts out as the value to the key of the map in IMFLFactory's variable "containerHeaders"
    }

    public void generateShape(){
        super.txtToSVG(containerHeader + " " + text);

        ShapeFactory sF = new ShapeFactory();

        //The text box's size is a subset of the larger box
        String txtBox = sF.create(new InputObject("Rectangle",
                new double[]{super.inObject.getParams()[0]*.15, super.inObject.getParams()[1]},
                super.inObject.getColor(),
                super.inObject.getStyle())).getSVGData();
        String containerBox = sF.create(super.inObject).getSVGData();
        String svgText = txtToSVG(text);

        //this could be refactored for the second text box to just be a line 15% down the top of the input rectangle however this doesnt work because our lines have set x,y cords
        super.setSvgData(containerBox + txtBox + svgText); //the shape SVG should be the combination of the two boxes, this may need to be changed depending on how we have to format the text
        //add text to the shapeSVG

        System.out.println("IFMLContainer generateShape");
    }
}
