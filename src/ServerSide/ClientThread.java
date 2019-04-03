import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Scanner;

class ClientThread implements Runnable{

    private PrintWriter out; // поток вывода
    private Scanner in; // поток ввода
    private Operations operations = new Operations(); // экземпляр операций
    String name; // имя клиента

    ClientThread(Socket socket, PrintWriter out) throws IOException, SQLException, ClassNotFoundException { // экземпляр клиентского потока
        String autorize = "0"; // авторизация будет идти постоянно, пока клиент не выйдет или не авторизуется
        while (autorize.equalsIgnoreCase("0")) {

            this.out = out;
            this.in = new Scanner(socket.getInputStream());
            autorize = operations.getAuthDate(in, this.out);
        }
        if(autorize.equalsIgnoreCase("1")){
            this.name = Operations.clientName; // в случае прохождения авторизации именуем клиента и его поток
        }
    }

    @Override
    public void run(){
        try {
            System.out.println("wait for messages...");

            while (true){
                if(in.hasNext()){
                    String clientMessage = in.nextLine();
                    System.out.println(clientMessage);
                    if(clientMessage.equalsIgnoreCase("quit")){
                        break;
                    }
                    String message = String.valueOf(clientMessage.split("_")[0]);
                    String abonent = clientMessage.substring(clientMessage.indexOf("_") + 1, clientMessage.length());
                    operations.sendMessaageToClient(message, abonent, out); // отправка сообщения от абонента абоненту
                }
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.operations.removeClient(this); // удаляем клиента
        }
    }
}
