package server;
import java.io.*;
import java.net.*;
import java.util.ArrayDeque;
import server.Dbcommands;
import server.Dbconnector;

public class Mainserver {

    public static void main(String[] args) {
        Dbconnector dbconnector = new Dbconnector();
        dbconnector.run();
        try (ServerSocket server = new ServerSocket(3128, 0, InetAddress.getByName("192.168.0.136")))
            {
            System.out.println("Server socket created! Waiting for connections\n");
            int state;
            Socket client = server.accept();
            System.out.println("Client connected\n");
            ObjectInputStream objectInput = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream objectOutput = new ObjectOutputStream(client.getOutputStream());
                ArrayDeque<String> list = new ArrayDeque<String>();
            while (!server.isClosed()) {
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
                        System.out.println("Recognized command: regs\nRecognized username: " + splitted[1] + "\nRecognized password: " + splitted[2] + "\nRecognized Email: " + splitted[3]);
                        state = Dbcommands.Registration(dbconnector.statement, splitted[1], splitted[2], splitted[3]);
                        System.out.println("Received state: " + state);
                        objectOutput.writeObject(state);
                        System.out.println();
                        break;
                    case "logs": // account login
                        System.out.println("Recognized command: gets\nRecognized username: " + splitted[1] + "\nRecognized password: " + splitted[2]);
                        state = Dbcommands.Login(dbconnector.statement, splitted[1], splitted[2]);
                        System.out.println("Received state: " + state);
                        objectOutput.writeObject(state);
                        System.out.println();
                        break;
                    case "getd": // get data
                        list.clear();
                        System.out.println("Recognized command: getd\nRecognized userid: " + Integer.parseInt(splitted[1]));
                        state = Dbcommands.Search(dbconnector.statement, Integer.parseInt(splitted[1]), list);
                        System.out.println("Received state: " + state);
                        objectOutput.writeObject(state);
                        objectOutput.reset();
                        objectOutput.writeObject(list);
                        list.clear();
                        System.out.println();
                        break;
                    case "chan": // change account name
                        System.out.println("Recognized command: chan\nRecognized userid: " + Integer.parseInt(splitted[1]) + "\nRecognized new name: " + splitted[2]);
                        state = Dbcommands.UsernameUpdate(dbconnector.statement, Integer.parseInt(splitted[1]), splitted[2]);
                        System.out.println("Received state: " + state);
                        objectOutput.writeObject(state);
                        System.out.println();
                        break;
                    case "chpd": // change account password
                        System.out.println("Recognized command: chpd\nRecognized userid: " + Integer.parseInt(splitted[1]) + "\nRecognized new pass: " + splitted[2]);
                        state = Dbcommands.UserpassUpdate(dbconnector.statement, Integer.parseInt(splitted[1]), splitted[2]);
                        System.out.println("Received state: " + state);
                        objectOutput.writeObject(state);
                        System.out.println();
                        break;
                    case "crnt": // create note
                        System.out.println("Recognized command: nmnt\nRecognized noteid: " + Integer.parseInt(splitted[1]) + "\nRecognized note name: " + splitted[2]);
                        state = Dbcommands.Upload(dbconnector.statement, Integer.parseInt(splitted[1]), splitted[2], splitted[3]);
                        System.out.println("Received state: " + state);
                        objectOutput.writeObject(state);
                        System.out.println();
                        break;
                    case "svnt": // save note
                        System.out.println("Recognized command: svnt\nRecognized noteid: " + Integer.parseInt(splitted[1]));
                        state = Dbcommands.UpdateNote(dbconnector.statement, Integer.parseInt(splitted[1]), splitted[2]);
                        System.out.println("Received state: " + state);
                        objectOutput.writeObject(state);
                        System.out.println();
                        break;
                    case "nmnt": // note name update
                        System.out.println("Recognized command: nmnt\nRecognized noteid: " + Integer.parseInt(splitted[1]) + "\nRecognized note name: " + splitted[2]);
                        state = Dbcommands.UpdateNoteName(dbconnector.statement, Integer.parseInt(splitted[1]), splitted[2]);
                        System.out.println("Received state: " + state);
                        objectOutput.writeObject(state);
                        System.out.println();
                        break;
                    case "dlnt": // delete note
                        System.out.println("Recognized command: dlnt\nRecognized noteid: " + Integer.parseInt(splitted[1]));
                        state = Dbcommands.Delete(dbconnector.statement, Integer.parseInt(splitted[1]), Integer.parseInt(splitted[2]));
                        System.out.println("Received state: " + state);
                        objectOutput.writeObject(state);
                        System.out.println();
                        break;
                    default:
                        System.out.println("Command not recognized!\n");
                        break;
                }

            }
        } catch (BindException be){
            System.out.println("Can't open socket! Probably entered port is not available.");
            return;
        } catch (SocketException se){
            System.out.println("Client connection lost!");
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        } catch (ClassNotFoundException cnfe) {
            throw new RuntimeException(cnfe);
        }
    }
}