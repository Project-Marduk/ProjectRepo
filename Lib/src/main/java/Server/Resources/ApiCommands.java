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
 * /api /[REST Command] /[Target Service/module] /[method Category] /[method Subcategory]
 *
 * Except for root(/) and up(/up), which are just connection checkers.
 *
 * @author Traae
 * @version 0.1.0
 */
public enum ApiCommands {
    // Server Basics
    root("/"),
    up("/up"),
    // Server Responses TODO finish this system;
    getResponseCode("/api/get/server/response/code"),
    getResponseMessage("/api/gets/server/response/message"),
    getResponseBoolean("/api/get/server/response/boolean"),
    // File Exporter
    renderPNG("/api/post/fileExporter/render/png"),
    renderSVG("/api/post/fileExporter/render/svg"),

    // Data Manager
    // - User commands
    registerUser("/api/post/dataManager/user/register"),
    loginUser("/api/post/dataManager/post/user/login"),
    logoutUser("/api/post/dataManager/user/logout/"),
    getUserData("/api/post/dataManager/user/files/"),
    // - Drawing Board Commands
    saveDrawingBoard("/api/post/dataManager/DrawingBoard/save"),
    getDrawingBoard("/api/post/dataManager/DrawingBoard/load"),
    // - Drawing Object Commands
    createDrawingObject("/api/post/dataManager/create/drawingObject"),
    deleteDrawingObject("/api/post/dataManager/delete/drawingObject"),
    updateDrawingObject("/api/post/dataManager/update/drawingObject"),
    createDrawingBoard("/api/post/dataManager/create/drawingBoard"),
    deleteDrawingBoard("/api/post/delete/drawingBoard"),
    // - Database Commands
    validateDatabaseConnection("/api/get/DataManager/databaseconnection/validate");





    private final String address;
    private ApiCommands(String s) {
        this.address = s;
    }
    public String path(){
        return address;
    };
}
