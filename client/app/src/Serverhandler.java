package client.app.src;

import java.io.*;
import java.net.*;
import java.util.ArrayDeque;

public class Serverhandler {
    public static Serverhandler serverhandler;
    public Socket s;
    public int id;
    public int index = -1;
    public ArrayDeque<String> noteslist = null;
    public String[] mass = new String[3];
    private ObjectOutputStream objectOutput = null;
    private ObjectInputStream objectInput = null;
    private Object receiveObject;
    public int newcon(){
        try {
            s = new Socket("88.204.64.47", 20000);
            return 0;
        } catch (ConnectException ce){
            System.out.println("Can't connect to server!");
            return -1;
        } catch (SocketException se) {
            System.out.println("Can't find server!");
            return -1;
        }
        catch (IOException ie) {
            ie.printStackTrace();
            return -2;
        }
    }
    public int auth(String user, String pass) throws Exception{
        if (objectInput == null || objectOutput == null) {
            objectOutput = new ObjectOutputStream(s.getOutputStream());
            objectInput = new ObjectInputStream(s.getInputStream());
        }
        String message = "logs~" + user + "~" + pass;
        objectOutput.writeObject(message);
        receiveObject = objectInput.readObject();
        int state = (int) receiveObject;
        if (state<0) {

            return state;
        }
        else this.id = state;
        return 0;
    }
    public int registration(String user, String pass, String email) throws Exception{
        if (objectInput == null || objectOutput == null) {
            objectOutput = new ObjectOutputStream(s.getOutputStream());
            objectInput = new ObjectInputStream(s.getInputStream());
        }
        String message = "regs~" + user + "~" + pass + "~" + email;
        objectOutput.writeObject(message);
        receiveObject = objectInput.readObject();
        int state = (int) receiveObject;
        return state;
    }
    public int getnoteslist() throws Exception{
        //noteslist = null;
        if (objectInput == null || objectOutput == null) {
            objectOutput = new ObjectOutputStream(s.getOutputStream());
            objectInput = new ObjectInputStream(s.getInputStream());
        }
        String message = "getd~" + id;
        objectOutput.writeObject(message);
        receiveObject = objectInput.readObject();
        int state = (int) receiveObject;
        //objectInput.reset();
        receiveObject = objectInput.readObject();
        noteslist = (ArrayDeque<String>)receiveObject;

        for(String s : noteslist)
            System.out.println(s);
        return state;
    }
    public int notecreate(String note_name, String note_data) throws Exception{
        if (objectInput == null || objectOutput == null) {
            objectOutput = new ObjectOutputStream(s.getOutputStream());
            objectInput = new ObjectInputStream(s.getInputStream());
        }
        String message = "crnt~" + id + "~" + note_name + "~" + note_data;
        objectOutput.writeObject(message);
        receiveObject = objectInput.readObject();
        int state = (int) receiveObject;
        return state;
    }
    public int noteupdate(int noteid, String note_data) throws Exception{
        if (objectInput == null || objectOutput == null) {
            objectOutput = new ObjectOutputStream(s.getOutputStream());
            objectInput = new ObjectInputStream(s.getInputStream());
        }
        String message = "svnt~" + noteid + "~" + note_data;
        objectOutput.writeObject(message);
        receiveObject = objectInput.readObject();
        int state = (int) receiveObject;
        return state;
    }
    public int notenameupdate(int noteid, String note_name) throws Exception{
        if (objectInput == null || objectOutput == null) {
            objectOutput = new ObjectOutputStream(s.getOutputStream());
            objectInput = new ObjectInputStream(s.getInputStream());
        }
        String message = "nmnt~" + noteid + "~" + note_name;
        objectOutput.writeObject(message);
        receiveObject = objectInput.readObject();
        int state = (int) receiveObject;
        return state;
    }
    public int notedelete(int noteid) throws Exception{
        if (objectInput == null || objectOutput == null) {
            objectOutput = new ObjectOutputStream(s.getOutputStream());
            objectInput = new ObjectInputStream(s.getInputStream());
        }
        String message = "dlnt~" + id + "~" + noteid;
        objectOutput.writeObject(message);
        receiveObject = objectInput.readObject();
        int state = (int) receiveObject;
        return state;

    }
    public int chname(String newname) throws Exception{
        if (objectInput == null || objectOutput == null) {
            objectOutput = new ObjectOutputStream(s.getOutputStream());
            objectInput = new ObjectInputStream(s.getInputStream());
        }
        String message = "chan~" + id + "~" + newname;
        objectOutput.writeObject(message);
        receiveObject = objectInput.readObject();
        int state = (int) receiveObject;
        return state;
    }
    public int chpass(String newpass) throws Exception{
        if (objectInput == null || objectOutput == null) {
            objectOutput = new ObjectOutputStream(s.getOutputStream());
            objectInput = new ObjectInputStream(s.getInputStream());
        }
        String message = "chpd~" + id + "~" + newpass;
        objectOutput.writeObject(message);
        receiveObject = objectInput.readObject();
        int state = (int) receiveObject;
        return state;
    }
}