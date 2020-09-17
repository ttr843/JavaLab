
package program;

import context.ApplicationContext;
import context.ApplicationContextReflectionBased;
import server.ChatMultiServer;


public class ProgramServer {


    public static void main(String[] args) {
        int port = 6000;
        for (String param : args) {
            String name = param.split("=")[0];
            String arg = param.split("=")[1];
            if (name.equals("--port")) {
                port = Integer.parseInt(arg);
            }
        }
        ApplicationContext applicationContext = new ApplicationContextReflectionBased();
        ChatMultiServer server = applicationContext.getComponent(ChatMultiServer.class);
        server.start(port);
    }
}
