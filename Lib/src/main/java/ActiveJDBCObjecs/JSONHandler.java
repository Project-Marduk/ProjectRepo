package ActiveJDBCObjecs;

import FactoryElements.InputObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class JSONHandler {

    /**
     * @author David Lindeman
     * @param jsonStr
     * @return
     * Takes a JSON string and converts it into a drawing board object
     */
    public static InputObject inputObjectFromJSON(String jsonStr){
        try {
            //create Gson instance
            Gson gson = new Gson();

            //set type for scoreboard
            Type inObjType = new TypeToken<InputObject>(){}.getType();

            //convert JSON string to scoreboard obj
            InputObject inObj = gson.fromJson(jsonStr, inObjType);

            return inObj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
