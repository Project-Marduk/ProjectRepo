package Server.Resources;


/**
 * An enumeration for the values of our Http Request Headers
 *
 * These values are arbitrary, but their usage is not.
 * Using these strings we can avoid mismatches between client and server.
 *
 * @author Traae
 */
public enum HttpRequestHeader {
    name("MardukServerCall"),
    value("application/json");

    private final String s;
    private HttpRequestHeader(String s) {
        this.s = s;
    }
    public String get(){
        return this.s;
    };


}
