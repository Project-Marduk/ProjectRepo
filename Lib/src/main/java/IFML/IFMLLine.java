package IFML;

import FactoryElements.Lines.AbstractLine;
import FactoryElements.Lines.HeadType;
import IFML.IFMLObject;
import lombok.Getter;
import lombok.Setter;

/**
 * @author David Lindeman
 * Line for UML diagrams
 */
@Getter @Setter
public class IFMLLine extends AbstractLine {
    HeadType lHeadType;
    HeadType rHeadType;

    public IFMLLine(int lX, int lY, int rX, int rY, String lineType) {
        super(lX, lY, rX, rY, lineType);
    }

    public void drawLine(){
        System.out.println("draw");
    }
}