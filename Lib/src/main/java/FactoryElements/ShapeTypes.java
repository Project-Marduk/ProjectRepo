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
    parallelogram("Parallelogram");

    private final String value;
    private ShapeTypes(String v) {this.value = v;}

    /**
     * @return the string value associated with the enum.
     */
    public String getValue(){
        return value;
    }

    /**
     *
     * @param value the string of the enum input using
     * @return returns corresponding ShapeType, or null if no match.
     */
    public static ShapeTypes enumOfString(String value){
        ShapeTypes t = null;
        for (ShapeTypes s : ShapeTypes.values()){
            if (value == s.value){
                t = s;
            }
        }
        return t;
    }


}
