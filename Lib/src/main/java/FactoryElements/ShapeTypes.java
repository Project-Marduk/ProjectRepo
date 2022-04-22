package FactoryElements;

public enum ShapeTypes {
    IFML_Action("IFML_Action"),
    IFML_Activation_Expression("IFML_Activation_Expression"),
    IFML_Container("IFML_Container"),
    IFML_Event("IFML_Event"),
    IFML_Module("IFML_Module"),
    IFML_Parameter("IFML_Parameter"),
    IFML_View_Component("IFML_View_Component"),
    IFML_View_Component_Part("IFML_View_Component_Part"),
    IFML_Line("IFML_Line"),
    Line("Line"),
    Wireframe_Object("Wireframe_Object"),
    rectangle("Rectangle"),
    square("Square"),
    circle("Circle"),
    hexagon("Hexagon"),
    parrallelogram("Parallelogram");

    private final String value;
    private ShapeTypes(String v) {this.value = v;}

    /**
     * @return the string value associated with the enum.
     */
    public String string(){
        return value;
    }
}
