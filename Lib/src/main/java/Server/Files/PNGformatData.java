package Server.Files;

import java.io.Serializable;

/**
 * PNGformat object.
 *
 * A simple data class for transmission of the png data from the FileExporter.
 * Contains the files name in String and its data in a byte[]
 *
 * @author Traae
 */
public class PNGformatData implements fileFormat, Serializable {
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
