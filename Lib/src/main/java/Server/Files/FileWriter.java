package Server.Files;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A File Writer
 *
 * A very simple file writer static method to use our fileFormat interface.
 *
 * TODO Make this more robust.
 * TODO handle the directory string in a more secure fashion.
 *
 * @author Traae
 * @version 0.1.0
 */
public class FileWriter {
    public static boolean isValidDirectory(String directory){
       return Files.isDirectory(Paths.get(directory));
    }

    public static void writeToFile(fileFormat toWrite, String directory){
        try {
            Files.write(
                    Paths.get(directory + "/" +  toWrite.getName()),
                    toWrite.getData());
        }catch (Exception e){
            System.out.println("Exception caught trying to write file: \n" +
                    e.getMessage() + e.getStackTrace());
        }

    }
}
