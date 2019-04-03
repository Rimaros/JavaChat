package ClientSide;

import Client.AuthenticationDate;
import Client.WorkingFrame;
import ClientWindow.ClientFrameData;
import ClientWindow.ClientFrameOperations;
import HelloWindow.HelloData;
import HelloWindow.HelloFrameOperations;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        HelloData helloDate = new HelloData();   // данные стартового окна (окно аутентификации)
        ClientFrameData clientFrameData = new ClientFrameData(); // данные основного окна чата
        AuthenticationDate authenticationDate = new AuthenticationDate(clientFrameData); // данные клиента и подключения

        HelloFrameOperations helloFrameOperations = new HelloFrameOperations(); // операции, связанные с функционалом стартового окна
        ClientFrameOperations clientFrameOperations = new ClientFrameOperations(); // операции, связанные с функционалом основного окна чата


        //ClientFrame clientFrame = new ClientFrame(authenticationDate, clientFrameOperations, clientFrameData);
        //HelloFrame helloFrame = new HelloFrame(authenticationDate, helloDate, helloFrameOperations, clientFrameOperations, clientFrameData, clientFrame);

        new WorkingFrame(authenticationDate, helloDate, helloFrameOperations, clientFrameOperations, clientFrameData); // создание объекта распределителя окон
        //new HelloFrame(authenticationDate, helloDate, helloFrameOperations, clientFrameOperations, clientFrameData); // создание объекта стартового окна
        //new ClientWindow.ClientFrame(authenticationDate, operations);
    }
}
