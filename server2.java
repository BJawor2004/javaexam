import java.io.*;
import java.net.*;
public class SimpleServer {
    private int port;

    public SimpleServer(int port)
    {
        this.port = port;
    }

    public void start()
    {
        try(ServerSocket serverSocket = new ServerSocket(this.port))
        {
            System.out.println("Serwer nasłuchuje na porcie: " + this.port);

            while(true)
            {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Otrzymano od klienta: " + clientSocket.getInetAddress());

                new Thread(()-> handleClient(clientSocket)).start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    private void handleClient(Socket clientSocket)
    {
        try(
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
                )
        {
            String message = in.readLine();
            System.out.println("Otrzymano od klienta: " + message);
            out.println("Wiadomość odebrana: " + message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            try
            {
                clientSocket.close();
            }
            catch (IOException ignored){};
        }
    }
}
