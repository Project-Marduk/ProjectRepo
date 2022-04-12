package Server.Resources;
/**
 * The port numbers for the microservices.
 *
 * An Enumeration of the ports numbers of the servers,
 * so that both servers and clients avoid mistyping a port number.
 *
 * Use: "ServerPorts.[enum].port()" in place of the integer you need for the port.
 *
 * @author Traae
 * @version 1.0.0
 */
public enum ServerPorts {
    WebClient(7000),
    Server(7001);

        private final int portToUse;
        private ServerPorts(int i) {
            this.portToUse = i;
        }
        public int port(){
            return portToUse;
        }
}

