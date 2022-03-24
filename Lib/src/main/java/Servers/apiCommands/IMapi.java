package Servers.apiCommands;

/**
 * Info Manager Api Paths
 *
 * An Enumeration of Api Paths
 * Corresponding to the command that they call from the server.
 *
 * Use: "IMapi.[enum].path()" in place of the string you need.
 *
 * @author Traae
 * @version 0.1.0
 */
public enum IMapi {
    registerUser("/api/post/register/user"),
    loginUser("/api/get/login/user"),
    getDiagram("/api/get/diagram"),
    getDiagramList("/api/get/diagram/list"),
    saveDiagram("/api/post/save/Diagram");

    private final String address;
    private IMapi(String s) {
        this.address = s;
    }
    public String path(){
        return address;
    };
}
