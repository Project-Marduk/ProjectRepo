package Servers.Connections;

import Servers.Files.PNGformat;
import Servers.Files.SVGformat;
import Servers.Resources.ServerMessages;
import Servers.apiCommands.FEapi;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


/**
 * The File Exporter connection class.
 *
 * Contains methods that interface with the File Exporter Server's Api.
 *
 * TODO currently the 2 methods are commented out.
 * TODO Un-comment them after we finalize the Diagram class. (highlight ctrl + /)
 * TODO update them to work properly with the Diagram,
 * TODO or which ever class represents the top of the data structure.
 *
 * @author Traae
 * @version 0.9.0
 */
public class FEconnection extends Connection {
    private static final String PNG_CALL = httpSuffix + FEapi.renderPNG.path();
    private static final String SVG_CALL = httpSuffix + FEapi.renderSVG.path();

    Gson gson;

    private FEconnection(){
        super();
        expectedMessage = ServerMessages.FEmessage.getMessage();
        gson = new Gson();
    }
    @Override
    public Connection instance() {
        if (INSTANCE == null){
            INSTANCE = new FEconnection();
        }
        return INSTANCE;
    }

//    /**
//     * Render Png
//     *
//     * Send a Diagram Json to the File Exporter and get a
//     *
//     *
//     * @param diagram The diagram to be rendered.
//     * @return a PNGformat object that can be written to a file.
//     */
//    public PNGformat renderPNG(DIAGRAM diagram) {
//        String input = gson.toJson(diagram);
//        HttpRequest request = createPost(PNG_CALL, input);
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            return gson.fromJson(response.body(), PNGformat.class);
//        } catch (IOException | InterruptedException ex) {
//            System.out.println("Error caught in FEconnection.renderPNG(): " + ex.getMessage());
//        }
//        return null;
//    }
//
//    /**
//     * render SVG
//     *
//     * @param diagram the diagram to be compiled into an svg file
//     * @return SVGformat object for writeing to file.
//     */
//    public SVGformat renderSVG(DIAGRAM diagram) {
//        String input = gson.toJson(diagram);
//        HttpRequest request = createPost(SVG_CALL, input);
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            return gson.fromJson(response.body(), SVGformat.class);
//        } catch (IOException | InterruptedException ex) {
//            System.out.println("Error caught in FEconnection.renderSVG(): " + ex.getMessage());
//            return null;
//        }
//    }

}
