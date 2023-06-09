package client.app.src;

import java.io.*;
import java.net.*;

public class Serverhandler {
    public Socket s;
    public void newcon(){
        try {
            // Создание сокета для подключения к серверу на localhost:1234
            s = new Socket("88.204.64.47", 20000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void auth(String user, String pass) throws Exception{
        // Получение потоков ввода-вывода для обмена данными с сервером
        ObjectOutputStream objectOutput = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());

        // Отправка данных для авторизации
        String message = "gets~" + user + "~" + pass;
        objectOutput.writeObject(message);
        Object receiveObject = objectInput.readObject();
        int id = (int) receiveObject;

        // Обработка результата

        if (id==-1) {
            //Ошибка на стороне сервера
            //Вывести сообщение на экран
            //Закрытие соединения с сервером
            s.close();
            return;
        }
    }
    public void registration(String user, String pass, String email) throws Exception{
        ObjectOutputStream objectOutput = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());

        // Отправка данных для авторизации
        String message = "regs~" + user + "~" + pass + "~" + email;
        objectOutput.writeObject(message);
        Object receiveObject = objectInput.readObject();
        int id = (int) receiveObject;

        // Обработка результата

        if (id==-1) {
            //Ошибка на стороне сервера
            //Вывести сообщение на экран
            //Закрытие соединения с сервером
            return;
        }
        s.close();
    }
    public void getnoteslist(int id) throws Exception{
        ObjectOutputStream objectOutput = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());

        // Отправка данных для авторизации
        String message = "getd~" + id;
        objectOutput.writeObject(message);
        Object receiveObject = objectInput.readObject();

        // Обработка результата

        if (id==-1) {
            //Ошибка на стороне сервера
            //Вывести сообщение на экран
            return;
        }
    }
    public void notecreate(int id, String note_name, String note_data) throws Exception{
        ObjectOutputStream objectOutput = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());

        String message = "crnt~" + id + "~" + note_name + "~" + note_data;
        objectOutput.writeObject(message);
        Object receiveObject = objectInput.readObject();
        id = (int) receiveObject;

        // Обработка результата

        if (id==-1) {
            //Ошибка на стороне сервера
            //Вывести сообщение на экран
            return;
        }
    }
    public void noteupdate(int noteid, String note_data) throws Exception{
        ObjectOutputStream objectOutput = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());

        String message = "svnt~" + noteid + "~" + note_data;
        objectOutput.writeObject(message);
        Object receiveObject = objectInput.readObject();
        int ans = (int) receiveObject;

        if (ans==-1) {
            //Ошибка на стороне сервера
            //Вывести сообщение на экран
            return;
        }
    }
    public void notedelete(int id, int noteid) throws Exception{
        ObjectOutputStream objectOutput = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());

        String message = "dlnt~" + id + "~" + noteid;
        objectOutput.writeObject(message);
        Object receiveObject = objectInput.readObject();
        id = (int) receiveObject;
        if (id==-1) {
            //Ошибка на стороне сервера
            //Вывести сообщение на экран
            return;
        }
    }
    public void chname(int id, String newname) throws Exception{
        ObjectOutputStream objectOutput = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());

        String message = "chan~" + id + "~" + newname;
        objectOutput.writeObject(message);
        Object receiveObject = objectInput.readObject();
        id = (int) receiveObject;
        if (id==-1) {
            //Ошибка на стороне сервера
            //Вывести сообщение на экран
            return;
        }
    }
    public void chpass(int id, String newpass) throws Exception{
        ObjectOutputStream objectOutput = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());

        String message = "chpd~" + id + "~" + newpass;
        objectOutput.writeObject(message);
        Object receiveObject = objectInput.readObject();
        id = (int) receiveObject;
        if (id==-1) {
            //Ошибка на стороне сервера
            //Вывести сообщение на экран
            return;
        }
    }
}