package Marduk.Javalin.Server.FileExporter;

import Server.Files.PNGformatData;
import Server.Files.SVGformatData;

/**
 * File Exporter Driver class.
 *
 * Handles the calls from the server to interact with the File Exporter Microservice.
 *
 * @author Steve
 * @version 0.1.0
 */
public class FileExporterDriver {
    private static FileExporterDriver instance = null;

    private FileExporterDriver(){}
    public static FileExporterDriver getInstance() {
        if (instance == null){
            instance = new FileExporterDriver();
        }
        return instance;
    }

    /**
     * Render PNG
     *
     * Takes our data structure and compiles it into a png
     * returns it ina format the client is responsible for writing to disk.
     *
     * @param toRender the Diagram to be rendered.
     * @return PNGformatData object containing the name and rendered byte[]
     */
    public PNGformatData renderPNG(/*DATASTRUCTUE toRender*/ Object toRender){
        PNGformatData renderedPNG = new PNGformatData();

        // STEVE this is all you, my man.

        return renderedPNG;
    }

    /**
     * Render SVG
     *
     * Takes our data structure and compiles it into a png
     * returns it ina format the client is responsible for writing to disk.
     *
     * @param toRender the Diagram to be rendered.
     * @return SVGformatData object containing the name and rendered byte[]
     */
    public SVGformatData renderSVG(/*DATASTRUCTUE toRender*/ Object toRender){
        SVGformatData renderedSVG = new SVGformatData();

        // Hear as well.

        return renderedSVG;
    }
}
