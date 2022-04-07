package Servers.apiCommands;
/**
 * File Exports Api Paths
 *
 * An Enumeration of Api Paths
 * Corresponding to the command that they call from the server.
 *
 * Use: "FEapi.[enum].path()" in place of the string you need.
 *
 * @author Traae
 * @version 0.1.0
 */
public enum FEapi {
    renderPNG("/api/post/render/png"),
    renderSVG("/api/post/render/svg");

    private final String address;
    private FEapi(String s) {
        this.address = s;
    }
    public String path(){
        return address;
    };
}
