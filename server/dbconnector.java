package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbconnector implements Runnable {
    private static final String url = "jdbc:mysql://26.230.233.234:3306/tp_database";
    private static final String username = "tpdb";
    private static final String password = "421-1Best";
    public static Connection conn;
    public static Statement statement;

    private static Socket clientDialog;

    public dbconnector(Socket client) {
        dbconnector.clientDialog = client;
    }

    @Override
    public void run() {

        try {

            DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            System.out.println("DataInputStream created\nDataOutputStream created");
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();
            System.out.println("Connected to DB");

            while (!clientDialog.isClosed()) {
                System.out.println("Server reading from channel");
                String entry = in.readUTF();

                System.out.println("READ from clientDialog message - " + entry);

                if (entry.equalsIgnoreCase("quit")) {

                    System.out.println("Client initialize connections suicide ...");
                    out.writeUTF("Server reply - " + entry + " - OK");
                    Thread.sleep(3000);
                    break;
                }
                
                
                
                
                //Example code
                if (entry.equalsIgnoreCase("Search")){
                    String SQLcommand = "";
                    //statement.executeUpdate(SQLcommand);
                }

                
                
                
                System.out.println("Server try writing to channel");
                out.writeUTF("Server reply - " + entry + " - OK");
                System.out.println("Server Wrote message to clientDialog.");

                out.flush();

            }

            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            in.close();
            out.close();

            clientDialog.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException trowables){
            trowables.printStackTrace();
            throw new RuntimeException();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}