import client.ClientInterface;
import client.ClientWindow;
import server.Server;
import server.ServerInterface;
import server.ServerWindow;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        ServerInterface serverWindow = new ServerWindow(server);
        ClientInterface clientWindow1 = new ClientWindow(server);
        ClientInterface clientWindow2 = new ClientWindow(server);
    }
}