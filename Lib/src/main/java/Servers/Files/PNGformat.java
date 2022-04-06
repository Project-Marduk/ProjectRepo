package Servers.FileFormats;

import java.io.Serializable;

public class PNGformat implements Serializable {
    public String name;
    public byte[] data;
    public String fileExtension;

    public PNGformat(){
        fileExtension = ".png";
    }

}
