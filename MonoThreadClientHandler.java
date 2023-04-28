import java.io.*;
import java.net.Socket;
import java.sql.*;

public class MonoThreadClientHandler implements Runnable {
    private static final String url = "jdbc:mysql://26.230.233.234:3306/tp_database";
    private static final String username = "tpdb";
    private static final String password = "421-1Best";
    public static Connection conn;
    public static Statement statement;

    private static Socket clientDialog;

    public MonoThreadClientHandler(Socket client) {
        MonoThreadClientHandler.clientDialog = client;
    }

    @Override
    public void run() {

        try {

            DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            System.out.println("DataInputStream created\nDataOutputStream created");
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement();
            System.out.println("Conn sex");

            while (!clientDialog.isClosed()) {
                System.out.println("Server reading from channel");

                // серверная нить ждёт в канале чтения (inputstream) получения
                // данных клиента после получения данных считывает их
                String entry = in.readUTF();

                // и выводит в консоль
                System.out.println("READ from clientDialog message - " + entry);

                if (entry.equalsIgnoreCase("quit")) {

                    // если кодовое слово получено то инициализируется закрытие
                    // серверной нити
                    System.out.println("Client initialize connections suicide ...");
                    out.writeUTF("Server reply - " + entry + " - OK");
                    Thread.sleep(3000);
                    break;
                }

                if (entry.equalsIgnoreCase("Search")){
                    String SQLcommand = "";
                    //statement.executeUpdate(SQLcommand);
                }
                    // если условие окончания работы не верно - продолжаем работу -
                // отправляем эхо обратно клиенту

                System.out.println("Server try writing to channel");
                out.writeUTF("Server reply - " + entry + " - OK");
                System.out.println("Server Wrote message to clientDialog.");

                // освобождаем буфер сетевых сообщений
                out.flush();

                // возвращаемся в началло для считывания нового сообщения
            }

            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // основная рабочая часть //
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            // если условие выхода - верно выключаем соединения
            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");

            // закрываем сначала каналы сокета !
            in.close();
            out.close();

            // потом закрываем сокет общения с клиентом в нити моносервера
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