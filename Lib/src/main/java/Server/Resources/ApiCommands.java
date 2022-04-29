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

public class ApiCommands {
    // Server Basics
    public static final String root = "/";
    public static final String up = "/up";

    // Route Branches
    public static final String fileExporter = "/fileExporter";
    public static final String dataManager = "/DataManager";
    public static final String server = "/server";

    // Server Responses
    public static final String getResponseCode = server + "/api/get/response/code";
    public static final String getResponseMessage = server + "/api/gets/response/message";
    public static final String getResponseBoolean = server + "/api/get/response/boolean";
    // File Exporter
    public static final String renderPNG = fileExporter + "/api/post/render/png";
    public static final String renderSVG = fileExporter + "/fileExporter/api/post/render/svg";

    // Data Manager
    // - User commands
    public static final String registerUser = dataManager  + "/api/post/user/register";
    public static final String loginUser = dataManager  + "/api/post/post/user/login";
    public static final String logoutUser = dataManager  + "/api/post/user/logout";
    public static final String UserFolderIDs = dataManager  + "/api/post/user/files";
    public static final String createFolder = dataManager  + "/api/post/folder/create";
    public static final String assignFolder = dataManager  + "/api/post/folder/assign";
    public static final String removeFolderUser = dataManager  + "/api/post/folder/remove";
    public static final String folderBoardIDs = dataManager  + "/api/post/user/files";

    // - Drawing Board Commands
    public static final String saveDrawingBoard=dataManager  + "/api/post/dataManager/DrawingBoard/save";
    public static final String  getDrawingBoard=dataManager  + "/api/post/dataManager/DrawingBoard/load";
    // - Drawing Object Commands
    public static final String createDrawingObject=dataManager  + "/api/post/create/drawingObject";
    public static final String deleteDrawingObject=dataManager  + "/api/post/delete/drawingObject";
    public static final String updateDrawingObject=dataManager  + "/api/post/update/drawingObject";
    public static final String getDrawingObject=dataManager  + "/api/post/get/drawingObject";
    public static final String createDrawingBoard=dataManager  + "/api/post/create/drawingBoard";
    public static final String deleteDrawingBoard=dataManager  + "/api/post/delete/drawingBoard";
    // - Database Commands
    public static final String validateDatabaseConnection = dataManager  + "/api/get/DBconnection/validate";


}
