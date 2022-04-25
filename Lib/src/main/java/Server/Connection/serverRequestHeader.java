package Server.Connection;


import groovyjarjarantlr4.v4.codegen.model.SrcOp;

/**
 * An enumeration for the values of our Http Request Headers
 *
 * These values are arbitrary, but their usage is not.
 * Using these strings we can avoid mismatches between client and server.
 *
 * @author Traae
 */
public class serverRequestHeader {
    public static final String name = "MardukServerCall";
    public static final String idOfObject = "inputObject/id";
    public static final String idOfBoard = "inputBoard/id";
    public static final String user = "user";
    public static final String inputObjectJson = "inputObject/json";
    public static final String inputBoardJson =  "inputBoard/json";


}
