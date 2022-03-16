/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package IM.Javalin.Server;

import Servers.Resources.ServerPorts;
import Servers.apiCommands.GeneralApi;
import Servers.apiCommands.IMapi;
import io.javalin.Javalin;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

/**
 * The Info Manager Server
 *
 * @author Traae
 * @version 0.1.0
 */
public class IMServer {
    private static final int MAX_THREADS = 20;
    private static final int MIN_THREADS = 2;
    private static final int TIMEOUT = 60000;
    private static final int DEFAULT_PORT = 7002;



    public static void main(String[] args) {
        String defaultMessage ="This is the Info Manager Server";
        String apiInfo = "This api is for the Info Manager Microservice. \n" +
                "We register and login users, & save and load Diagrams\n" +
                "COMMAND LIST:";

        InfoManager infoManager = InfoManager.getInstance();

        
        QueuedThreadPool queuedThreadPool = new QueuedThreadPool(MAX_THREADS, MIN_THREADS,TIMEOUT);
        Javalin app = Javalin.create(config ->
                config.server(() ->
                        new Server(queuedThreadPool))).start(ServerPorts.InfoManager.port());

        app.routes(() -> {
            // Basic info calls
            app.get(GeneralApi.root.path(), ctx -> ctx.result(defaultMessage));
            app.get(GeneralApi.getInfo.path(), ctx -> ctx.result(apiInfo));

            // Check status and Error message
            app.get(GeneralApi.getStatus.path(), ctx -> ctx.result("0"/* CALL FileExporter . get current status code ()*/));
            app.get(GeneralApi.getError.path(), ctx -> ctx.result("All good"/* CALL FileExporter . get error message ()*/));




            app.post(IMapi.registerUser.path(), ctx -> {
                // [Database User Register and Save Function](ctx.body());
                // ctx.result("Register Successful");
            });
            app.get(IMapi.loginUser.path(), ctx -> {
                // toLogin = [Database User Load](ctx.body());
                // InfoManager UserLogin( toLogin );
                // ctx.result("Login Succefull");
            });
            app.get(IMapi.getDiagram.path(), ctx -> {
                //String diagramName = ctx.body();
                // Diagram toLoad = [Database load Diagram](diagramName);
                // ctx.json(toLoad);
            });
            app.get(IMapi.getDiagramList.path(), ctx -> {
                // do we want to json a list of lists?
            });
            // Receive a diagram for save.
            app.post(IMapi.saveDiagram.path(), ctx -> {
                /*
                NOTE: I still do not full understand this conditional,
                specificly "application/json"

                if (Objects.equals(ctx.contentType(), "application/json")) {
                    // we make a diagram out of the diagram json that was sent to us.
                    // Diagram diagram = ctx.bodyAsClass(Diagram.class);

                    // [Database Save Diagram Function](diagram);

                    ctx.result("Save Successful");
                }*/
            });


        });

    }
}
