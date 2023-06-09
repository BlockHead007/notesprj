package server;

import java.io.*;
import java.net.*;
import java.util.ArrayDeque;
import java.util.concurrent.*;
import server.Dbcommands;
import server.Dbconnector;

/*class dbconnect{
    public Statement statement;
}*/



public class Mainserver {
    static ExecutorService executeIt = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {
        Dbconnector dbconnector = new Dbconnector();
        dbconnector.run();
        try (ServerSocket server = new ServerSocket(3128, 0, InetAddress.getByName("127.0.0.1")))
            {
            System.out.println("Server socket created, command console reader for listen to server commands");
            int state;
            boolean bstate;
            Socket client = server.accept();
            System.out.println("Client connected.");
            ObjectInputStream objectInput = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream objectOutput = new ObjectOutputStream(client.getOutputStream());
                ArrayDeque<String> list = new ArrayDeque<String>();
            while (!server.isClosed()) {
                //System.out.println("Main Server found any messages in channel.");
                Object receivedObject = objectInput.readObject();
                String getmsg = receivedObject.toString();
                System.out.println("Received message: " + getmsg);
                String[] splitted = getmsg.split("~");
                String serverCommand = splitted[0];//getmsg.substring(0, 4);
                if (serverCommand.equalsIgnoreCase("quit")) {
                    System.out.println("Server exiting...");
                    server.close();
                    break;
                }
                switch (serverCommand){
                    case "regs": // account registration
                        state = Dbcommands.Registration(dbconnector.statement, splitted[1], splitted[2], splitted[3]);
                        objectOutput.writeObject(state);
                        break;
                    case "gets": // account login and get session
                        //String dbmsgsend = splitted[1] + ":" + splitted[2];
                        bstate = Dbcommands.Login(dbconnector.statement, splitted[1], splitted[2]);
                        objectOutput.writeObject(bstate);
                        break;
                    case "getd": // get data
                        state = Dbcommands.Search(dbconnector.statement, Integer.parseInt(splitted[1]), list);
                        objectOutput.writeObject(state);
                        objectOutput.writeObject(list);
                        list.clear();
                        break;
                    case "chan": // change account name
                        System.out.println("Client issued command chan\nParameters are: " + splitted[1] + ", " + splitted[2]);
                        state = Dbcommands.UsernameUpdate(dbconnector.statement, Integer.parseInt(splitted[1]), splitted[2]);
                        System.out.println("Base answer: " + state);
                        objectOutput.writeObject(state);
                        break;
                    case "chpd": // change account password
                        state = Dbcommands.UserpassUpdate(dbconnector.statement, Integer.parseInt(splitted[1]), splitted[2]);
                        objectOutput.writeObject(state);
                        break;
                    case "crnt": // create note
                        state = Dbcommands.Upload(dbconnector.statement, Integer.parseInt(splitted[1]), splitted[2], splitted[3]);
                        objectOutput.writeObject(state);
                        break;
                    case "svnt":
                        state = Dbcommands.UpdateNote(dbconnector.statement, Integer.parseInt(splitted[1]), splitted[2]);
                        objectOutput.writeObject(state);
                        break;
                    case "dlnt": // delete note
                        state = Dbcommands.Delete(dbconnector.statement, Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]));
                        objectOutput.writeObject(state);
                        break;
                }

            }


                //executeIt.execute(new Dbconnector());
                //System.out.print("Connection accepted.");
            } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        executeIt.shutdown();
        }
    }
