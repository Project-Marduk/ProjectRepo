package Marduk.Javalin.Server.FileExporter;

import DrawingBoard.DrawingBoard;
import DrawingBoard.InputBoard;
import Files.mardukFileWriter;
import Files.PNGformatData;
import Files.SVGformatData;
import Server.ResponseManagement.RespondingClass;
import Server.ResponseManagement.ResponseManager;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.io.*;


/**
 * File Exporter Driver class.
 *
 * Handles the calls from the server to interact with the File Exporter Microservice.
 *
 * @author Steve
 * @version 0.1.0
 */
public class FileExporterDriver implements RespondingClass {
    private static FileExporterDriver instance = null;
    ResponseManager responseManager;

    private FileExporterDriver(){}
    public static FileExporterDriver getInstance() {
        if (instance == null){
            instance = new FileExporterDriver();
        }
        return instance;
    }
    @Override
    public void setResponseManager(ResponseManager r) {
        responseManager = r;
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
    public PNGformatData renderPNG(InputBoard toRender) {
        // Directories for our temporary files.
        String tempFileDir = "./tempfiles";
        String outputFile = tempFileDir + "/" + "out.png";

        // Our data to return to the client.
        PNGformatData renderedPNG = new PNGformatData();
        renderedPNG.setName(toRender.getName());

        // Take the input board and turn it into a svg file, to be saved temporarily.
        SVGformatData svg = renderSVG(toRender);
        String svgURI = mardukFileWriter.writeToFile(svg, tempFileDir);

        // Create a PNG transcoder
        PNGTranscoder t = new PNGTranscoder();
        // Create the transcoder input.
        TranscoderInput input = new TranscoderInput(svgURI);
        // Create the transcoder output.
        OutputStream ostream;

        try {
            ostream = new FileOutputStream(outputFile);
            TranscoderOutput output = new TranscoderOutput(ostream);

            t.transcode(input, output);
            ostream.close();

        } catch (IOException | TranscoderException e) {
            e.printStackTrace();
        }

        try {
            FileInputStream tempPNGInStream = new FileInputStream(outputFile);

            renderedPNG.setData(tempPNGInStream.readAllBytes());

            tempPNGInStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Clean up the temp files.
        File toDelete1 = new File(outputFile);
        toDelete1.delete();
        File toDelete2 = new File(svgURI);
        toDelete2.delete();

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
    public SVGformatData renderSVG(InputBoard toRender){
        SVGformatData renderedSVG = new SVGformatData();
        DrawingBoard rendering = new DrawingBoard(toRender);

        renderedSVG.setName(toRender.getName());
        renderedSVG.setData(rendering.returnSVGData());

        return renderedSVG;
    }
}
