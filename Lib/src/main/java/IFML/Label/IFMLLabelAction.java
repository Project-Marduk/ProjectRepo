package IFML.Label;

import BasicShapes.Hexagon;

public class IFMLLabelAction extends IFMLLabel {
    public IFMLLabelAction(String id, int sLen){
        super(id, "Enter Text");
        generateShape(sLen);
    }

    private void generateShape(int sLen){
        svgData = new Hexagon(sLen).getSVGData();
        //display text information

        //assign color?
    }

    public void editText(String inputText){
        text = inputText;
    }

}
