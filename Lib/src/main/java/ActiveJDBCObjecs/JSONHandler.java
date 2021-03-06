package ActiveJDBCObjecs;

import FactoryElements.InputObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JSONHandler {
//
//    /**
//     * @author David Lindeman
//     * @param jsonStr
//     * @return
//     * Takes a JSON string and converts it into a drawing board object
//     */
//    public static InputObject inputObjectFromJSON(String jsonStr){
//        try {
//            //create Gson instance
//            Gson gson = new Gson();
//
////            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
////            System.out.println(jsonStr);
//            //set type for input object
//            Type inObjType = new TypeToken<InputObject>(){}.getType();
//
//            //convert JSON string to input obj
//            InputObject inObj = gson.fromJson(jsonStr, inObjType.);
//
//            return inObj;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    public static DrawingBoardAJDBC drawingBoardFromJSON(String jsonStr){
//        try {
//            //create Gson instance
//            Gson gson = new Gson();
//
////            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
////            System.out.println(jsonStr);
//            //set type for input object
//            Type objType = new TypeToken<DrawingBoardAJDBC>(){}.getType();
//
//            //convert JSON string to input obj
//            DrawingBoardAJDBC obj = gson.fromJson(jsonStr, objType);
//
//            return obj;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * @author David Lindeman
//     * @param inList
//     * @return
//     * takes in an array list of strings and returns it as a JSON string
//     */
//    public static String arrayListToJSON(ArrayList<String> inList){
//        Gson gson = new Gson();
//        return gson.toJson(inList);
//    }
//
//    /**
//     * This should get refactored using generics
//     * @param jsonStr
//     * @return
//     */
//    public static ArrayList<String> arrayListFromJSON(String jsonStr){
//        try {
//            //create Gson instance
//            Gson gson = new Gson();
//            //set type for input object
//            Type objType = new TypeToken<ArrayList<String>>(){}.getType();
//
//            //convert JSON string to input obj
//            ArrayList<String> obj = gson.fromJson(jsonStr, objType);
//
//            return obj;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
}
