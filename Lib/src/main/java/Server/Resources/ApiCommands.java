package Server.Resources;

/**
 * General Api Paths
 *
 * An Enumeration of Api Paths
 * Corresponding to the command that they call from the server.
 *
 * Use: "GeneralApi.[enum].path()" in place of the string you need.
 *
 * @author Traae
 * @version 0.1.0
 */
public enum ApiCommands {
    root("/"),
    up("/up"),

    getStatus("/get/server/status"),
    getError("/get/server/error"),

    renderPNG("/post/fileExporter/render/png"),
    renderSVG("/post/fileExporter/render/svg"),

    registerUser("/api/post/register/user"),
    loginUser("/api/post/login/user"),
    logoutUser("api/get/login/user"),

    getUserData("/api/post/diagram/list"),
    saveDiagram("/api/post/save/Diagram"),
    getDiagram("/api/get/diagram"),

    createDrawingObject("/api/post/create/drawingobject"),
    deleteDrawingObject("/api/post/delete/drawingobject"),
    updateDrawingObject("/api/post/update/drawingobject"),
    createDrawingBoard("/api/post/create/drawingboard"),
    deleteDrawingBoard("/api/post/delete/drawingboard"),

    validateDatabaseConnection("/api/post/validate/databaseconnection"),
    updateDrawingBoard("/api/post/update/drawingboard"),
    getSVGFileData("/api/get/svgfiledata"),

    getDrawingObjectData("api/get/drawingobjectdata");




    private final String address;
    private ApiCommands(String s) {
        this.address = s;
    }
    public String path(){
        return address;
    };
}
