package Server.apiCommands;

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
public enum GeneralApi {
    root("/"),
    getStatus("/api/get/status"),
    getError("/api/get/error");

    private final String address;
    private GeneralApi(String s) {
        this.address = s;
    }
    public String path(){
        return address;
    };
}
