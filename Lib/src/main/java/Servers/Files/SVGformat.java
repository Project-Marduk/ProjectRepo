package Servers.FileFormats;

import java.io.Serializable;

public class SVGformat implements Serializable {
    public String name;
    public String data;
    public String fileExtension;

    public SVGformat(){
        fileExtension = ".svg";
    }
}
