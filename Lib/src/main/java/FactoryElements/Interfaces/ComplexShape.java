package FactoryElements.Interfaces;

/**
 * @author David Lindeman
 * All complex objects that require multiple objects will need to generate their SVG shape information
 * Therefore we will be utilizing this interface so all use the same method call name
 * Most generate shapes will utilize object data and shape functions to generate the shape(s) and text box(es)
 * Color will also be assigned
 */
public interface ComplexShape {
    public void generateShape();
}
