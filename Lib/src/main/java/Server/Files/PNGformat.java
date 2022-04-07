package Servers.Files;

import java.io.Serializable;

/**
 * PNGformat object.
 *
 * A simple data class for transmission of the png data from the FileExporter.
 *
 * @author Traae
 */
public class PNGformat implements fileFormat, Serializable {
    public String name;
    public byte[] data;


    @Override
    public String getName() {
        return name + ".png";
    }
    @Override
    public byte[] getData() {
        return data;
    }
}
