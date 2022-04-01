import io.javalin.Javalin;
import io.javalin.plugin.rendering.vue.JavalinVue;
import io.javalin.plugin.rendering.vue.VueComponent;
import io.javalin.core.security.RouteRole;
import io.javalin.http.Context;


public class App {

    public enum Role implements RouteRole {
        ANYONE,
        LOGGED_IN;
    }

    Role getUserRole(Context ctx) {
        // determine user role based on request.
        // typically done by inspecting headers, cookies, or user session

        return null;
    }


    public static void main(String[] args) {



        Javalin app = Javalin.create(
                javalinConfig ->{

                    javalinConfig.enableWebjars();
                    javalinConfig.accessManager((handler, ctx, routeRoles) -> {

                        if (routeRoles.contains(Role.ANYONE) || routeRoles.contains(Role.LOGGED_IN)) {
                            handler.handle(ctx);
                        } else {
                            ctx.status(401).result("Unauthorized");
                        }
                    });

                }



        ).start(7002);



        app.get("/", new VueComponent("hello-world"), Role.ANYONE);
        app.get("/login", new VueComponent("login"), Role.ANYONE);
        app.get("/main/{userid}", new VueComponent("user"), Role.LOGGED_IN);
        app.error(404, "html", new VueComponent("not-found"));







    }




}




