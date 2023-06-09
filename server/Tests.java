package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Tests {
    public static void main(String[] args) {
        String serverAddress = "88.204.64.47";
        int serverPort = 20000;

        try (Socket socket = new Socket(serverAddress, serverPort)) {
            // Получение потоков для чтения/записи объектов
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
            String message, serverCommand = "";
            Object receivedObject;
            Object receivedObject2;
            Scanner in = new Scanner(System.in);
            //int id; // should be used in gui program
            while (serverCommand != "quit"){
                message = in.nextLine();
                System.out.println("entered line: " + message);
                String[] splitted = message.split("~");
                serverCommand = splitted[0];
                switch (serverCommand){
                    case "regs": // account registration
                        objectOutput.writeObject(message);
                        receivedObject = objectInput.readObject();
                        System.out.println("Server response: " + receivedObject);
                        break;
                    case "gets": // account login and get session
                        objectOutput.writeObject(message);
                        receivedObject = objectInput.readObject();
                        System.out.println("Server response: " + receivedObject);
                        break;
                    case "getd": // get data
                        objectOutput.writeObject(message);
                        receivedObject = objectInput.readObject();
                        receivedObject2 = objectInput.readObject();
                        System.out.println("Server response: " + receivedObject + "\n" + receivedObject2);
                        break;
                    case "chan": // change account name
                        objectOutput.writeObject(message);
                        receivedObject = objectInput.readObject();
                        System.out.println("Server response: " + receivedObject);
                        break;
                    case "chpd": // change account password
                        objectOutput.writeObject(message);
                        receivedObject = objectInput.readObject();
                        System.out.println("Server response: " + receivedObject);
                        break;
                    case "crnt": // create note
                        objectOutput.writeObject(message);
                        receivedObject = objectInput.readObject();
                        System.out.println("Server response: " + receivedObject);
                        break;
                    case "svnt":
                        objectOutput.writeObject(message);
                        receivedObject = objectInput.readObject();
                        System.out.println("Server response: " + receivedObject);
                        break;
                    case "dlnt": // delete note
                        objectOutput.writeObject(message);
                        receivedObject = objectInput.readObject();
                        System.out.println("Server response: " + receivedObject);
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}