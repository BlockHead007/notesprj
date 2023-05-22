package server;
import server.dbcommands;

import java.sql.*;

public class Tests {

    private static final String url = "jdbc:mysql://26.230.233.234:3306/tp_database";
    private static final String username = "tpdb";
    private static final String password = "421-1Best";
    public static Connection conn;
    public static Statement statement;

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();
            System.out.println("Conn sex");

            //ArrayDeque<String> tempSTR = new ArrayDeque<String>();

            //int act = SQLforTP.Registration(statement, "Temp1", "Temp1", "Temp1@notesprj.ru");
            //SQLforTP.Upload(statement, 3, "MyMail", "MySomeText");
            /*SQLforTP.Search(statement,3, tempSTR);

            while(tempSTR.peek()!=null){
                String ID=tempSTR.pop();
                String Name=tempSTR.pop();
                String Data=tempSTR.pop();

                System.out.println(ID + "::" + Name + ":" + Data);
            }*/

            //SQLforTP.Delete(statement, 2, 2);
                boolean proofe = dbcommands.Login(statement, "Temp1", "Temp1");
            System.out.println(proofe);

            int SQLcommandSupport = 1;
            String SQLcommand = "SELECT * FROM tp_database.notes_link";
            ResultSet result = statement.executeQuery(SQLcommand);
            while(result.next()){
                int id = result.getInt(1);
                int id1 = result.getInt(2);
                System.out.printf("%d - %d\n", id, id1);
                //String name = result.getString(2);
                //String data = result.getString(3);
                //String mail = result.getString(4);
                //System.out.printf("%d. %s: %s\n", id, name, data);
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        }
    }
