import java.io.*;
import java.net.*;
public class SimpleClient {
    private String host;
    private int port;

    public SimpleClient(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    public void sendMessage(String message)
    {
        try(Socket socket = new Socket(this.host, this.port))
        {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println(message);
            String response = in.readLine();
            System.out.println("Otrzymano od serwera: " + response);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
