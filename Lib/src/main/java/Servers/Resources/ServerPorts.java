package Servers.Resources;
/**
 * The port numbers for the microservices.
 *
 * An Enumeration of the ports numbers of the servers
 *
 * Use: "ServerPorts.[enum].port()" in place of the integer you need.
 *
 * @author Traae
 * @version 0.1.0
 */
public enum ServerPorts {
    DiagramComposer(7001),
    FileExporter(7002),
    InfoManager(7003);

        private final int portToUse;
        private ServerPorts(int i) {
            this.portToUse = i;
        }
        public int port(){
            return portToUse;
        }
}

