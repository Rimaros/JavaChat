import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

class StartServer {
    private static final int port = 3346; // хардкодим порт
    static ArrayList<ClientThread> clientThreads = new ArrayList<>(); // потоки клиентов

    StartServer() throws SQLException, ClassNotFoundException { // стартует сервер

        System.out.println("Welcome to Server side"); // служебная информация
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(port); // создаем сокет

            while (!serverSocket.isClosed()) { // пока не закрыт
                System.out.println("Waiting for a Client....");
                clientSocket = serverSocket.accept(); // слушаем клиента
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                ClientThread clientThread = new ClientThread(clientSocket, out); // услышали, запускаем поток клиента
                if(clientThread.name!=null) {
                    clientThreads.add(clientThread); // добавили в список
                    new Thread(clientThread).start(); // запустили его поток
                    System.out.println("Client connected");
                }
            }
        } catch (IOException e) {
            System.out.println("Couldn't listen port " + port);
            System.out.println("Can't accept");
            System.exit(-1);
        } finally {
            try {
                assert clientSocket != null;
                clientSocket.close();
                serverSocket.close();
                System.out.println("Server is Stop");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
