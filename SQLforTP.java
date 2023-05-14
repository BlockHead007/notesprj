import java.sql.*;
import java.util.ArrayDeque;

public class SQLforTP {

    public static void Search(Statement statement, int PersonID, ArrayDeque<String> ArrDe){
        try {
            String SQLcommand = "SELECT * FROM tp_database.notes_link WHERE idaccount_list=" + Integer.toString(PersonID);
            ResultSet result = statement.executeQuery(SQLcommand);
            while (result.next()) {
                int id = result.getInt(2);

                SQLcommand = "SELECT * FROM tp_database.notes_list WHERE idnotes_list=" + Integer.toString(id);
                ResultSet result_Search = statement.executeQuery(SQLcommand);

                String note_id = Integer.toString(result_Search.getInt(1));
                String note_name = result_Search.getString(2);
                String note_data = result_Search.getString(3);

                ArrDe.add(note_id);
                ArrDe.add(note_name);
                ArrDe.add(note_data);
            }
        }
        catch (SQLException trowables){
            trowables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void Upload(Statement statement, int PersonID, String note_name, String note_data){
        try {
            String SQLcommand = "INSERT INTO notes_list (note_name, note_data) VALUES (" + note_name + ", " + note_data + ");";
            statement.executeUpdate(SQLcommand);

            SQLcommand = "SELECT * FROM tp_database.notes_list WHERE idnotes_list="
                    + "("+ note_name + ", " + note_data + ");";
            ResultSet result_UpdateID = statement.executeQuery(SQLcommand);
            while(result_UpdateID.next());
            int id = result_UpdateID.getInt(1);

            SQLcommand = "UPDATE tp_database.notes_link SET idaccount_list=" + Integer.toString(PersonID) +
                    "WHERE idnotes_list=" + Integer.toString(id);
            statement.executeUpdate(SQLcommand);
        }
        catch (SQLException trowables){
            trowables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void Delete(Statement statement, int PersonID, int NoteID){
        try {
            String SQLcommand = "DELETE FROM notes_link WHERE Id = (idaccount_list=" + Integer.toString(PersonID) + ", idnotes_list="+ Integer.toString(NoteID) +")";
            ResultSet result = statement.executeQuery(SQLcommand);

        }
        catch (SQLException trowables){
            trowables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void Login(Statement statement, String Login, String Password, boolean Proof){
        try {
            String SQLcommand = "SELECT * FROM tp_database.account_list WHERE (username=" + Login
                    + ", password=" + Password + ");";
            Proof = statement.execute(SQLcommand);
            if(Proof == false) {
                SQLcommand = "SELECT * FROM tp_database.account_list WHERE (mail=" + Login
                        + ", password=" + Password + ");";
                Proof = statement.execute(SQLcommand);
            }
        }
        catch (SQLException trowables){
            trowables.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static int Registration(Statement statement, String Name, String Password, String Mail){
        try {
            boolean Proof = false;
            Proof = statement.execute("SELECT * FROM tp_database.account_list WHERE mail=" + Mail + ";");
            if (Proof == true) return -1;
            String SQLcommand = "INSERT INTO tp_database.account_list (username, password, mail) VALUES (" + Name + ", " + Password +", " + Mail + ");";
            statement.executeUpdate(SQLcommand);

            SQLcommand = "SELECT * FROM tp_database.account_list WHERE mail=" + Mail + ";";
            ResultSet result_UpdateID = statement.executeQuery(SQLcommand);
            int id = result_UpdateID.getInt(1);
            return id;
        }
        catch (SQLException trowables){
            trowables.printStackTrace();
            throw new RuntimeException();
        }
    }

}
