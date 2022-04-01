package WebClient;

import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.VueComponent;

public class App11 {
    public static void main(String[] args) {

        Javalin app = Javalin.create(
             javalinConfig -> javalinConfig.enableWebjars()


        ).start(7000);


        app.get("/", new VueComponent("hello-world"));



    }
}






