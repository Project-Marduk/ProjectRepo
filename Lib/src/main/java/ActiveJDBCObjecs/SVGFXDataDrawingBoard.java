package ActiveJDBCObjecs;

public class SVGFXDataDrawingBoard {


    public static String returnSVGData(Integer boardId){
        String svgData = "";
        //query drawing object table for
//        for(String key : objects.keySet()){
//            svgData += "\n" + objects.get(key).getSVGData();
//            svgData += "\n" + objects.get(key).txtToSVG();
//        }

        return "<svg contentScriptType=\"text/ecmascript\" width=\"" + Double.toString(1000) + "px\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" baseProfile=\"full \"\n" +
                "    zoomAndPan=\"magnify\" contentStyleType=\"text/css\" height=\"" + Double.toString(1000) + "px\" preserveAspectRatio=\"xMidYMid meet\" xmlns=\"http://www.w3.org/2000/svg\"\n" +
                "    version=\"1.0\">"
                + svgData
                + "\n"
                + "</svg>";
    }

}
