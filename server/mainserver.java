package server;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class mainserver {
    static ExecutorService executeIt = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(3128, 0, InetAddress.getByName("127.0.0.1"));
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)))

        {
            System.out.println("Server socket created, command console reader for listen to server commands");

            while (!server.isClosed()) {

                if (buffer.ready()) {
                    System.out.println("Main Server found any messages in channel.");
                    String serverCommand = buffer.readLine();
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        System.out.println("Server exiting...");
                        server.close();
                        break;
                    }
                }

                Socket client = server.accept();

                executeIt.execute(new dbconnector(client));
                System.out.print("Connection accepted.");
            }

            executeIt.shutdown();
        } catch(Exception e)
        {System.out.println("init error: "+e);} // вывод исключений
    }
}
