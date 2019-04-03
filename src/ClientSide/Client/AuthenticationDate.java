package Client;

import ClientWindow.ClientFrameData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthenticationDate {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 3346;

    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;
    private String login;
    private String name;
    private String password;
    private ArrayList<String> contacts;
    private ReadThread readThread;
    //private String changedContact;


    public AuthenticationDate(ClientFrameData clientFrameData) throws IOException {
        this.socket = new Socket(SERVER_HOST, SERVER_PORT);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.login = "";
        this.name = "";
        this.password = "";
        this.contacts = new ArrayList<>();
        this.readThread = new ReadThread(in, contacts, clientFrameData);
        //this.changedContact = null;

        runReadThread();
    }

    void runReadThread(){
        Thread rthStart = new Thread(readThread);
        rthStart.start();
    }
}
