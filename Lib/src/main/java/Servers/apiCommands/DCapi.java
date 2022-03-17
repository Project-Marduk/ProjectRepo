package Servers.apiCommands;

/**
 * Diagram Composer Api Paths
 *
 * An Enumeration of Api Paths
 * Corresponding to the command that they call from the server.
 *
 * Use: "DCapi.[enum].path()" in place of the string you need.
 *
 * @author Traae
 * @version 0.1.0
 */
public enum DCapi {
    root("/");

    private final String address;
    private DCapi(String s) {
        this.address = s;
    }
    public String path(){
        return address;
    };
}
