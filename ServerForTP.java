import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServerForTP {
    static ExecutorService executeIt = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(3345);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in)))

        {
            System.out.println("Server socket created, command console reader for listen to server commands");

            while (!server.isClosed()) {

                if (buffer.ready()) {
                    System.out.println("Main Server found any messages in channel.");
                    String serverCommand = buffer.readLine();
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        System.out.println("Server exiting...");
                        server.close();
                        break;
                    }
                }

                // если комманд от сервера нет то становимся в ожидание
                // подключения к сокету общения под именем - "clientDialog" на
                // серверной стороне
                Socket client = server.accept();

                // после получения запроса на подключение сервер создаёт сокет
                // для общения с клиентом и отправляет его в отдельную нить
                // в Runnable(при необходимости можно создать Callable)
                // монопоточную нить = сервер - MonoThreadClientHandler и тот
                // продолжает общение от лица сервера
                executeIt.execute(new MonoThreadClientHandler(client));
                System.out.print("Connection accepted.");
            }

            // закрытие пула нитей после завершения работы всех нитей
            executeIt.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
