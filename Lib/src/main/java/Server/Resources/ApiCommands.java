package Server.Resources;

/**
 * General Api Paths
 *
 * An Enumeration of Api Paths
 * Corresponding to the command that they call from the server.
 *
 * Use: "ApiCommands.[enum].path()" in place of the string you need.
 *
 * All path string are formatted as:
 * /api /[REST Command] /[Target Service/module] /[method Intention] /[method target]
 *
 * Except for root(/) and up(/up), which are just connection checkers.
 *
 * @author Traae
 * @version 0.1.0
 */
public enum ApiCommands {
    root("/"),
    up("/up"),

    getStatus("/api/get/server/status"),
    getError("/api/get/server/error"),

    renderPNG("/api/post/fileExporter/render/png"),
    renderSVG("/api/post/fileExporter/render/svg"),

    registerUser("/api/post/dataManager/user/register"),
    loginUser("/api/post/dataManager/post/user/login"),
    logoutUser("/api/post/dataManager/user/logout/"),

    getUserData("/api/post/dataManager/user/files/list"),
    saveDrawingBoard("/api/post/save/Diagram"),
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
