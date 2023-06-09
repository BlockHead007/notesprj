package server;

import java.sql.*;
import java.util.ArrayDeque;

public class Dbcommands {
    /*public static String GetNoteData(Statement statement, int NoteID){
        try {
            String SQLcommand = "SELECT * FROM tp_database.notes_link WHERE idnotes_list=" + Integer.toString(NoteID);
            SQLcommand = "SELECT * FROM tp_database.notes_list WHERE idnotes_list=" + Integer.toString(NoteID);
        }
    }*/


    public static int Search(Statement statement, int PersonID, ArrayDeque<String> ArrDe){
        try {
            String SQLcommand = "SELECT * FROM tp_database.notes_link WHERE idaccount_list=" + Integer.toString(PersonID);

            ArrayDeque<Integer> tempINT = new ArrayDeque<Integer>();

            ResultSet result_Search = statement.executeQuery(SQLcommand);
            while (result_Search.next())
                tempINT.add(result_Search.getInt("idnotes_list"));

            while(tempINT.peek()!=null) {
                int ID = tempINT.pop();
                SQLcommand = "SELECT * FROM tp_database.notes_list WHERE idnotes_list=" + Integer.toString(ID);
                result_Search = statement.executeQuery(SQLcommand);
                while (result_Search.next()) {
                    String note_id = Integer.toString(result_Search.getInt(1));
                    String note_name = result_Search.getString(2);
                    String note_data = result_Search.getString(3);

                    ArrDe.add(note_id);
                    ArrDe.add(note_name);
                    ArrDe.add(note_data);
                }
            }
            return 0;
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return -1;
        }
    }
//tested-work
    public static int Upload(Statement statement, int PersonID, String note_name, String note_data){
        try {
            String SQLcommand = "INSERT INTO notes_list (note_name, note_data) VALUES ('" + note_name + "', '" + note_data + "');";
            statement.executeUpdate(SQLcommand);

            SQLcommand = "SELECT idnotes_list " +
                            "FROM tp_database.notes_list " +
                    "WHERE note_name='" + note_name + "'AND note_data='" + note_data + "'";
            ResultSet result_UpdateID = statement.executeQuery(SQLcommand);
            ArrayDeque<Integer> tempSTR = new ArrayDeque<Integer>();
            while(result_UpdateID.next()) tempSTR.add(result_UpdateID.getInt("idnotes_list"));
            int id = tempSTR.getLast();
            SQLcommand = "INSERT INTO notes_link (idaccount_list, idnotes_list) VALUES (" + PersonID + " , " + id + ");";
            statement.executeUpdate(SQLcommand);
            return 0;
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return -1;
        }
    }
//tested-work
    public static int UpdateNote(Statement statement, int NoteID, String note_data){
        try{
            String SQLcommand = "UPDATE notes_list SET note_data = '" + note_data + "' WHERE idnotes_list=" + Integer.toString(NoteID);
            statement.executeUpdate(SQLcommand);
            return 0;
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return -1;
        }
    }
    public static int Delete(Statement statement, int PersonID, int NoteID){
        try {
            String SQLcommand = "DELETE FROM notes_link WHERE (idaccount_list=" + Integer.toString(PersonID) + " AND idnotes_list="+ Integer.toString(NoteID) +");";
            statement.executeUpdate(SQLcommand);
            return 0;
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return -1;
        }
    }
//tested-work
    public static Integer Login(Statement statement, String Login, String Password) {
        try {
            boolean Proof = false;int ID = -1;
            String SQLcommand = "SELECT * FROM tp_database.account_list WHERE (username='" + Login
                    + "' AND password='" + Password + "');";
            Proof = statement.execute(SQLcommand);
            if (Proof == true) {
                ResultSet resultSet = statement.executeQuery(SQLcommand);
                while(resultSet.next()) ID = resultSet.getInt(1);
            }
            if (Proof == false) {
                SQLcommand = "SELECT * FROM tp_database.account_list WHERE (mail='" + Login
                        + "' AND password='" + Password + "');";
                Proof = statement.execute(SQLcommand);
                if (Proof == true) {
                    ResultSet resultSet = statement.executeQuery(SQLcommand);
                    while(resultSet.next()) ID = resultSet.getInt(1);
                }
            }
            return ID;
        } catch (Exception ex) {
            System.out.println("Connection failed...");

            System.out.println(ex);
            return -1;
        }
    }
//tested-work
    public static int Registration(Statement statement, String Name, String Password, String Mail){
        try {
            //boolean Proof = false;
            //Proof = statement.execute("SELECT * FROM tp_database.account_list WHERE mail='" + Mail + "';");
            //if (Proof == true) return -1;
            String SQLcommand = "INSERT INTO tp_database.account_list (username, password, mail) VALUES ('" + Name + "', '" + Password +"', '" + Mail + "');";
            statement.executeUpdate(SQLcommand);

            SQLcommand = "SELECT * FROM tp_database.account_list WHERE mail='" + Mail + "';";
            ResultSet result_UpdateID = statement.executeQuery(SQLcommand);
            result_UpdateID.next();
            int id = result_UpdateID.getInt(1);
            return id;
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return -1;
        }
    }
//tested-work
    public static int UsernameUpdate(Statement statement, int PersonID, String Newname){
        try{
            String SQLcommand = "UPDATE account_list SET username = '" + Newname + "' WHERE idaccount_list=" + Integer.toString(PersonID);
            int result = statement.executeUpdate(SQLcommand);
            return result;
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return -1;
        }
    }

    public static int UserpassUpdate(Statement statement, int PersionID, String Pass){
        try{
            String SQLcommand = "UPDATE account_list SET password = '" + Pass + "' WHERE idaccount_list=" + Integer.toString(PersionID);
            int result = statement.executeUpdate(SQLcommand);
            return result;
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
            return -1;
        }
    }

}
